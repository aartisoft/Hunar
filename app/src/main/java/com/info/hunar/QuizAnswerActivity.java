package com.info.hunar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizAnswerActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView toolbarimg,iv_answer;
    TextView txToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_answer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txToolbar = (TextView) findViewById(R.id.txToolbar);
        toolbarimg = (ImageView) findViewById(R.id.imgToolbar);

        toolbarimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
