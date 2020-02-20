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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by Raghvendra Sahu on 09-Feb-20.
 */
public class QuizTest_adapter extends RecyclerView.Adapter<QuizTest_adapter.ViewHolder> {

    private List<QuizTestModelDataQuiz> dataModelList;
    QuizTestModelDataQuiz dataModel;
    Context context;
    String id = "", option = "";
    public JSONArray passArray=null;

    public ArrayList<SelectedQuizData> selectedQuizListData = new ArrayList<>();

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        dataModel = dataModelList.get(position);
        holder.bind(dataModel);
        holder.itemRowBinding.setModel(dataModel);
        // holder.itemRowBinding.setItemClickListener(this);
        holder.itemRowBinding.radioGroup.setTag(position);

        holder.setIsRecyclable(false);

        holder.itemRowBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int clickedPos = (Integer) radioGroup.getTag();
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                dataModelList.get(clickedPos).setSelectedId(radioButtonID);

                int childCount = holder.itemRowBinding.radioGroup.getChildCount();
                for (int x = 0; x < childCount; x++) {
                    RadioButton btn = (RadioButton) holder.itemRowBinding.radioGroup.getChildAt(x);

                    if (btn.getId() == i) {
                        System.out.println(btn.getText().toString());
                        option = btn.getText().toString();

                    }
                }

                id = dataModelList.get(position).getId();

                Log.e("ans_radio", "id:" + id + "\n opt:" + option);
                selectedItemArray(position);
            }
        });


    }

    private void selectedItemArray(int position) {
        if (selectedQuizListData != null && !selectedQuizListData.isEmpty()) {

            for (int j = 0; j < selectedQuizListData.size(); j++) {

                if (selectedQuizListData.get(j).getId().equalsIgnoreCase(id)) {

                    selectedQuizListData.remove(j);

                    Log.e("sele_quiz_data11", "" + id + " " +
                            "size " + selectedQuizListData.size());

                    selectedQuizListData.add(new SelectedQuizData(id, option));
                   // Log.e("sele_quiz_data14", "" + selectedQuizListData.size());
                    getSelected_itemArray();

                    break;
                } else {
                    if (j == selectedQuizListData.size() - 1) {
                        selectedQuizListData.add(new SelectedQuizData(id, option));
                        getSelected_itemArray();
                      //  Log.e("sele_quiz_data12", "" + selectedQuizListData.size());
                    }

                }

            }

        } else {
            selectedQuizListData.add(new SelectedQuizData(id, option));
           // Log.e("sele_quiz_data13", "" + selectedQuizListData.size());
            getSelected_itemArray();
        }

        //Log.e("sele_quiz_data", "" + selectedQuizListData);
    }

    private void getSelected_itemArray() {
        passArray = new JSONArray();

        if (selectedQuizListData != null && !selectedQuizListData.isEmpty()) {

            for (int i = 0; i < selectedQuizListData.size(); i++) {

               // Log.e("selected_id", "" + selectedQuizListData.get(i).getId());
               // Log.e("option", "" + selectedQuizListData.get(i).getOption());

                JSONObject jObjP = new JSONObject();

                try {
                    jObjP.put("id", selectedQuizListData.get(i).getId());
                    jObjP.put("option", selectedQuizListData.get(i).getOption());

                    passArray.put(jObjP);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("array_jsaon", "" + passArray.toString());

            }
        } else {
            Log.e("no_item_found", "no_item");
        }
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public String PassArray() {

        if (passArray!=null){
            return passArray.toString();
        }
        return null;
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
