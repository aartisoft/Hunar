package com.info.hunar.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;
import com.info.hunar.Fragment.Home_fragment;
import com.info.hunar.Fragment.Profile_Fragment;
import com.info.hunar.Fragment.Searching_Course_fragment;
import com.info.hunar.Fragment.Wishes_Course_fragment;
import com.info.hunar.R;
import com.smarteist.autoimageslider.SliderView;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Home_Activity extends AppCompatActivity
{
    private SliderView sliderView;
    public static BottomNavigationBar bottombar;
    private TextView painter;
    public  static CardView card_search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
       // sliderView = (SliderView) findViewById(R.id.imageSlider);
        bottombar = (BottomNavigationBar) findViewById(R.id.bottom_bar);
       // painter = findViewById(R.id.chip_Painter);
        card_search = findViewById(R.id.card_search);

        BottomBarItem item1 = new BottomBarItem(R.drawable.ic_home_black_24dp,R.string.item1);
        BottomBarItem item2 = new BottomBarItem(R.drawable.ic_arrow_downward_black_24dp,R.string.item2);
       // BottomBarItem item3 = new BottomBarItem(R.drawable.courss,R.string.item3);
        BottomBarItem item4 = new BottomBarItem(R.drawable.ic_favorite_border_black_24dp,R.string.item4);
        BottomBarItem item5 = new BottomBarItem(R.drawable.ic_account_circle_black_24dp,R.string.item5);

        bottombar.addTab(item1);
        bottombar.addTab(item2);
       // bottombar.addTab(item3);
        bottombar.addTab(item4);
        bottombar.addTab(item5);


        //open default fragment
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.frame, new Home_fragment());
        tx.commit();


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        //bottom bar set on click listener
        bottombar.setOnSelectListener(new BottomNavigationBar.OnSelectListener() {
            @Override
            public void onSelect(int position) {
                if (position == 0)
                {
                   // Toast.makeText(Home_Activity.this, "Featured", Toast.LENGTH_SHORT).show();

                    Fragment fragment=new Home_fragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame, fragment);
                    // ft.addToBackStack(null);
                    ft.commit();
                }
                else if (position == 1)
                {

                    Fragment fragment=new Searching_Course_fragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame, fragment);
                    // ft.addToBackStack(null);
                    ft.commit();

                   // Toast.makeText(Home_Activity.this, "Searching Courses", Toast.LENGTH_SHORT).show();
                }

                // Toast.makeText(Home_Activity.this, "Courses", Toast.LENGTH_SHORT).show();

//                Fragment fragment=new My_cources_Fragment();
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.frame, fragment);
//                // ft.addToBackStack(null);
              //  ft.commit();

                else if (position == 2)
                {
                    // Toast.makeText(Home_Activity.this, "Wishlist", Toast.LENGTH_SHORT).show();
                    Fragment fragment=new Wishes_Course_fragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame, fragment);
                    // ft.addToBackStack(null);
                    ft.commit();


                }
                else if (position == 3)
                {

                    Fragment fragment=new Profile_Fragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame, fragment);
                    // ft.addToBackStack(null);
                    ft.commit();
                }
                else if (position == 4)
                {


                }
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            return true;
        }
        if (id == R.id.action_share) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
