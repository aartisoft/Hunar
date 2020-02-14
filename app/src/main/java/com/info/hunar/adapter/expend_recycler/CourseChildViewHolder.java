package com.info.hunar.adapter.expend_recycler;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.info.hunar.R;
import com.info.hunar.activity.Activity_VideoPlay;
import com.info.hunar.activity.SubCourseDetailsActivity;
import com.info.hunar.activity.login_signup.ForgotPassword_Activity;
import com.info.hunar.api_url.Api_Call;
import com.info.hunar.api_url.Base_Url;
import com.info.hunar.api_url.RxApiClicent;
import com.info.hunar.model_pojo.AddRemoveWishList;
import com.info.hunar.model_pojo.ForgotModel;
import com.info.hunar.model_pojo.subcategory_course_model.CourseDataVideo;
import com.info.hunar.session.SessionManager;
import com.info.hunar.utils.Conectivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Raghvendra Sahu on 19-Jan-20.
 */
public class CourseChildViewHolder extends ChildViewHolder {

    private TextView tv_video_name,tv_video_time;
    ImageView iv_wish;
    SessionManager session;
    private String userId;

    public CourseChildViewHolder(View itemView) {
        super(itemView);
        tv_video_name = (TextView) itemView.findViewById(R.id.tv_video_name);
        tv_video_time = (TextView) itemView.findViewById(R.id.tv_video_time);
        iv_wish = itemView.findViewById(R.id.iv_wish);
    }

    public void bind(final CourseDataVideo movies) {
        tv_video_name.setText(movies.getVideoName());
        tv_video_time.setText("Video- "+movies.getTimeDuration()+" mins ");


        tv_video_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String video_name=movies.getVideoName();
                String course_name=movies.getCourse_name();
                Intent in = new Intent(v.getContext(), Activity_VideoPlay.class);
                in.putExtra("course_name",course_name);
                in.putExtra("video_name",video_name);
                in.putExtra("course_url",movies.getVideo());
                //in.putExtra("SubCategory_name", course_name);
                in.putExtra("SubCategory_id", movies.getSubcatId());
                v.getContext().startActivity(in);

            }
        });

        iv_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String video_id=movies.getId();
                session = new SessionManager(v.getContext());
                userId = session.getUser().getUserId();
                if (Conectivity.isConnected(v.getContext())){
                    AddRemoveWishList(video_id,v.getContext());
                }else {
                    Toast.makeText(v.getContext(), "Please check internet", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @SuppressLint("CheckResult")
    private void AddRemoveWishList(String video_id, final Context context) {
        final ProgressDialog progressDialog = new ProgressDialog(context, R.style.MyGravity);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.show();

        Api_Call apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);

        apiInterface.AddRemoveWishlist(video_id,userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<AddRemoveWishList>() {
                    @Override
                    public void onNext(AddRemoveWishList response) {
                        //Handle logic
                        try {
                            // CategoryArrayList.clear();
                            progressDialog.dismiss();
                            Log.e("result_my_test", "" + response.getResponce());

                            if (response.getResponce() == true) {

                                Toast.makeText(context, "" + response.getMessage(), Toast.LENGTH_SHORT).show();

                                if (response.getMessage().equalsIgnoreCase("Add in Wishlist")){
                                    iv_wish.setImageResource(R.drawable.ic_favorite_red);
                                }else {
                                    iv_wish.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                                }

                            } else {

                                //Toast.makeText(context, "" + response.getError_msg(), Toast.LENGTH_SHORT).show();
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
}
