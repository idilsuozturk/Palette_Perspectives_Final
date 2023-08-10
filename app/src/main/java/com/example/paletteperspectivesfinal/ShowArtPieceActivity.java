package com.example.paletteperspectivesfinal;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import Classes.User;

public class ShowArtPieceActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    DocumentReference reference;
    StorageReference storageRef;
    FirebaseStorage storage;
    TextView name;
    ImageButton backButton;
    ImageView photo;
    TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_artwork_no_auction_page);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        name = findViewById(R.id.textView34);
        backButton= findViewById(R.id.imageButton3);
        photo = findViewById(R.id.imageView12);
        price = findViewById(R.id.editTextNumber5);

        String imageUrl = getIntent().getStringExtra("imageUrl");
        String price1 = getIntent().getStringExtra("price");
        String name1 = getIntent().getStringExtra("name");

        StorageReference defaultImageRef = storageRef.child(imageUrl);
        defaultImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(ShowArtPieceActivity.this)
                        .load(uri)
                        .fitCenter()
                        .into(photo);
            }
        });
        price.setText(price1);
        name.setText(name1);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
