package com.info.hunar.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.info.hunar.R;
import com.info.hunar.api_url.Api_Call;
import com.info.hunar.api_url.Base_Url;
import com.info.hunar.api_url.RxApiClicent;
import com.info.hunar.databinding.ActivityProfileBinding;
import com.info.hunar.model_pojo.registration_model.Register_field_model;
import com.info.hunar.model_pojo.registration_model.RegistrationModel;
import com.info.hunar.session.SessionManager;
import com.info.hunar.utils.Conectivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Profile_Activity extends AppCompatActivity {
    ActivityProfileBinding binding;
    SessionManager session;
    String userId;
    Register_field_model registerFieldModel;
    private String Gender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        session = new SessionManager(Profile_Activity.this);
        userId = session.getUser().getUserId();

        registerFieldModel = new Register_field_model(Profile_Activity.this);
        registerFieldModel.setName(session.getUser().getName());
        registerFieldModel.setEmail(session.getUser().getUserEmail());
        registerFieldModel.setAddress(session.getUser().getAddress());
        registerFieldModel.setMobile(session.getUser().getMobileNo());
        if (session.getGender().equalsIgnoreCase("Male")){
            registerFieldModel.setSelectedId(R.id.radio_male);
        }else {
            registerFieldModel.setSelectedId(R.id.radio_female);
        }

        binding.setModel(registerFieldModel);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        binding.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!registerFieldModel.getName().isEmpty() && !registerFieldModel.getEmail().isEmpty() &&
                        !registerFieldModel.getMobile().isEmpty()
                        && !registerFieldModel.getAddress().isEmpty()) {

                    if (!Patterns.EMAIL_ADDRESS.matcher(registerFieldModel.getEmail()).matches()) {
                        Toast.makeText(Profile_Activity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    } else if (registerFieldModel.getMobile().length() < 9 && registerFieldModel.getMobile().length()>11) {
                        Toast.makeText(Profile_Activity.this, "Please enter valid number", Toast.LENGTH_SHORT).show();
                    } else if (registerFieldModel.getSelectedId() == -1) {
                        Toast.makeText(Profile_Activity.this, "Please select gender", Toast.LENGTH_SHORT).show();
                    } else {
                        if (registerFieldModel.getSelectedId() == R.id.radio_male) {
                            Gender = "Male";
                        } else {
                            Gender = "Female";
                        }
                        if (Conectivity.isConnected(Profile_Activity.this)) {
                            UpdateProfileApi(registerFieldModel.getName(),registerFieldModel.getEmail(),registerFieldModel.getPassword()
                                    ,registerFieldModel.getMobile(),registerFieldModel.getAddress(),Gender);

                        } else {
                            Toast.makeText(Profile_Activity.this, "Please check internet", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    Toast.makeText(Profile_Activity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @SuppressLint("CheckResult")
    private void UpdateProfileApi(String name, String email, String password, String mobile, String address,
                                  String gender) {

        final ProgressDialog progressDialog =new ProgressDialog(Profile_Activity.this,R.style.MyGravity);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.show();

        Api_Call apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);

        apiInterface.UpdateUser(name,email,mobile,address,gender,userId)
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
                                Toast.makeText(Profile_Activity.this, "Update successful", Toast.LENGTH_SHORT).show();

                               onBackPressed();
                            }else {

                                Toast.makeText(Profile_Activity.this, "Not update, some error", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
