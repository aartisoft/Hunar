package com.info.hunar.activity.quiz_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.info.hunar.R;
import com.info.hunar.adapter.QuizAnswer_adapter;
import com.info.hunar.adapter.QuizTest_adapter;
import com.info.hunar.api_url.Api_Call;
import com.info.hunar.api_url.Base_Url;
import com.info.hunar.api_url.RxApiClicent;
import com.info.hunar.databinding.ActivityQuizAnswerBinding;
import com.info.hunar.model_pojo.result_answer_model.ResultAnswerModel;
import com.info.hunar.model_pojo.result_model.ResultModel;
import com.info.hunar.session.SessionManager;
import com.info.hunar.utils.Conectivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class QuizAnswerActivity extends AppCompatActivity {
    ActivityQuizAnswerBinding binding;
    String userId, SubCategory_name;
    String SubCategory_id;
    SessionManager session;
    QuizAnswer_adapter quizAnswer_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_quiz_answer);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz_answer);


        session = new SessionManager(QuizAnswerActivity.this);
        userId = session.getUser().getUserId();

        if (getIntent() != null) {
            SubCategory_id = getIntent().getStringExtra("SubCategory_id");
            SubCategory_name = getIntent().getStringExtra("Course_name");
            binding.toolbarId.txToolbar.setText(getIntent().getStringExtra("Course_name"));
        }
        if (Conectivity.isConnected(QuizAnswerActivity.this)) {
            getResultAnswer(SubCategory_id);

        } else {
            Toast.makeText(QuizAnswerActivity.this, "Please check internet", Toast.LENGTH_SHORT).show();
        }

        binding.toolbarId.imgToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @SuppressLint("CheckResult")
    private void getResultAnswer(String subCategory_id) {
        final ProgressDialog progressDialog = new ProgressDialog(QuizAnswerActivity.this, R.style.MyGravity);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.show();

        Api_Call apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);

        apiInterface.GetResultAnswer(subCategory_id, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ResultAnswerModel>() {
                    @Override
                    public void onNext(ResultAnswerModel response) {
                        //Handle logic
                        try {
                            // CategoryArrayList.clear();
                            progressDialog.dismiss();
                            Log.e("result_my_score", "" + response.getResponce());
                            Toast.makeText(QuizAnswerActivity.this, "" + response.getResponce(), Toast.LENGTH_SHORT).show();
                            if (response.getResponce() == true) {
                                quizAnswer_adapter = new QuizAnswer_adapter(response.getData(), QuizAnswerActivity.this);
                                binding.setMyAdapter(quizAnswer_adapter);//set databinding adapter

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


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
