package com.example.paletteperspectivesfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class BuyerProfile extends AppCompatActivity {

    FirebaseAuth auth;
    Button logOutButton;
    FirebaseUser user;
    FirebaseFirestore fireStore;
    DocumentReference reference;
    TextView name;
    TextView surname;
    String name1;
    String surname1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_profile);
        name = findViewById(R.id.textView16);
        surname = findViewById(R.id.textView18);
        auth = FirebaseAuth.getInstance();
        logOutButton = findViewById(R.id.logOutButton1);
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
                        name1 = document.getString("FirstName");
                        surname1 = document.getString("LastName");
                        name.setText(name1);
                        surname.setText(surname1);
                    }
                }
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
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

        ImageButton notificationButton = findViewById(R.id.imageButton);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to BuyerNotification activity
                Intent intent = new Intent(BuyerProfile.this, BuyerNotificationActivity.class);
                startActivity(intent);
            }
        });
    }
}
