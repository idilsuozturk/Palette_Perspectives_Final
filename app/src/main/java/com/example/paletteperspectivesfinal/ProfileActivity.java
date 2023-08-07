package com.example.paletteperspectivesfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Classes.User;

public class ProfileActivity extends AppCompatActivity {
    private TextView firstNameTextView;
    private TextView lastNameTextView;
    ImageButton backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_seller_profile_page);
        firstNameTextView = findViewById(R.id.textView16);
        lastNameTextView = findViewById(R.id.textView18);
        backButton = findViewById(R.id.imageButton3);

        User selectedUser = getIntent().getParcelableExtra("selectedUser");

        if (selectedUser != null) {
            // Display user details in UI elements
            firstNameTextView.setText(selectedUser.getFirstName());
            lastNameTextView.setText(selectedUser.getLastName());
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
