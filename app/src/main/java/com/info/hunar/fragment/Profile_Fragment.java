package com.info.hunar.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.info.hunar.activity.Profile_Activity;
import com.info.hunar.R;
import com.info.hunar.databinding.FragmentProfileBinding;
import com.info.hunar.session.SessionManager;

import static com.info.hunar.activity.Home_Activity.bottombar;
import static com.info.hunar.activity.Home_Activity.card_search;

public class Profile_Fragment extends Fragment {
    FragmentProfileBinding binding;
    SessionManager session;
    private String userId;


    public Profile_Fragment() {
        // Required empty public constructor
    }

    public static Profile_Fragment newInstance(String param1, String param2) {
        Profile_Fragment fragment = new Profile_Fragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           // mParam1 = getArguments().getString(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       //View root= inflater.inflate(R.layout.fragment_profile_, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_, container,false);
        View view = binding.getRoot();
        card_search.setVisibility(View.GONE);
        session = new SessionManager(getActivity());
        userId = session.getUser().getUserId();


        binding.llViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent in = new Intent(getActivity(), Profile_Activity.class);
            startActivity(in);
            }
        });



        binding.tvWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottombar.getChildAt(2).setSelected(true);
                bottombar.selectTab(2,true);
                Fragment fragment=new Wishes_Course_fragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame, fragment);
                 ft.addToBackStack(null);
                ft.commit();



            }
        });

        binding.tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottombar.selectTab(1,true);
                Fragment fragment=new Searching_Course_fragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame, fragment);
                 ft.addToBackStack(null);
                ft.commit();

            }
        });


    return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Account");
    }


    @Override
    public void onResume() {
        super.onResume();
        binding.tvProfileName.setText(session.getUser().getName());
        binding.tvProfileEmail.setText(session.getUser().getUserEmail());
    }
}
