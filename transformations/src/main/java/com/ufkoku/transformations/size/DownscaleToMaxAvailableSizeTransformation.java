package com.ufkoku.transformations.size;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;

import com.squareup.picasso.Transformation;
import com.ufkoku.utils.GLUtils;

@SuppressLint("LongLogTag")
public class DownscaleToMaxAvailableSizeTransformation implements Transformation {

    private static final String TAG = "DownscaleToMaxAvailableSizeTransformation";

    private static final int maxSize;

    static {
        maxSize = GLUtils.getMaxAvailableTextureSize();
        Log.i(TAG, "Max bitmap size is " + maxSize);
    }

    @Override
    public Bitmap transform(Bitmap source) {
        if (source.getWidth() <= maxSize && source.getHeight() <= maxSize) {
            return source;
        }

        float scaleFactor = (float) maxSize / (float) Math.max(source.getWidth(), source.getHeight());

        Bitmap scaled = Bitmap.createScaledBitmap(
                source,
                (int) Math.min((float) maxSize, (float) source.getWidth() * scaleFactor),
                (int) Math.min((float) maxSize, (float) source.getHeight() * scaleFactor),
                true);

        source.recycle();

        return scaled;
    }

    @Override
    public String key() {
        return "DownscaleToMaxAvailableSizeTransformation";
    }

}
