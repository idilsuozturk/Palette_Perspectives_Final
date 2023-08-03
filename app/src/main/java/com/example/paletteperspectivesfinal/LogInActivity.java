package com.example.paletteperspectivesfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button loginButton = findViewById(R.id.loginButton);

        boolean isBuyerSelected = getIntent().getBooleanExtra("buyerSelected", false);
        boolean isAdminSelected = getIntent().getBooleanExtra("adminSelected", false);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (isBuyerSelected) {
                    intent = new Intent(LogInActivity.this, MainBuyerActivity.class);
                }
                else if (isAdminSelected) {
                    intent = new Intent(LogInActivity.this, MainAdminActivity.class);
                }
                else {
                    intent = new Intent(LogInActivity.this, MainSellerActivity.class);
                }
                startActivity(intent);
            }
        });
    }
}
