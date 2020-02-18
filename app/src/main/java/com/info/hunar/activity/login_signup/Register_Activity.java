package com.info.hunar.activity.login_signup;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.info.hunar.activity.Home_Activity;
import com.info.hunar.R;
import com.info.hunar.api_url.Api_Call;
import com.info.hunar.api_url.Base_Url;
import com.info.hunar.api_url.RxApiClicent;
import com.info.hunar.databinding.CreateNewAcBinding;
import com.info.hunar.model_pojo.registration_model.Register_field_model;
import com.info.hunar.model_pojo.registration_model.RegistrationModel;
import com.info.hunar.session.SessionManager;
import com.info.hunar.utils.Conectivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Register_Activity extends AppCompatActivity {
    CreateNewAcBinding binding;
    Register_field_model registerFieldModel;
    private String Gender;
    SessionManager session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.create_new_ac);
        binding = DataBindingUtil.setContentView(this, R.layout.create_new_ac);
        binding.setRegisterActivity(this);
        session = new SessionManager(Register_Activity.this);
        registerFieldModel = new Register_field_model(Register_Activity.this);
        registerFieldModel.setName("");
        registerFieldModel.setEmail("");
        registerFieldModel.setAddress("");
        registerFieldModel.setPassword("");
        registerFieldModel.setMobile("");
        binding.setModel(registerFieldModel);

        Click_Listeners();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

    public void onButtonClick(View view) {
        if (!registerFieldModel.getName().isEmpty() && !registerFieldModel.getEmail().isEmpty() &&
                !registerFieldModel.getMobile().isEmpty() && !registerFieldModel.getPassword().isEmpty()
                && !registerFieldModel.getAddress().isEmpty()) {

            if (!Patterns.EMAIL_ADDRESS.matcher(registerFieldModel.getEmail()).matches()) {
                Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            } else if (registerFieldModel.getPassword().length() < 5) {
                Toast.makeText(this, "Password must be 6 digits", Toast.LENGTH_SHORT).show();
            } else if (registerFieldModel.getMobile().length() < 9 && registerFieldModel.getMobile().length()>11) {
                Toast.makeText(this, "Please enter valid number", Toast.LENGTH_SHORT).show();
            } else if (registerFieldModel.getSelectedId() == -1) {
                Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            } else {
                if (registerFieldModel.getSelectedId() == R.id.radio_male) {
                    Gender = "Male";
                } else {
                   Gender = "Female";
                }
                if (Conectivity.isConnected(Register_Activity.this)) {
                 RegistrationApi(registerFieldModel.getName(),registerFieldModel.getEmail(),registerFieldModel.getPassword()
                 ,registerFieldModel.getMobile(),registerFieldModel.getAddress(),Gender);

                } else {
                    Toast.makeText(Register_Activity.this, "Please check internet", Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            Toast.makeText(Register_Activity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }


    }

    @SuppressLint("CheckResult")
    private void RegistrationApi(String name, String email, String password, String mobile, String address,
                                 String gender) {

        final ProgressDialog progressDialog =new ProgressDialog(Register_Activity.this,R.style.MyGravity);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.show();

        Api_Call apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);

        apiInterface.RegistrationUser(name,email,password,mobile,address,gender)
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
                                Toast.makeText(Register_Activity.this, "Register successful", Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(Register_Activity.this, Home_Activity.class);
                                startActivity(intent);
                                finish();
                            }else {

                                Toast.makeText(Register_Activity.this, "Not register, some error", Toast.LENGTH_SHORT).show();
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


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void Click_Listeners() {
        binding.tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in1 = new Intent(Register_Activity.this, Sign_In_Activity.class);
                startActivity(in1);

            }
        });


    }


}
