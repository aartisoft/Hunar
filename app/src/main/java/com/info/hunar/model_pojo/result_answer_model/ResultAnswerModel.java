package com.info.hunar.model_pojo.result_answer_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Raghvendra Sahu on 18-Feb-20.
 */
public class ResultAnswerModel {
    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("data")
    @Expose
    private List<ResultAnswerModelData> data = null;

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public List<ResultAnswerModelData> getData() {
        return data;
    }

    public void setData(List<ResultAnswerModelData> data) {
        this.data = data;
    }
}
