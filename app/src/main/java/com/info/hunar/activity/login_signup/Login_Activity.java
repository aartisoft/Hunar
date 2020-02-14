package com.info.hunar.activity.login_signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.info.hunar.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Login_Activity extends AppCompatActivity {
    private Button login;
    private TextView makeAcc, forgetpass,new_account;
    private EditText usernm, passoword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernm = (EditText) findViewById(R.id.ed_email);
        passoword = (EditText) findViewById(R.id.ed_password);
        login = (Button) findViewById(R.id.button_email);
        new_account = findViewById(R.id.new_account);

        Click_listenrs();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void Click_listenrs() {
        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(Login_Activity.this, Create_New_Account_Activity.class);
                startActivity(in1);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Login_Activity.this, Sign_In_Activity.class);
                startActivity(in);

            }
        });

    }
}