package com.example.paletteperspectivesfinal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class MainSellerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_seller);

        Button uploadArtworkButton = findViewById(R.id.button);
        Button removeArtworkButton = findViewById(R.id.button5);

        uploadArtworkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUploadOptionsDialog();
            }
        });
        removeArtworkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSellerRemoveArtworkActivity();
            }
        });

    }
    private void openUploadOptionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Upload Artwork");
        builder.setItems(new CharSequence[]{"Hard Copy", "Digital Copy"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // Hard Copy
                        openHardCopyActivity();
                        break;
                    case 1: // Digital Copy
                        openDigitalCopyActivity();
                        break;
                }
            }
        });
        builder.show();
    }
    private void openHardCopyActivity() {
        Intent intent = new Intent(MainSellerActivity.this, HardCopyActivity.class);
        startActivity(intent);
    }

    private void openDigitalCopyActivity() {
        Intent intent = new Intent(MainSellerActivity.this, DigitalCopyActivity.class);
        startActivity(intent);
    }
    private void openSellerRemoveArtworkActivity() {
        Intent intent = new Intent(MainSellerActivity.this, SellerRemoveArtworkActivity.class);
        startActivity(intent);
    }


}