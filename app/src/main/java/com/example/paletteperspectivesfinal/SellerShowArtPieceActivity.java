package com.example.paletteperspectivesfinal;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SellerShowArtPieceActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    DocumentReference reference;
    StorageReference storageRef;
    FirebaseStorage storage;
    TextView name;
    String name1;
    ImageButton backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_remove_artwork);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        name = findViewById(R.id.textView12);
        backButton= findViewById(R.id.imageButton3);

        String imageUrl = getIntent().getStringExtra("imageUrl");

        ImageView imageView = findViewById(R.id.imageView7);

        StorageReference defaultImageRef = storageRef.child(imageUrl);
        defaultImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(SellerShowArtPieceActivity.this)
                        .load(uri)
                        .fitCenter()
                        .into(imageView);
            }
        });

        reference = db.collection("Users").document(mAuth.getCurrentUser().getUid());
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        name1 = document.getString("FirstName");
                        name.setText(name1);
                    }
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
