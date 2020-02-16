package com.info.hunar.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
public class QuizTest_adapter extends RecyclerView.Adapter<QuizTest_adapter.ViewHolder> {

    private List<QuizTestModelDataQuiz> dataModelList;
   QuizTestModelDataQuiz dataModel;
    Context context;
    private RadioGroup lastCheckedRadioGroup = null;
    String id = "", option = "";

    public List<SelectedQuizData> selectedQuizListData = new ArrayList<>();
    public static ArrayList<String> selectedAnswers = new ArrayList<>();

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        dataModel = dataModelList.get(position);
        holder.bind(dataModel);
        holder.itemRowBinding.setModel(dataModel);
        // holder.itemRowBinding.setItemClickListener(this);

        holder.itemRowBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int childCount = holder.itemRowBinding.radioGroup.getChildCount();
                for (int x = 0; x < childCount; x++) {
                    RadioButton btn = (RadioButton) holder.itemRowBinding.radioGroup.getChildAt(x);

                    if (btn.getId() == i) {
                        //selectedAnswers.set(i, btn.getText().toString());
                        System.out.println(btn.getText().toString());

                        selectedQuizListData.add(new SelectedQuizData(dataModel.getId(),btn.getText().toString()));

                        Log.e("sele_quiz_data", "" + selectedQuizListData);
                        //Log.e("sele_quiz_data1", "" + selectedAnswers);
                    }
                    }
            }
        });
   


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

    public String getRadioChecked(){
//        int childCount = holder.itemRowBinding.radioGroup.getChildCount();
//        for (int x = 0; x < childCount; x++) {
//            RadioButton btn = (RadioButton) holder.itemRowBinding.radioGroup.getChildAt(x);
//
//            if (btn.getId() == i) {
//                //selectedAnswers.set(i, btn.getText().toString());
//                System.out.println(btn.getText().toString());
//
//            }
//        }


        //*******************************
        for (int i=0; i<dataModelList.size(); i++){

            if (dataModelList.get(i).getSelectedId() == R.id.radio_one) {
                id = dataModelList.get(i).getId();
                option = dataModelList.get(i).getOptionOne();

            } else if (dataModelList.get(i).getSelectedId() == R.id.radio_two) {
                id = dataModelList.get(i).getId();
                option = dataModelList.get(i).getOptionTwo();


            } else if (dataModelList.get(i).getSelectedId() == R.id.radio_three) {
                id = dataModelList.get(i).getId();
                option = dataModelList.get(i).getOptionThree();


            } else if (dataModelList.get(i).getSelectedId() == R.id.radio_four) {
                id = dataModelList.get(i).getId();
                option = dataModelList.get(i).getOptionFour();
            }

            Log.e("ans_radio",""+id+"\n"+option);

        }
        return option;
    }

}
