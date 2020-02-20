package com.info.hunar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.info.hunar.BR;
import com.info.hunar.R;
import com.info.hunar.activity.SubCategory_activity;
import com.info.hunar.binding_interface.HomeCategoryClickListener;
import com.info.hunar.databinding.CategoryItemBinding;
import com.info.hunar.databinding.WishlistItemBinding;
import com.info.hunar.model_pojo.category_model.CategoryModelData;
import com.info.hunar.model_pojo.wishlist_model.WishlistModelData;

import java.util.List;

/**
 * Created by Raghvendra Sahu on 19-Feb-20.
 */
public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder>  {

    private List<WishlistModelData> dataModelList;
    Context context;


    public WishlistAdapter(List<WishlistModelData> dataModelList, Context ctx) {
        this.dataModelList = dataModelList;
        context = ctx;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WishlistItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.wishlist_item, parent, false);

        return new ViewHolder(binding);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WishlistModelData dataModel = dataModelList.get(position);
        holder.bind(dataModel);
        holder.itemRowBinding.setModel(dataModel);
        //holder.itemRowBinding.setItemClickListener(this);

        // Log.e("all_artisansSize", ""+dataModel.getProductName());

    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public WishlistItemBinding itemRowBinding;

        public ViewHolder(WishlistItemBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.model, obj);
            itemRowBinding.executePendingBindings();
        }
    }

    public void cardClicked(CategoryModelData f) {
        // Toast.makeText(context, "You clicked " + f.getProductName(), Toast.LENGTH_LONG).show();

//        Intent intent = new Intent(context, SubCategory_activity.class);
//        intent.putExtra("Category_id", f.getId());
//        intent.putExtra("Category_name", f.getCatName());
//        context.startActivity(intent);

    }

}
