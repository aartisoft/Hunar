package com.info.hunar.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.info.hunar.Api_Url.Api_Call;
import com.info.hunar.Api_Url.Base_Url;
import com.info.hunar.Api_Url.RxApiClicent;
import com.info.hunar.QuizTestActivity;
import com.info.hunar.R;
import com.info.hunar.Utils.Conectivity;
import com.info.hunar.adapter.QuizTest_adapter;
import com.info.hunar.databinding.ActivityTestKnowBinding;
import com.info.hunar.model_pojo.quiz_test_model.QuizTestModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TestKnow_Activity extends AppCompatActivity {
    ActivityTestKnowBinding binding;
    TextView tv_test_know;
    String SubCategory_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_test_know_);
         binding= DataBindingUtil.setContentView(this, R.layout.activity_test_know_);


        tv_test_know = findViewById(R.id.tv_test_know);

        if (getIntent() != null) {
            //txToolbar.setText(getIntent().getStringExtra("painter"));
            SubCategory_id = getIntent().getStringExtra("SubCategory_id");
        }

        if (Conectivity.isConnected(TestKnow_Activity.this)) {
            getSubCateCourdeDetails();

        } else {
            Toast.makeText(this, "Please check internet", Toast.LENGTH_SHORT).show();
        }

        // txToolbar.setText("Basic painter course");

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


        tv_test_know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TestKnow_Activity.this, QuizTestActivity.class);
                startActivity(intent);
            }
        });

    }

    @SuppressLint("CheckResult")
    private void getSubCateCourdeDetails() {
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
                            // CategoryArrayList.clear();
                            progressDialog.dismiss();
                            Log.e("result_my_test",""+ response.getResponce());

                            if (response.getResponce()==true){

                                QuizTest_adapter  quizTest_adapter = new QuizTest_adapter(response.getData().getQuiz(), TestKnow_Activity.this);
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
