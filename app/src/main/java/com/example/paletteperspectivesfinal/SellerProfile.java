package com.example.paletteperspectivesfinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class SellerProfile extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore fireStore;
    DocumentReference reference;
    FirebaseStorage storage;
    StorageReference storageRef;
    Button buttonLog;
    Button changeImageButton;
    ImageView profileImageView;
    FirebaseUser user;
    TextView name;
    String fullName;
    String forHello;
    TextView hello;
    String profilePictureUrl;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_profile);
        name = findViewById(R.id.nameSeller);
        hello = findViewById(R.id.hello);
        auth = FirebaseAuth.getInstance();
        buttonLog = findViewById(R.id.logOutButton);
        user = auth.getCurrentUser();
        fireStore = FirebaseFirestore.getInstance();
        profileImageView = findViewById(R.id.imageView10);
        changeImageButton = findViewById(R.id.button36);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        changeUrl();

        reference = fireStore.collection("Users").document(user.getUid());
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        forHello = "Hello!\n" + document.getString("FirstName");
                        fullName = document.getString("FirstName") + "\n" + document.getString("LastName");
                        profilePictureUrl = document.getString("profilePictureUrl");
                        name.setText(fullName);
                        hello.setText(forHello);
                        StorageReference defaultImageRef = storageRef.child(profilePictureUrl);
                        defaultImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                // Load the default profile image into profileImageView
                                Glide.with(SellerProfile.this)
                                        .load(uri)
                                        .into(profileImageView);
                            }
                        });
                    }
                }
            }
        });

        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ImageButton backButton = findViewById(R.id.imageButton3);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        changeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open gallery to select a new profile picture
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            // Upload the selected image to Firebase Storage
            StorageReference userImageRef = storageRef.child("ProfilePicture/" + user.getUid() + ".png");
            userImageRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        // Update the profile picture URL in Firestore
                        userImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageUrl = uri.toString();
                                // Update the profilePictureUrl in Firestore
                                Map<String, Object> data = new HashMap<>();
                                data.put("profilePictureUrl", imageUrl);
                                reference.update(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            // Successfully updated profilePictureUrl, load the new image
                                            Glide.with(SellerProfile.this)
                                                    .load(imageUrl)
                                                    .into(profileImageView);
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
            });
        }
    }
    public  void  changeUrl() {
        reference = fireStore.collection("Users").document(user.getUid());
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        profilePictureUrl = document.getString("profilePictureUrl");
                        if(profilePictureUrl.indexOf('?') > 0) {
                            int start = profilePictureUrl.indexOf("Profile");
                            int end = profilePictureUrl.indexOf("?");
                            String profilePictureUrl1 = profilePictureUrl.substring(start, end);
                            String newProfilePictureUrl = profilePictureUrl1.replace("%2F", "/");
                            fireStore.collection("Users")
                                    .document(user.getUid())
                                    .update("profilePictureUrl", newProfilePictureUrl)
                                    .addOnSuccessListener(aVoid -> {
                                        StorageReference defaultImageRef = storageRef.child(newProfilePictureUrl);
                                        defaultImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                // Load the default profile image into profileImageView
                                                Glide.with(SellerProfile.this)
                                                        .load(uri)
                                                        .into(profileImageView);
                                            }
                                        });
                                    })
                                    .addOnFailureListener(e -> {
                                        // Profile picture URL update failed
                                    });

                        }

                    }
                }
            }
        });
    }
}
