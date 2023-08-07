package com.example.paletteperspectivesfinal;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Classes.User;

public class ProfileActivity extends AppCompatActivity {
    private TextView firstNameTextView;
    private TextView lastNameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_seller_profile_page);
        firstNameTextView = findViewById(R.id.textView16);
        lastNameTextView = findViewById(R.id.textView18);

        User selectedUser = getIntent().getParcelableExtra("selectedUser");

        if (selectedUser != null) {
            // Display user details in UI elements
            firstNameTextView.setText(selectedUser.getName());
            lastNameTextView.setText(selectedUser.getLastName());
        }
    }
}
