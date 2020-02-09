package com.info.hunar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.info.hunar.R;
import com.info.hunar.databinding.QuizItemBinding;
import com.info.hunar.model_pojo.quiz_test_model.QuizTestModelDataQuiz;

import java.util.List;

/**
 * Created by Raghvendra Sahu on 09-Feb-20.
 */
public class QuizTest_adapter extends RecyclerView.Adapter<QuizTest_adapter.ViewHolder> {

    private List<QuizTestModelDataQuiz> dataModelList;
    Context context;

    public QuizTest_adapter(List<QuizTestModelDataQuiz> dataModelList, Context ctx) {
        this.dataModelList = dataModelList;
        context = ctx;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        QuizItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.quiz_item, parent, false);

        return new ViewHolder(binding);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QuizTestModelDataQuiz dataModel = dataModelList.get(position);
        holder.bind(dataModel);
        holder.itemRowBinding.setModel(dataModel);
        //  holder.itemRowBinding.setItemClickListener(this);

        // Log.e("all_artisansSize", ""+dataModel.getProductName());

    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public QuizItemBinding itemRowBinding;

        public ViewHolder(QuizItemBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(com.info.hunar.BR.model, obj);
            itemRowBinding.executePendingBindings();
        }
    }

//    public void cardClicked(CategoryModelData f) {
//        // Toast.makeText(context, "You clicked " + f.getProductName(), Toast.LENGTH_LONG).show();
//
//        Intent intent = new Intent(context, SubCategory_activity.class);
//        intent.putExtra("Category_id", f.getId());
//        intent.putExtra("Category_name", f.getCatName());
//        context.startActivity(intent);
//
//    }

}
