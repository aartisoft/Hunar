package com.info.hunar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.info.hunar.api_url.Api_Call;
import com.info.hunar.api_url.Base_Url;
import com.info.hunar.api_url.RxApiClicent;
import com.info.hunar.R;
import com.info.hunar.model_pojo.quiz_test_model.QuizTestModelDataQuiz;
import com.info.hunar.utils.Conectivity;
import com.info.hunar.adapter.QuizTest_adapter;
import com.info.hunar.databinding.ActivityTestKnowBinding;
import com.info.hunar.model_pojo.quiz_test_model.QuizTestModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TestKnow_Activity extends AppCompatActivity {
    ActivityTestKnowBinding binding;
    String SubCategory_id,SubCategory_name;
    List<QuizTestModelDataQuiz>quizList=new ArrayList();
    String id,option;
    QuizTest_adapter  quizTest_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_test_know_);
         binding= DataBindingUtil.setContentView(this, R.layout.activity_test_know_);

        if (getIntent() != null) {
            SubCategory_id = getIntent().getStringExtra("SubCategory_id");
            SubCategory_name = getIntent().getStringExtra("SubCategory_name");
           binding.toolbarId.txToolbar.setText(SubCategory_name);
        }

        if (Conectivity.isConnected(TestKnow_Activity.this)) {
            getQuizQues();

        } else {
            Toast.makeText(this, "Please check internet", Toast.LENGTH_SHORT).show();
        }

        binding.toolbarId.imgToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


        binding.tvTestScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///if (quizTest_adapter!=null){

                    //quizTest_adapter.getRadioChecked();
                    Log.e("ans_radio1",""+quizTest_adapter.getRadioChecked());
               // }


                //**********after api call
                Intent intent = new Intent(TestKnow_Activity.this, QuizResultActivity.class);
                intent.putExtra("Course_name",SubCategory_name);
                intent.putExtra("SubCategory_id",SubCategory_id);
                startActivity(intent);
            }
        });

    }

    @SuppressLint("CheckResult")
    private void getQuizQues() {
        final ProgressDialog progressDialog =new ProgressDialog(TestKnow_Activity.this,R.style.MyGravity);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.show();

        Api_Call apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);

        apiInterface.GetQuizTest(SubCategory_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<QuizTestModel>() {
                    @Override
                    public void onNext(QuizTestModel response) {
                        //Handle logic
                        try {
                            quizList.clear();
                            progressDialog.dismiss();
                            Log.e("result_my_test",""+ response.getResponce());

                            if (response.getResponce()==true){
                                quizList=response.getData().getQuiz();
                                 quizTest_adapter = new QuizTest_adapter(quizList, TestKnow_Activity.this);
                                binding.setMyAdapter(quizTest_adapter);//set databinding adapter

                            }else {

                                Toast.makeText(TestKnow_Activity.this, "No record found", Toast.LENGTH_SHORT).show();
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
}
