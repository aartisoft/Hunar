package com.info.hunar.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.info.hunar.adapter.CategoryAdapter;
import com.info.hunar.Api_Url.Api_Call;
import com.info.hunar.Api_Url.Base_Url;
import com.info.hunar.Api_Url.RxApiClicent;
import com.info.hunar.R;
import com.info.hunar.Utils.Conectivity;
import com.info.hunar.databinding.FragmentFeaturedFragmentBinding;
import com.info.hunar.model_pojo.category_model.CategoryModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static com.info.hunar.Activity.Home_Activity.card_search;


public class Home_fragment extends Fragment {
    FragmentFeaturedFragmentBinding binding;
    CategoryAdapter categoryAdapter;

    public Home_fragment() {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_featured_fragment, container, false);
        View root = binding.getRoot();//using data binding
        //View root= inflater.inflate(R.layout.fragment_featured_fragment, container, false);
        getActivity().setTitle("Dashboard");

        card_search.setVisibility(View.GONE);//home activity item



        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        if (Conectivity.isConnected(getActivity())){
            GetCategory();//using Rx jva with retrofit

        }else {
            Toast.makeText(getActivity(), "Please check Internet", Toast.LENGTH_SHORT).show();
        }


   return  root;
    }

    @SuppressLint("CheckResult")
    private void GetCategory() {

        final ProgressDialog progressDialog =new ProgressDialog(getActivity(),R.style.MyGravity);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.show();

        Api_Call apiInterface = RxApiClicent.getClient(Base_Url.BaseUrl).create(Api_Call.class);

        apiInterface.GetCategory()
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
                            // Toast.makeText(DirectoryBusinessActivity.this, ""+response.getMsg(), Toast.LENGTH_SHORT).show();

                            if (response.getResponce()==true){
                               // CategoryArrayList=response.getData();
                                //  Toast.makeText(DirectoryBusinessActivity.this, ""+response.getMsg(), Toast.LENGTH_SHORT).show();
                                categoryAdapter = new CategoryAdapter(response.getCategory(), getActivity());
                                binding.setMyAdapter(categoryAdapter);//set databinding adapter
                               // categoryAdapter.notifyDataSetChanged();

                            }else {

                                Toast.makeText(getActivity(), "No record found", Toast.LENGTH_SHORT).show();
                            }

                            categoryAdapter.notifyDataSetChanged();

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

}
