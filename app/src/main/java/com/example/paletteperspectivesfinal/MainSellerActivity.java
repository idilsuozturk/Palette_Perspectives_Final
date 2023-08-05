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
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_upload_options, null);
        builder.setView(dialogView);

        Button hardCopyButton = dialogView.findViewById(R.id.hardCopyButton);
        Button digitalCopyButton = dialogView.findViewById(R.id.digitalCopyButton);

        AlertDialog dialog = builder.create();
        dialog.show();

        hardCopyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                openHardCopyActivity();
            }
        });

        digitalCopyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                openDigitalCopyActivity();
            }
        });
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