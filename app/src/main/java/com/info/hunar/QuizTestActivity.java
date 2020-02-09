package com.info.hunar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizTestActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView toolbarimg,iv_answer;
    TextView txToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_test);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txToolbar = (TextView) findViewById(R.id.txToolbar);
        toolbarimg = (ImageView) findViewById(R.id.imgToolbar);
        iv_answer = (ImageView) findViewById(R.id.iv_answer);


        //txToolbar.setText(getIntent().getStringExtra("painter"));
        toolbarimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        iv_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(QuizTestActivity.this,QuizAnswerActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
