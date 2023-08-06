package com.example.paletteperspectivesfinal;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainBuyerActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_main_page);

        Button bestSellersButton = findViewById(R.id.button39);
        Button favouritesButton = findViewById(R.id.button40);
        Button accountButton = findViewById(R.id.button41);

        bestSellersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainBuyerActivity.this, BuyerBestSellersActivity.class);
                startActivity(intent);
            }
        });
        favouritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainBuyerActivity.this, BuyerFavouritesActivity.class);
                startActivity(intent);
            }
        });
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainBuyerActivity.this, BuyerProfile.class);
                startActivity(intent);
            }
        });

    }
}
