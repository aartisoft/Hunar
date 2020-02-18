package com.info.hunar.activity.login_signup;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.info.hunar.api_url.Api_Call;
import com.info.hunar.api_url.Base_Url;
import com.info.hunar.api_url.RxApiClicent;
import com.info.hunar.adapter.Slide_Activity_Adapter;
import com.info.hunar.activity.Home_Activity;
import com.info.hunar.R;
import com.info.hunar.model_pojo.Welcome_Video_Model;
import com.info.hunar.session.SessionManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class WelcomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    ViewPager mViewPager;
    private Slide_Activity_Adapter mAdapter;
    private LinearLayout viewPagerCountDots;
    private int dotsCount;
    private ImageView[] dots;
    LinearLayout llSkip;
    Button signin, browse;
    private Context mContext;
    int[] mResources = {R.color.white, R.color.white, R.color.white};
    SimpleExoPlayerView mSimpleExoPlayerView;
    //VideoView myVideo;
    Api_Call apiInterface;
    private TextView tv_skipvid;
    SessionManager sessionManager;

    private SimpleExoPlayer mSimpleExoPlayer;
    private AdaptiveTrackSelection.Factory mAdaptiveTrackSelectionFactory;
    private TrackSelector mTrackSelector;
    private LoadControl mLoadControl;
    private DefaultBandwidthMeter mBandwidthMeter;
    LoopingMediaSource mLoopingMediaSource;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_slide);
        sessionManager = new SessionManager(WelcomeActivity.this);

        apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);
        mContext = WelcomeActivity.this;
        signin = (Button) findViewById(R.id.buttonsign);
        browse = (Button) findViewById(R.id.buttonbrowse);
        //myVideo =  findViewById(R.id.myVideo);
        mSimpleExoPlayerView = (SimpleExoPlayerView) findViewById(R.id.myVideo);
        llSkip = findViewById(R.id.llSkip);
        tv_skipvid = findViewById(R.id.tv_skipvid);

        //*********if play video view video view
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams)
//                myVideo.getLayoutParams();
//        params.width = metrics.widthPixels;
//        params.height = metrics.heightPixels;
//        params.leftMargin = 0;
//        myVideo.setLayoutParams(params);

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
                if (sessionManager.isLoggedIn()) {
                    Intent in = new Intent(mContext, Home_Activity.class);
                    startActivity(in);
                    overridePendingTransition(R.anim.anim_slide_in_left,
                            R.anim.anim_slide_out_left);
                    finish();
                } else {
                    Toast.makeText(WelcomeActivity.this, "Please login first", Toast.LENGTH_SHORT).show();
                }

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mContext, Login_Activity.class);
                startActivity(in);
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
                finish();
            }
        });

        tv_skipvid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(mContext, Login_Activity.class);
                startActivity(in);
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
                finish();
            }
        });

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @SuppressLint("CheckResult")
    private void get_Video_API() {
        apiInterface.Get_Welcome_Video()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Welcome_Video_Model>() {
                    @Override
                    public void onNext(Welcome_Video_Model response) {
                        if (response.getResponce() == true) {
                            MediaController vidControl = new MediaController(WelcomeActivity.this);
                            // String path = "android.resource://" + getPackageName() + "/" + R.raw.hunar_welcome_video;
                            String path = Base_Url.CategoryImage_URL + "" + response.getData().get(0).getVideo();
                            Log.e("Video URL ", " --- " + path);
                            Uri vidUri = Uri.parse(path);

                            // if play video view
//                            myVideo.setVideoURI(vidUri);
//                            myVideo.start();
//                            vidControl.setAnchorView(myVideo);
//                            myVideo.setMediaController(vidControl);
//
//                            myVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//
//                                @Override
//                                public void onCompletion(MediaPlayer mp) {
//                                    llSkip.setVisibility(View.VISIBLE);      }
//                            });

                            //**********play expo player
                            playMedia(path);
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

    private void playMedia(String path) {
        mBandwidthMeter = new DefaultBandwidthMeter();
        mAdaptiveTrackSelectionFactory = new AdaptiveTrackSelection.Factory(mBandwidthMeter);
        mTrackSelector = new DefaultTrackSelector(mAdaptiveTrackSelectionFactory);

        mLoadControl = new DefaultLoadControl();

        mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, mTrackSelector, mLoadControl);

        mSimpleExoPlayerView.setPlayer(mSimpleExoPlayer);

        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(WelcomeActivity.this,
                Util.getUserAgent(WelcomeActivity.this, "com.exoplayerdemo"), bandwidthMeter);

        // Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource(Uri.parse(path),
                dataSourceFactory, extractorsFactory, null, null);

        //  MediaSource videoSource = new ExtractorMediaSource(Uri.parse
        //     ("asset:///measuring.mp4"),dataSourceFactory, extractorsFactory, null, null);

        // Prepare the player with the source.
        mLoopingMediaSource = new LoopingMediaSource(videoSource);

        mSimpleExoPlayer.prepare(videoSource);

        mSimpleExoPlayer.setPlayWhenReady(true);

        mSimpleExoPlayer.addListener(new ExoPlayer.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

                if (playbackState == ExoPlayer.STATE_BUFFERING) {
                    //mProgressBar.setVisibility(View.VISIBLE);
                    progressDialog = new ProgressDialog(WelcomeActivity.this, R.style.MyGravity);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    progressDialog.show();
                } else {
                    //mProgressBar.setVisibility(View.GONE);
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                Toast.makeText(WelcomeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPositionDiscontinuity() {

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
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //playMedia();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopMedia();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMedia();
    }

    private void stopMedia() {
        if (mSimpleExoPlayer!=null){
            mSimpleExoPlayer.stop();
            mSimpleExoPlayer.release();
        }

    }
}
