package com.example.paletteperspectivesfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registrationAfterActivity extends AppCompatActivity {

    TextInputEditText editTextName, editTextSurname, editTextAge;
    Button button1;
    FirebaseFirestore fireStore;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationafter);
        editTextName = findViewById(R.id.namesign);
        editTextSurname = findViewById(R.id.surnamesign);
        editTextAge = findViewById(R.id.agesign);
        button1 = findViewById(R.id.finishRegister);
        fireStore = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, surname, age, UId;
                name = String.valueOf(editTextName.getText());
                surname = String.valueOf(editTextSurname.getText());
                age = String.valueOf(editTextAge.getText());
                UId = user.getUid();

                CollectionReference Users = fireStore.collection("Users");

                Map<String, Object> user = new HashMap<>();
                user.put("FirstName", name);
                user.put("LastName", surname);
                user.put("age", age);
                user.put("ID",UId);
                Users.document(UId).set(user);

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(surname)) {
                    Toast.makeText(registrationAfterActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(age)) {
                    Toast.makeText(registrationAfterActivity.this, "Enter your age", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age1 = Integer.valueOf(age);
                if (age1 < 18) {
                    Toast.makeText(registrationAfterActivity.this, "You must be at least 18 years old to register", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
