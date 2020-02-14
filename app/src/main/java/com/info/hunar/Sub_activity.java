package com.info.hunar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.info.hunar.activity.SubCourseDetailsActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Sub_activity extends AppCompatActivity
{
    TextView txToolbar;
    Toolbar toolbar;
    ImageView toolbarimg;
    CardView card1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txToolbar = (TextView) findViewById(R.id.txToolbar);
        toolbarimg = (ImageView) findViewById(R.id.imgToolbar);
        card1 =  findViewById(R.id.card1);

        txToolbar.setText("Basic Painter");

        toolbarimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Sub_activity.this, SubCourseDetailsActivity.class);
                startActivity(in);


            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
