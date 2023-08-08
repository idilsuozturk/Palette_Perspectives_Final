package com.example.paletteperspectivesfinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import Classes.User;

public class ProfileActivity extends AppCompatActivity {
    private TextView firstNameTextView;
    private TextView lastNameTextView;
    FirebaseStorage storage;
    StorageReference storageRef;
    ImageView profileImageView;
    ImageButton backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_seller_profile_page);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        firstNameTextView = findViewById(R.id.textView16);
        lastNameTextView = findViewById(R.id.textView18);
        backButton = findViewById(R.id.imageButton3);
        profileImageView = findViewById(R.id.imageView10);

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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainBuyerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
