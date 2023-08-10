package com.example.paletteperspectivesfinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import Classes.ImageAdapterFor;
import Classes.User;

public class ProfileActivity extends AppCompatActivity implements ImageAdapterFor.OnItemClickListener{
    private TextView firstNameTextView;
    private TextView lastNameTextView;
    FirebaseStorage storage;
    FirebaseFirestore fireStore;
    StorageReference storageRef;
    ImageView profileImageView;
    ImageButton backButton;
    List<String> imageUrls;
    ImageAdapterFor imageAdapterFor;
    RecyclerView images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_seller_profile_page);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        fireStore = FirebaseFirestore.getInstance();
        firstNameTextView = findViewById(R.id.textView16);
        lastNameTextView = findViewById(R.id.textView18);
        backButton = findViewById(R.id.imageButton3);
        profileImageView = findViewById(R.id.imageView10);
        images = findViewById(R.id.recyclerView3);
        images.setHasFixedSize(true);
        images.setLayoutManager(new LinearLayoutManager(this));

        imageUrls = new ArrayList<>();
        imageAdapterFor = new ImageAdapterFor(imageUrls, this);
        images.setAdapter(imageAdapterFor);

        User selectedUser = getIntent().getParcelableExtra("selectedUser");

        if (selectedUser != null) {
            // Display user details in UI elements
            firstNameTextView.setText(selectedUser.getFirstName());
            lastNameTextView.setText(selectedUser.getLastName());
            StorageReference defaultImageRef = storageRef.child(selectedUser.getProfilePictureUrl());
            defaultImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    // Load the default profile image into profileImageView
                    Glide.with(ProfileActivity.this)
                            .load(uri)
                            .into(profileImageView);
                }
            });
        }
        fireStore.collection("Users")
                .document(selectedUser.getId())
                .collection("DigitalCopy")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    imageUrls.clear(); // Clear existing URLs
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        String imageUrl = documentSnapshot.getString("imageUrl");
                        if (imageUrl != null) {
                            imageUrls.add(imageUrl);
                        }
                    }
                    fireStore.collection("Users")
                            .document(selectedUser.getId())
                            .collection("HardCopy") // Replace with the name of your other collection
                            .get()
                            .addOnSuccessListener(otherQueryDocumentSnapshots -> {
                                for (DocumentSnapshot documentSnapshot : otherQueryDocumentSnapshots) {
                                    String imageUrl = documentSnapshot.getString("imageUrl");
                                    if (imageUrl != null) {
                                        imageUrls.add(imageUrl);
                                    }
                                }
                                imageAdapterFor.notifyDataSetChanged(); // Notify adapter of data change
                            })
                            .addOnFailureListener(e -> {
                                // Handle the failure to fetch images from the second collection
                            });
                })
                .addOnFailureListener(e -> {
                    // Handle the failure to fetch images
                });
        imageAdapterFor.setOnItemClickListener(this);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainBuyerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onItemClick(int position) {
        String imageUrl = imageUrls.get(position);
        Intent intent = new Intent(this, ShowArtPieceActivity.class);
        intent.putExtra("imageUrl", imageUrl);
        startActivity(intent);
    }
}
