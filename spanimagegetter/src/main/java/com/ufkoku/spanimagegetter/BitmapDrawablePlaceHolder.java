package com.ufkoku.spanimagegetter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class BitmapDrawablePlaceHolder extends BitmapDrawable implements Target {

    private final TextView textView;

    private Drawable drawable;

    public BitmapDrawablePlaceHolder(TextView textView) {
        this.textView = textView;
    }

    public void setDrawable(@Nullable Drawable drawable) {
        if (drawable != null) {
            this.drawable = drawable;
            checkBounds();
        }
    }

    public Drawable getDrawable() {
        return drawable;
    }

    //------------------------------------------------------------------//

    @Override
    public void draw(final Canvas canvas) {
        if (drawable != null) {
            checkBounds();
            drawable.draw(canvas);
        }
    }

    private void checkBounds() {
        float defaultProportion = (float) drawable.getIntrinsicWidth() / (float) drawable.getIntrinsicHeight();
        int width = Math.min(textView.getWidth(), drawable.getIntrinsicWidth());
        int height = (int) ((float) width / defaultProportion);

        boolean needRefresh = false;

        if (getBounds().right != textView.getWidth() || getBounds().bottom != height) {
            setBounds(0, 0, textView.getWidth(), height); //set to full width
            needRefresh = true;
        }

        int halfOfPlaceHolderWidth = (int) ((float) getBounds().right / 2f);
        int halfOfImageWidth = (int) ((float) width / 2f);

        int left = halfOfPlaceHolderWidth - halfOfImageWidth;
        int top = 0;
        int right = halfOfPlaceHolderWidth + halfOfImageWidth;
        int bottom = height;

        if (drawable.getBounds().left != left
                || drawable.getBounds().top != top
                || drawable.getBounds().right != right
                || drawable.getBounds().bottom != bottom) {
            drawable.setBounds(
                    left, //centering an image
                    0,
                    right,
                    bottom);
            needRefresh = true;
        }

        if (needRefresh) {
            textView.setText(textView.getText()); //refresh text
        }
    }

    //------------------------------------------------------------------//

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        setDrawable(new BitmapDrawable(textView.getResources(), bitmap));
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        setDrawable(errorDrawable);
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        setDrawable(placeHolderDrawable);
    }

}