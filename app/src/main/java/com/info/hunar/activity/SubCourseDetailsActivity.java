package com.info.hunar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.info.hunar.activity.login_signup.Sign_In_Activity;
import com.info.hunar.api_url.Api_Call;
import com.info.hunar.api_url.Base_Url;
import com.info.hunar.api_url.RxApiClicent;
import com.info.hunar.R;
import com.info.hunar.session.SessionManager;
import com.info.hunar.utils.Conectivity;
import com.info.hunar.adapter.expend_recycler.CourceListAdapter;
import com.info.hunar.adapter.expend_recycler.ExpandableRecyclerAdapter;
import com.info.hunar.model_pojo.subcategory_course_model.CourseData;
import com.info.hunar.model_pojo.subcategory_course_model.Sub_course_details_model;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SubCourseDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txToolbar, tv_take_know, tv_mrp,
            tv_subcat_title, tv_desc, tv_certi, tv_offer_price;
    ImageView toolbarimg;
    String SubCategory_id, Category_name, SubCategory_name;
    RecyclerView recycler_course;
    CourceListAdapter mAdapter;
    List<CourseData> courseDataList = new ArrayList<>();
    SessionManager session;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcat_course);
        session = new SessionManager(SubCourseDetailsActivity.this);
        userId = session.getUser().getUserId();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txToolbar = (TextView) findViewById(R.id.txToolbar);
        toolbarimg = (ImageView) findViewById(R.id.imgToolbar);
        tv_desc = findViewById(R.id.tv_desc);
        tv_certi = findViewById(R.id.tv_certi);
        tv_offer_price = findViewById(R.id.tv_offer_price);
        tv_mrp = findViewById(R.id.tv_mrp);
        recycler_course = findViewById(R.id.recycler_course);
        tv_take_know = (TextView) findViewById(R.id.tv_take_know);
        tv_subcat_title = findViewById(R.id.tv_subcat_title);

        try {
            if (getIntent() != null) {
                SubCategory_id = getIntent().getStringExtra("SubCategory_id");
                Category_name = getIntent().getStringExtra("Category_name");
                SubCategory_name = getIntent().getStringExtra("SubCategory_name");

                txToolbar.setText(Category_name);
                tv_subcat_title.setText(SubCategory_name);
            }

        } catch (Exception e) {
        }

        toolbarimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //***************************************
        if (Conectivity.isConnected(SubCourseDetailsActivity.this)) {
            getSubCateCourdeDetails();

        } else {
            Toast.makeText(this, "Please check internet", Toast.LENGTH_SHORT).show();
        }

        //**********************************************************

        tv_mrp.setPaintFlags(tv_mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        tv_take_know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SubCourseDetailsActivity.this, TestKnow_Activity.class);
                in.putExtra("SubCategory_name", SubCategory_name);
                in.putExtra("SubCategory_id", SubCategory_id);
                startActivity(in);
            }
        });


    }

    @SuppressLint("CheckResult")
    private void getSubCateCourdeDetails() {
        final ProgressDialog progressDialog = new ProgressDialog(SubCourseDetailsActivity.this, R.style.MyGravity);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.show();

        Api_Call apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);

        apiInterface.GetSubCategoryDetails(SubCategory_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Sub_course_details_model>() {
                    @Override
                    public void onNext(Sub_course_details_model response) {
                        //Handle logic
                        try {
                            // CategoryArrayList.clear();
                            progressDialog.dismiss();
                            Log.e("result_mr_product", "" + response.getResponce());

                            if (response.getResponce() == true) {
                                // CategoryArrayList=response.getData();
                                tv_certi.setText(response.getData().getSubcategory().getCertificate());
                                tv_desc.setText(response.getData().getSubcategory().getDescription());
                                tv_offer_price.setText(" " + response.getData().getSubcategory().getOfferPrice());
                                tv_mrp.setText("Mrp " + response.getData().getSubcategory().getMrp());

                                setAdapterExpand(response.getData().getCourseData());

                            } else {

                                Toast.makeText(SubCourseDetailsActivity.this, "No record found", Toast.LENGTH_SHORT).show();
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

    private void setAdapterExpand(List<CourseData> courseData) {

        courseDataList = courseData;
        mAdapter = new CourceListAdapter(this, courseDataList);
        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
                CourseData expandedMovieCategory = courseDataList.get(position);

//                Toast.makeText(SubCourseDetailsActivity.this,
//                        "Expand "+expandedMovieCategory.getCourseName(),Toast.LENGTH_SHORT)
//                        .show();
            }

            @Override
            public void onListItemCollapsed(int position) {
                CourseData collapsedMovieCategory = courseDataList.get(position);

//                Toast.makeText(SubCourseDetailsActivity.this,
//                        "Collapse "+collapsedMovieCategory.getCourseName(),
//                        Toast.LENGTH_SHORT)
//                        .show();
            }
        });

        recycler_course.setAdapter(mAdapter);
        recycler_course.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mAdapter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
