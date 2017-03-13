package com.ufkoku.picasso_extensions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.ufkoku.transformations.form.CircleImageTransformation;
import com.ufkoku.transformations.size.DownscaleToMaxAvailableSizeTransformation;

public class TransformationsDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_transformations);

        Picasso.with(this)
                .load("file:///android_asset/7k_image.jpg")
                .into((ImageView) findViewById(R.id.downscaleTransformationBefore));

        Picasso.with(this)
                .load("file:///android_asset/7k_image.jpg")
                .transform(new DownscaleToMaxAvailableSizeTransformation())
                .into((ImageView) findViewById(R.id.downscaleTransformationAfter));

        Picasso.with(this)
                .load("file:///android_asset/fhd_image.jpg")
                .into((ImageView) findViewById(R.id.circleTransformationBefore));

        Picasso.with(this)
                .load("file:///android_asset/fhd_image.jpg")
                .transform(new CircleImageTransformation())
                .into((ImageView) findViewById(R.id.circleTransformationAfter));
    }

}
