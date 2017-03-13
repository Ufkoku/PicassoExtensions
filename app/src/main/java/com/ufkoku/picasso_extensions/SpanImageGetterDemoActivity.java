package com.ufkoku.picasso_extensions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.ufkoku.spanimagegetter.PicassoImageGetter;
import com.ufkoku.transformations.size.DownscaleToMaxAvailableSizeTransformation;

public class SpanImageGetterDemoActivity extends AppCompatActivity {

    private static final String TEXT = "<p>This is text</p>\n" +
            "<img src=\"file:///android_asset/7k_image.jpg\"/>\n" +
            "<p>Another text</p>";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_getter);
        final TextView textView = (TextView) findViewById(R.id.text);

        Picasso picasso = new Picasso.Builder(this)
                .requestTransformer(new Picasso.RequestTransformer() {
                    @Override
                    public Request transformRequest(Request request) {
                        return request.buildUpon()
                                .transform(new DownscaleToMaxAvailableSizeTransformation())
                                .build();
                    }
                })
                .build();

        textView.setText(Html.fromHtml(
                TEXT,
                new PicassoImageGetter(textView, picasso),
                null
        ));
    }

}
