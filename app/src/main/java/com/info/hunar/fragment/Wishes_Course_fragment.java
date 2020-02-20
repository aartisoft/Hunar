package com.info.hunar.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.info.hunar.R;
import com.info.hunar.adapter.CategoryAdapter;
import com.info.hunar.adapter.WishlistAdapter;
import com.info.hunar.api_url.Api_Call;
import com.info.hunar.api_url.Base_Url;
import com.info.hunar.api_url.RxApiClicent;
import com.info.hunar.databinding.FragmentWishesCourseFragmentBinding;
import com.info.hunar.model_pojo.category_model.CategoryModel;
import com.info.hunar.model_pojo.wishlist_model.WishlistModel;
import com.info.hunar.session.SessionManager;
import com.info.hunar.utils.Conectivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.info.hunar.activity.Home_Activity.bottombar;
import static com.info.hunar.activity.Home_Activity.card_search;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Wishes_Course_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Wishes_Course_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Wishes_Course_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    WishlistAdapter categoryAdapter;
    FragmentWishesCourseFragmentBinding binding;
    SessionManager session;
    String userId;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Wishes_Course_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Wishes_Course_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Wishes_Course_fragment newInstance(String param1, String param2) {
        Wishes_Course_fragment fragment = new Wishes_Course_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Wishlist");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wishes__course_fragment, container, false);
        View root = binding.getRoot();//using data binding
        //View root= inflater.inflate(R.layout.fragment_wishes__course_fragment, container, false);
        session = new SessionManager(getActivity());
        userId = session.getUser().getUserId();

        card_search.setVisibility(View.GONE);
        bottombar.getChildAt(2).setSelected(true);
        bottombar.selectTab(2, true);


        if (Conectivity.isConnected(getActivity())) {
            GetWishlist();//using Rx jva with retrofit

        } else {
            Toast.makeText(getActivity(), "Please check Internet", Toast.LENGTH_SHORT).show();
        }

        return root;
    }

    @SuppressLint("CheckResult")
    private void GetWishlist() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.MyGravity);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.show();

        Api_Call apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);

        apiInterface.GetWishlist(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<WishlistModel>() {
                    @Override
                    public void onNext(WishlistModel response) {
                        //Handle logic
                        try {
                            // CategoryArrayList.clear();
                            progressDialog.dismiss();
                            Log.e("result_mr_product", "" + response.getResponce());
                            // Toast.makeText(DirectoryBusinessActivity.this, ""+response.getMsg(), Toast.LENGTH_SHORT).show();

                            if (response.getResponce() == true) {
                                // CategoryArrayList=response.getData();
                                //  Toast.makeText(DirectoryBusinessActivity.this, ""+response.getMsg(), Toast.LENGTH_SHORT).show();
                                categoryAdapter = new WishlistAdapter(response.getData(), getActivity());
                                binding.setMyAdapter(categoryAdapter);//set databinding adapter
                                // categoryAdapter.notifyDataSetChanged();

                            } else {

                                Toast.makeText(getActivity(), "No record found", Toast.LENGTH_SHORT).show();
                            }

                            categoryAdapter.notifyDataSetChanged();

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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
