package com.example.paletteperspectivesfinal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DigitalCopyActivity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap selectedBitmap;

    private static final int PICK_IMAGE_PDF_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_upload_digitalcopy);

        Button uploadButton = findViewById(R.id.button9);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        FloatingActionButton fabUpload = findViewById(R.id.floatingActionButton2);
        fabUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        imageView = findViewById(R.id.imageView6);

        ImageButton backButton = findViewById(R.id.imageButton3);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf"); // You can modify this to handle PNG and other file types as well
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_PDF_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_PDF_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri fileUri = data.getData();
            // Load the selected image into the ImageView
            try {
                selectedBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(fileUri));
                imageView.setImageBitmap(selectedBitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onApplyBlur(View view) {
        if (selectedBitmap != null) {
            int radius = 5;
            Bitmap blurredBitmap = applyBoxBlur(selectedBitmap, radius);
            imageView.setImageBitmap(blurredBitmap);
        }
    }

    public Bitmap applyBoxBlur(Bitmap src, int radius) {
        int width = src.getWidth();
        int height = src.getHeight();
        int[] pixels = new int[width * height];
        src.getPixels(pixels, 0, width, 0, 0, width, height);

        int size = radius * 2 + 1;
        int numPixels = size * size;

        int[] blurredPixels = new int[width * height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rSum = 0, gSum = 0, bSum = 0;

                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        int px = x + dx;
                        int py = y + dy;

                        if (px >= 0 && px < width && py >= 0 && py < height) {
                            int color = pixels[py * width + px];
                            rSum += Color.red(color);
                            gSum += Color.green(color);
                            bSum += Color.blue(color);
                        }
                    }
                }

                int rBlur = rSum / numPixels;
                int gBlur = gSum / numPixels;
                int bBlur = bSum / numPixels;

                blurredPixels[y * width + x] = Color.rgb(rBlur, gBlur, bBlur);
            }
        }

        Bitmap blurredBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        blurredBitmap.setPixels(blurredPixels, 0, width, 0, 0, width, height);

        return blurredBitmap;
    }

}
