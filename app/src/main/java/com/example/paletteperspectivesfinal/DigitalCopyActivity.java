package com.example.paletteperspectivesfinal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import Classes.DigitalArtPiece;

public class DigitalCopyActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirebaseStorage storage;
    StorageReference storageRef;
    DocumentReference reference;

    EditText priceEditText;
    TextView forHello;
    Switch bidSwitch;
    Button uploadButton;
    FloatingActionButton galleryButton;
    ImageFilterView imageFilterView;
    Uri selectedImageUri;
    ImageButton backButton;
    String hello;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_upload_digitalcopy);
        backButton = findViewById(R.id.imageButton3);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        priceEditText = findViewById(R.id.editText);
        bidSwitch = findViewById(R.id.switch1);
        uploadButton = findViewById(R.id.button9);
        galleryButton = findViewById(R.id.floatingActionButton2);
        imageFilterView = findViewById(R.id.imageFilterView);
        forHello = findViewById(R.id.textView6);

        reference = db.collection("Users").document(mAuth.getCurrentUser().getUid());
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        hello = "Hello!\n" + document.getString("FirstName");
                        forHello.setText(hello);
                    }
                }
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void uploadImage() {
        if (selectedImageUri != null) {
            String userId = mAuth.getCurrentUser().getUid();
            String price = priceEditText.getText().toString();
            boolean canBid = bidSwitch.isChecked();
            String imageId = UUID.randomUUID().toString();
            String imageFileName = userId + "_" + imageId;

            StorageReference imageRef = storageRef.child("images/" + imageFileName);

            imageRef.putFile(selectedImageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String imageUrl = uri.toString();
                            int start = imageUrl.indexOf("images");
                            int end = imageUrl.indexOf("?");
                            String imageUrl1 = imageUrl.substring(start, end);
                            String newImageUrl = imageUrl1.replace("%2F", "/");
                            reference = db.collection("Users").document(userId);
                            reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {
                                            name = document.getString("FirstName");

                                        }
                                    }
                                }
                            });
                            DigitalArtPiece artPiece = new DigitalArtPiece(userId, newImageUrl, price, canBid, name);
                            db.collection("Users").document(userId).collection("DigitalCopy").document(imageId).set(artPiece);
                        });
                    })
                    .addOnFailureListener(e -> {
                        // Handle image upload failure
                    });
        } else {
            // Handle no image selected
        }
        Intent intent = new Intent(getApplicationContext(), MainSellerActivity.class);
        startActivity(intent);
        finish();
    }
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri));
                imageFilterView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
