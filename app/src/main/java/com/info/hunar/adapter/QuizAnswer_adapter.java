package com.info.hunar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.info.hunar.R;
import com.info.hunar.databinding.AnswerItemBinding;
import com.info.hunar.model_pojo.result_answer_model.ResultAnswerModelData;
import java.util.List;

/**
 * Created by Raghvendra Sahu on 18-Feb-20.
 */
public class QuizAnswer_adapter extends RecyclerView.Adapter<QuizAnswer_adapter.ViewHolder> {

    private List<ResultAnswerModelData> dataModelList;
    ResultAnswerModelData dataModel;
    Context context;

    public QuizAnswer_adapter(List<ResultAnswerModelData> dataModelList, Context ctx) {
        this.dataModelList = dataModelList;
        context = ctx;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AnswerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.answer_item, parent, false);

        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        dataModel = dataModelList.get(position);
        holder.bind(dataModel);
        holder.itemRowBinding.setModel(dataModel);
        // holder.itemRowBinding.setItemClickListener(this);

    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public AnswerItemBinding itemRowBinding;

        public ViewHolder(AnswerItemBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(com.info.hunar.BR.model, obj);
            itemRowBinding.executePendingBindings();
        }
    }


}
