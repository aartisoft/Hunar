package com.info.hunar.Activity.LoginSignup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.info.hunar.Api_Url.Api_Call;
import com.info.hunar.Api_Url.Base_Url;
import com.info.hunar.Api_Url.RxApiClicent;
import com.info.hunar.adapter.Slide_Activity_Adapter;
import com.info.hunar.Activity.Home_Activity;
import com.info.hunar.R;
import com.info.hunar.model_pojo.Welcome_Video_Model;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class WelcomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener
{
    ViewPager mViewPager;
    private Slide_Activity_Adapter mAdapter;
    private LinearLayout viewPagerCountDots;
    private int dotsCount;
    private ImageView[] dots;
    LinearLayout llSkip;
    Button signin , browse;
    private Context mContext;
    int[] mResources = {R.color.white, R.color.white, R.color.white};
    VideoView myVideo;
    Api_Call apiInterface;
    private TextView tv_skipvid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_slide);
        apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);
        mContext = WelcomeActivity.this;
        signin = (Button) findViewById(R.id.buttonsign);
        browse = (Button) findViewById(R.id.buttonbrowse);
        myVideo =  findViewById(R.id.myVideo);
        llSkip =  findViewById(R.id.llSkip);
        tv_skipvid = findViewById(R.id.tv_skipvid);
       /* MediaController controller=new MediaController(WelcomeActivity.this);
        controller.hide();
        myVideo.setMediaController(controller);*/

        ////
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams)
                myVideo.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;
        myVideo.setLayoutParams(params);

        get_Video_API();

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerCountDots = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        mAdapter = new Slide_Activity_Adapter(WelcomeActivity.this, mContext, mResources);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(true, new ZoomOutSlideTransformer());
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);
        setPageViewIndicator();


        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mContext, Home_Activity.class);
                startActivity(in);
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mContext, Login_Activity.class);
                startActivity(in);
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
            }
        });

        tv_skipvid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(mContext, Login_Activity.class);
                startActivity(in);
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
            }});

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @SuppressLint("CheckResult")
    private void get_Video_API(){
        apiInterface.Get_Welcome_Video()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Welcome_Video_Model>() {
                    @Override
                    public void onNext(Welcome_Video_Model response) {
                        if (response.getResponce()==true){
                            MediaController vidControl = new MediaController(WelcomeActivity.this);
                            // String path = "android.resource://" + getPackageName() + "/" + R.raw.hunar_welcome_video;
                            String path = Base_Url.CategoryImage_URL+""+response.getData().get(0).getVideo();
                            Log.e("Video URL "," --- "+path);
                            Uri vidUri = Uri.parse(path);
                            myVideo.setVideoURI(vidUri);
                            myVideo.start();
                            vidControl.setAnchorView(myVideo);
                            myVideo.setMediaController(vidControl);

                            myVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    llSkip.setVisibility(View.VISIBLE);      }
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    private void setPageViewIndicator() {

        Log.d("###setPageViewIndicator", " : called");
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(mContext);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    15,
                    15
            );

            params.setMargins(4, 0, 4, 0);

            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mViewPager.setCurrentItem(presentPosition);
                    return true;
                }

            });


            viewPagerCountDots.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.e("###onPageSelected, pos ", String.valueOf(position));
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

        if (position + 1 == dotsCount) {

        } else {

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public void scrollPage(int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        }
}
