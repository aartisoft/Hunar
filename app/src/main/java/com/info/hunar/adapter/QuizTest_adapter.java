package com.info.hunar.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.info.hunar.R;
import com.info.hunar.binding_interface.TestQuizClickListener;
import com.info.hunar.databinding.QuizItemBinding;
import com.info.hunar.model_pojo.quiz_test_model.QuizTestModelDataQuiz;
import com.info.hunar.model_pojo.quiz_test_model.SelectedQuizData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raghvendra Sahu on 09-Feb-20.
 */
public class QuizTest_adapter extends RecyclerView.Adapter<QuizTest_adapter.ViewHolder>  {

    private List<QuizTestModelDataQuiz> dataModelList;
    Context context;
    String id="", option="";

   public static List<SelectedQuizData>selectedQuizListData=new ArrayList<>();

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
        // holder.itemRowBinding.setItemClickListener(this);

        if(dataModel.getSelectedId() == R.id.radio_one){
            id=dataModel.getId();
            option=dataModel.getOptionOne();
            selectedQuizListData.add(new SelectedQuizData(id,option));
        }
        else if (dataModel.getSelectedId() == R.id.radio_two){
            id=dataModel.getId();
            option=dataModel.getOptionTwo();
            selectedQuizListData.add(new SelectedQuizData(id,option));

        }else if (dataModel.getSelectedId() == R.id.radio_three){
            id=dataModel.getId();
            option=dataModel.getOptionThree();
            selectedQuizListData.add(new SelectedQuizData(id,option));

        }else if(dataModel.getSelectedId() == R.id.radio_four){
            id=dataModel.getId();
            option=dataModel.getOptionFour();
            selectedQuizListData.add(new SelectedQuizData(id,option));

        }



        Log.e("sele_quiz_data",""+selectedQuizListData);

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




}
