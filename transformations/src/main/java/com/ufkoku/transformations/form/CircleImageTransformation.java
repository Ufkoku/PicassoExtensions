package com.ufkoku.transformations.form;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import com.squareup.picasso.Transformation;

public class CircleImageTransformation implements Transformation {

    private Paint paint;

    public CircleImageTransformation() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
    }

    @Override
    public Bitmap transform(Bitmap source) {
        final int targetSize;
        final int targetHalfSize;
        final int sourceCenterX;
        final int sourceCenterY;

        targetSize = Math.min(source.getWidth(), source.getHeight());
        targetHalfSize = (int) ((float) targetSize / 2f);
        sourceCenterX = (int) ((float) source.getWidth() / 2f);
        sourceCenterY = (int) ((float) source.getHeight() / 2f);

        Bitmap target = Bitmap.createBitmap(targetSize, targetSize, source.getConfig() != null ? source.getConfig() : Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);

        paint.setStyle(Paint.Style.FILL);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));

        canvas.drawARGB(0, 0, 0, 0);

        paint.setColor(Color.WHITE);
        Rect targetRect = new Rect(0, 0, targetSize, targetSize);
        RectF targetRectF = new RectF(targetRect);
        canvas.drawOval(targetRectF, paint);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4f);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Rect sourceRect = new Rect(
                sourceCenterX - targetHalfSize,
                sourceCenterY - targetHalfSize,
                sourceCenterX + targetHalfSize,
                sourceCenterY + targetHalfSize
        );
        canvas.drawBitmap(source, sourceRect, targetRect, paint);

        source.recycle();

        return target;
    }

    @Override
    public String key() {
        return "CircleImageTransformation";
    }
}
