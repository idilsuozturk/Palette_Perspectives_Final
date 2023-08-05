package com.example.paletteperspectivesfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button adminButton = findViewById(R.id.admin);
        Button buyerButton = findViewById(R.id.buyer);
        Button sellerButton = findViewById(R.id.seller);
        Button areYouNewHere = findViewById(R.id.signIn);

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                intent.putExtra("adminSelected", true);
                startActivity(intent);
            }
        });

        buyerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                intent.putExtra("buyerSelected", true);
                startActivity(intent);
            }
        });

        sellerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here you should check if the user is logged in as a seller
                // For demonstration purposes, I am using a boolean flag to simulate the login status
                boolean isSellerLoggedIn = true;

                if (isSellerLoggedIn) {
                    Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                    startActivity(intent);
                } else {
                    // If the user is not logged in, you can navigate them to the login page
                    Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                    startActivity(intent);
                }
            }
        });

        areYouNewHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
