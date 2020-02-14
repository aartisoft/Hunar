package com.info.hunar.activity.login_signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.info.hunar.activity.Home_Activity;
import com.info.hunar.R;
import com.info.hunar.api_url.Api_Call;
import com.info.hunar.api_url.Base_Url;
import com.info.hunar.api_url.RxApiClicent;
import com.info.hunar.databinding.ActivitySignInBinding;
import com.info.hunar.model_pojo.registration_model.Register_field_model;
import com.info.hunar.model_pojo.registration_model.RegistrationModel;
import com.info.hunar.session.SessionManager;
import com.info.hunar.utils.Conectivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Sign_In_Activity extends AppCompatActivity {
    ActivitySignInBinding binding;
    Register_field_model registerFieldModel;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sign__in_);
      binding = DataBindingUtil.setContentView(this, R.layout.activity_sign__in_);
        registerFieldModel = new Register_field_model(Sign_In_Activity.this);
        registerFieldModel.setEmail("");
        registerFieldModel.setPassword("");
        binding.setModel(registerFieldModel);
        session = new SessionManager(Sign_In_Activity.this);

        binding.tvNextSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!registerFieldModel.getEmail().isEmpty() && !registerFieldModel.getPassword().isEmpty()) {

                    if (!Patterns.EMAIL_ADDRESS.matcher(registerFieldModel.getEmail()).matches()) {
                        Toast.makeText(Sign_In_Activity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    } else if (registerFieldModel.getPassword().length() < 5) {
                        Toast.makeText(Sign_In_Activity.this, "Password must be 6 digits", Toast.LENGTH_SHORT).show();
                    } else{

                        if (Conectivity.isConnected(Sign_In_Activity.this)) {
                            LoginApi(registerFieldModel.getEmail(),registerFieldModel.getPassword());

                        } else {
                            Toast.makeText(Sign_In_Activity.this, "Please check internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                    }else {
                    Toast.makeText(Sign_In_Activity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(Sign_In_Activity.this, ForgotPassword_Activity.class);
                startActivity(in1);

            }
        });


    }

    @SuppressLint("CheckResult")
    private void LoginApi(String email, String password) {

        final ProgressDialog progressDialog =new ProgressDialog(Sign_In_Activity.this,R.style.MyGravity);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.show();

        Api_Call apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);

        apiInterface.LoginUser(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<RegistrationModel>() {
                    @Override
                    public void onNext(RegistrationModel response) {
                        //Handle logic
                        try {
                            // CategoryArrayList.clear();
                            progressDialog.dismiss();
                            Log.e("result_my_test",""+ response.getResponce());

                            if (response.getResponce()==true){
                                session.createSession(response.getData());
                                Toast.makeText(Sign_In_Activity.this, "Login successful", Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(Sign_In_Activity.this, Home_Activity.class);
                                startActivity(intent);
                                finish();
                            }else {

                                Toast.makeText(Sign_In_Activity.this, "Not login, some error", Toast.LENGTH_SHORT).show();
                            }

                        }catch (Exception e){
                            progressDialog.dismiss();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Handle error
                        progressDialog.dismiss();
                        Log.e("mr_product_error", e.toString());
                        // Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        progressDialog.dismiss();
                    }
                });
    }
}
