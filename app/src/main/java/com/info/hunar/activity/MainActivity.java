package com.info.hunar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.info.hunar.R;

public class MainActivity extends AppCompatActivity {

    private Button signUp, login;
    private TextView makeAcc,forgetpass;
    private EditText usernm, passoword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
