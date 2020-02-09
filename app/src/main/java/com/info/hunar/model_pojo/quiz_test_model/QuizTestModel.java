package com.info.hunar.model_pojo.quiz_test_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raghvendra Sahu on 09-Feb-20.
 */
public class QuizTestModel {

    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("data")
    @Expose
    private QuizTestModelData data;

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public QuizTestModelData getData() {
        return data;
    }

    public void setData(QuizTestModelData data) {
        this.data = data;
    }


}
