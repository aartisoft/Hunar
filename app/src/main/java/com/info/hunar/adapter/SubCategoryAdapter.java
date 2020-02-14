package com.info.hunar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.info.hunar.R;
import com.info.hunar.activity.SubCourseDetailsActivity;
import com.info.hunar.binding_interface.HomeCategoryClickListener;
import com.info.hunar.databinding.CategoryItemBinding;
import com.info.hunar.model_pojo.category_model.CategoryModelData;

import java.util.List;

/**
 * Created by Raghvendra Sahu on 15-Jan-20.
 */
public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> implements HomeCategoryClickListener {

    private List<CategoryModelData> dataModelList;
    Context context;
    String Category_name;


    public SubCategoryAdapter(List<CategoryModelData> dataModelList, Context ctx, String category_name) {
        this.dataModelList = dataModelList;
        context = ctx;
        Category_name = category_name;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CategoryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.category_item, parent, false);

        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CategoryModelData dataModel = dataModelList.get(position);
        holder.bind(dataModel);
        holder.itemRowBinding.setModel(dataModel);
        holder.itemRowBinding.setItemClickListener(this);

        // Log.e("all_artisansSize", ""+dataModel.getProductName());

    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CategoryItemBinding itemRowBinding;

        public ViewHolder(CategoryItemBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(com.info.hunar.BR.model, obj);
            itemRowBinding.executePendingBindings();
        }
    }

    public void cardClicked(CategoryModelData f) {
        // Toast.makeText(context, "You clicked " + f.getProductName(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, SubCourseDetailsActivity.class);
        intent.putExtra("SubCategory_id", f.getId());
        intent.putExtra("SubCategory_name", f.getCatName());
        intent.putExtra("Category_name", Category_name);
        context.startActivity(intent);

    }

}
