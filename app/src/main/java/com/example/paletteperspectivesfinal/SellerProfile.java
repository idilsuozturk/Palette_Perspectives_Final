package com.example.paletteperspectivesfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SellerProfile extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore fireStore;
    DocumentReference reference;
    Button buttonLog;
    FirebaseUser user;
    TextView name;
    String fullName;
    String forHello;
    TextView hello;

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

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        reference = fireStore.collection("Users").document(user.getUid());
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        forHello = "Hello!\n" + document.getString("FirstName");
                        fullName = document.getString("FirstName") + " " + document.getString("LastName");
                        name.setText(fullName);
                        hello.setText(forHello);
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
    }
}
