package com.ufkoku.spanimagegetter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

public class PicassoImageGetter implements Html.ImageGetter {

    private final TextView targetTextView;

    private final Picasso picasso;

    @Nullable
    private final Drawable placeHolderDrawable;

    @Nullable
    private final Drawable errorDrawable;

    public PicassoImageGetter(TextView targetTextView, Picasso picasso) {
        this(targetTextView, picasso, null, null);
    }

    public PicassoImageGetter(TextView target, Picasso picasso, @Nullable Drawable placeHolderDrawable, @Nullable Drawable errorDrawable) {
        this.targetTextView = target;
        this.picasso = picasso;
        this.placeHolderDrawable = placeHolderDrawable;
        this.errorDrawable = errorDrawable;
    }

    @Override
    public Drawable getDrawable(String source) {
        BitmapDrawablePlaceHolder targetDrawable = new BitmapDrawablePlaceHolder(targetTextView);

        RequestCreator requestCreator = picasso.load(source);

        if (placeHolderDrawable != null) {
            requestCreator.placeholder(placeHolderDrawable);
        }

        if (errorDrawable != null) {
            requestCreator.error(errorDrawable);
        }

        requestCreator.into(targetDrawable);

        return targetDrawable;
    }

}
