package com.info.hunar.activity.quiz_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.info.hunar.R;
import com.info.hunar.activity.SubCourseDetailsActivity;
import com.info.hunar.api_url.Api_Call;
import com.info.hunar.api_url.Base_Url;
import com.info.hunar.api_url.RxApiClicent;
import com.info.hunar.databinding.ActivityTestResultBinding;
import com.info.hunar.model_pojo.result_model.ResultModel;
import com.info.hunar.model_pojo.result_model.ResultPdfModel;
import com.info.hunar.session.SessionManager;
import com.info.hunar.utils.Conectivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class QuizResultActivity extends AppCompatActivity {
    ActivityTestResultBinding binding;
    String SubCategory_id;
    SessionManager session;
    String userId,SubCategory_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_test_result);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_test_result);

        session = new SessionManager(QuizResultActivity.this);
        userId = session.getUser().getUserId();

        if (getIntent() != null) {
            SubCategory_id = getIntent().getStringExtra("SubCategory_id");
            SubCategory_name = getIntent().getStringExtra("Course_name");
            binding.toolbarId.txToolbar.setText(getIntent().getStringExtra("Course_name"));
        }

        if (Conectivity.isConnected(QuizResultActivity.this)) {
            getResultScore(SubCategory_id);

        } else {
            Toast.makeText(QuizResultActivity.this, "Please check internet", Toast.LENGTH_SHORT).show();
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
                Intent intent=new Intent(QuizResultActivity.this, QuizAnswerActivity.class);
                intent.putExtra("Course_name",SubCategory_name);
                intent.putExtra("SubCategory_id",SubCategory_id);
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
    private void getResultScore(String subCategory_id) {
        final ProgressDialog progressDialog = new ProgressDialog(QuizResultActivity.this, R.style.MyGravity);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.show();

        Api_Call apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);

        apiInterface.GetResultScore(subCategory_id,userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ResultModel>() {
                    @Override
                    public void onNext(ResultModel response) {
                        //Handle logic
                        try {
                            // CategoryArrayList.clear();
                            progressDialog.dismiss();
                            Log.e("result_my_score", "" + response.getResponce());
                            Toast.makeText(QuizResultActivity.this, "" + response.getResponce(), Toast.LENGTH_SHORT).show();
                            if (response.getResponce() == true) {

                                binding.tvTotalScore.setText("Total score- "+response.getTotalScore());
                                binding.tvAttempt.setText("Attempt- "+response.getAttempt());
                                binding.tvUpattempt.setText("Not attempt- "+response.getNotAttempt());
                                binding.tvWrong.setText("Wrong- "+response.getWrong());
                                binding.tvRight.setText("Right- "+response.getRight());


                            } else {

                                //Toast.makeText(QuizResultActivity.this, "" + response.getError_msg(), Toast.LENGTH_SHORT).show();
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

    @SuppressLint("CheckResult")
    private void getResultPdf(String subCategory_id) {
        final ProgressDialog progressDialog = new ProgressDialog(QuizResultActivity.this, R.style.MyGravity);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.show();

        Api_Call apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);

        apiInterface.GetPdf(subCategory_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ResultPdfModel>() {
                    @Override
                    public void onNext(ResultPdfModel response) {
                        //Handle logic
                        try {
                            // CategoryArrayList.clear();
                            progressDialog.dismiss();
                            Log.e("result_my_test", "" + response.getResponce());

                            if (response.getResponce() == true) {

                                //Toast.makeText(QuizResultActivity.this, "" + response.getPdf(), Toast.LENGTH_SHORT).show();
                                if (response.getPdf()!=null && !response.getPdf().isEmpty()){
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.getPdf()));
                                    startActivity(browserIntent);
                                }else {
                                    Toast.makeText(QuizResultActivity.this, "Pdf not found", Toast.LENGTH_SHORT).show();
                                }



                            } else {

                                Toast.makeText(QuizResultActivity.this, "" + response.getErrorMsg(), Toast.LENGTH_SHORT).show();
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
        //super.onBackPressed();
        Intent intent=new Intent(QuizResultActivity.this, SubCourseDetailsActivity.class);
        startActivity(intent);
    }
}
