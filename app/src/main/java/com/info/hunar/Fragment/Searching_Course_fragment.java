package com.info.hunar.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.info.hunar.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static com.info.hunar.Activity.Home_Activity.bottombar;
import static com.info.hunar.Activity.Home_Activity.card_search;

public class Searching_Course_fragment extends Fragment {



    public Searching_Course_fragment() {
        // Required empty public constructor
    }


    public static Home_fragment newInstance(String param1, String param2) {
        Home_fragment fragment = new Home_fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.activity_searching_course_, container, false);
        getActivity().setTitle("Downloads");

        card_search.setVisibility(View.GONE);

        bottombar.getChildAt(1).setSelected(true);
        bottombar.selectTab(1,true);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        return  root;
    }







//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
