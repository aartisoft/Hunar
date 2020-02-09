package com.info.hunar.Activity.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.info.hunar.Activity.Home_Activity;
import com.info.hunar.R;

public class Sign_In_Activity extends AppCompatActivity {

   TextView tv_next_signin,tv_forgot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__in_);

        tv_next_signin=findViewById(R.id.tv_next_signin);
        tv_forgot=findViewById(R.id.tv_forgot);

        tv_next_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(Sign_In_Activity.this, Home_Activity.class);
                startActivity(in1);

            }
        });

        tv_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(Sign_In_Activity.this, ForgotPassword_Activity.class);
                startActivity(in1);

            }
        });


    }
}
