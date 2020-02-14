package com.info.hunar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.info.hunar.activity.TestKnow_Activity;
import com.info.hunar.activity.login_signup.ForgotPassword_Activity;
import com.info.hunar.api_url.Api_Call;
import com.info.hunar.api_url.Base_Url;
import com.info.hunar.api_url.RxApiClicent;
import com.info.hunar.databinding.ActivityTestResultBinding;
import com.info.hunar.model_pojo.ForgotModel;
import com.info.hunar.utils.Conectivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class QuizResultActivity extends AppCompatActivity {
    ActivityTestResultBinding binding;
    String SubCategory_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_test_result);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_test_result);

        if (getIntent() != null) {
            SubCategory_id = getIntent().getStringExtra("SubCategory_id");
            //SubCategory_name = getIntent().getStringExtra("SubCategory_name");
            binding.toolbarId.txToolbar.setText(getIntent().getStringExtra("Course_name"));
        }


        binding.toolbarId.imgToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        binding.ivAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuizResultActivity.this,QuizAnswerActivity.class);
                startActivity(intent);
            }
        });

        binding.tvDownloadPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Conectivity.isConnected(QuizResultActivity.this)) {
                    getResultPdf(SubCategory_id);

                } else {
                    Toast.makeText(QuizResultActivity.this, "Please check internet", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @SuppressLint("CheckResult")
    private void getResultPdf(String subCategory_id) {
        final ProgressDialog progressDialog = new ProgressDialog(QuizResultActivity.this, R.style.MyGravity);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.show();

        Api_Call apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);

        apiInterface.GetPdf(subCategory_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ForgotModel>() {
                    @Override
                    public void onNext(ForgotModel response) {
                        //Handle logic
                        try {
                            // CategoryArrayList.clear();
                            progressDialog.dismiss();
                            Log.e("result_my_test", "" + response.getResponce());

                            if (response.getResponce() == true) {

                                Toast.makeText(QuizResultActivity.this, "" + response.getMsg(), Toast.LENGTH_SHORT).show();

                            } else {

                                Toast.makeText(QuizResultActivity.this, "" + response.getError_msg(), Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
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
    public void onBackPressed() {
        super.onBackPressed();
    }
}
