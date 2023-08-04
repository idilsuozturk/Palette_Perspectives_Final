package com.example.paletteperspectivesfinal;

import android.graphics.Bitmap;
import android.graphics.Color;

public class BoxBlur {

    public static Bitmap applyBoxBlur(Bitmap src, int radius) {
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
