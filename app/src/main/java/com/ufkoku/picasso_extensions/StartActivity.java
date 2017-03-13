package com.ufkoku.picasso_extensions;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        findViewById(R.id.transformationsDemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, TransformationsDemoActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.spanImageGetterDemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, SpanImageGetterDemoActivity.class);
                startActivity(intent);
            }
        });
    }
}
