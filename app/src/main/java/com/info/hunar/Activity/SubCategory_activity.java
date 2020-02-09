package com.info.hunar.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.info.hunar.Api_Url.Api_Call;
import com.info.hunar.Api_Url.Base_Url;
import com.info.hunar.Api_Url.RxApiClicent;
import com.info.hunar.R;
import com.info.hunar.Utils.Conectivity;
import com.info.hunar.adapter.SubCategoryAdapter;
import com.info.hunar.databinding.SubcatActivityBinding;
import com.info.hunar.model_pojo.category_model.CategoryModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SubCategory_activity extends AppCompatActivity {
    SubcatActivityBinding binding;
    String Category_id,Category_name;
    SubCategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.subcat_activity);
         binding= DataBindingUtil.setContentView(this, R.layout.subcat_activity);


        try {
            if (getIntent()!=null){
                Category_id=getIntent().getStringExtra("Category_id");
                Category_name=getIntent().getStringExtra("Category_name");

                binding.toolbarTag.txToolbar.setText(Category_name);
            }

        }catch (Exception e){

        }

        if (Conectivity.isConnected(SubCategory_activity.this)){
            GetSubCategory();//using Rx jva with retrofit

        }else {
            Toast.makeText(SubCategory_activity.this, "Please check Internet", Toast.LENGTH_SHORT).show();
        }


        binding.toolbarTag.imgToolbar.setOnClickListener(new View.OnClickListener() {
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

    }

    @SuppressLint("CheckResult")
    private void GetSubCategory() {
        final ProgressDialog progressDialog =new ProgressDialog(SubCategory_activity.this,R.style.MyGravity);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.show();

        Api_Call apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);

        apiInterface.GetSubCategory(Category_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<CategoryModel>() {
                    @Override
                    public void onNext(CategoryModel response) {
                        //Handle logic
                        try {
                            // CategoryArrayList.clear();
                            progressDialog.dismiss();
                            Log.e("result_mr_product",""+ response.getResponce());

                            if (response.getResponce()==true){
                                // CategoryArrayList=response.getData();

                                categoryAdapter = new SubCategoryAdapter(response.getData(), SubCategory_activity.this,Category_name);
                                binding.setMyAdapter(categoryAdapter);//set databinding adapter
                                // categoryAdapter.notifyDataSetChanged();

                            }else {

                                Toast.makeText(SubCategory_activity.this, "No record found", Toast.LENGTH_SHORT).show();
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
