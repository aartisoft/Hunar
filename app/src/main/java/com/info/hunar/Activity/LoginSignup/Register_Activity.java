package com.info.hunar.Activity.LoginSignup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.info.hunar.Activity.Home_Activity;
import com.info.hunar.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Register_Activity extends AppCompatActivity
{
    private Button SignUp;
    private EditText Ed_Name, Ed_Email, Ed_Phone, Ed_Pass, Ed_ConfirmPass;
    private ImageView img_Back;
    TextView tv_signin,tv_new_signup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_ac);

//        SignUp = (Button) findViewById(R.id.SignUp);
//        Ed_Name = (EditText) findViewById(R.id.ed_name);
//        Ed_Email = (EditText) findViewById(R.id.ed_email);
//        Ed_Phone = (EditText) findViewById(R.id.ed_phonenum);
//        Ed_Pass = (EditText) findViewById(R.id.ed_password);
//        Ed_ConfirmPass = (EditText) findViewById(R.id.ed_confirm_password);
       // img_Back = (ImageView) findViewById(R.id.backpress);
        tv_signin =  findViewById(R.id.tv_signin);
        tv_new_signup =  findViewById(R.id.tv_new_signup);

        Click_Listeners();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));    }

    private void Click_Listeners()
    {
        tv_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in1 = new Intent(Register_Activity.this, Sign_In_Activity.class);
                startActivity(in1);

            }
        });

        tv_new_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in1 = new Intent(Register_Activity.this, Home_Activity.class);
                startActivity(in1);

            }
        });



    }



}
