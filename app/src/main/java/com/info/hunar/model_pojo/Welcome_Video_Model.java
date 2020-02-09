package com.info.hunar.model_pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Welcome_Video_Model {

    @SerializedName("responce")
    @Expose
    private Boolean responce;

    @SerializedName("data")
    @Expose
    private List<Welcome_Video_Data> data;

    public Welcome_Video_Model(Boolean responce, List<Welcome_Video_Data> data) {
        this.responce = responce;
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public List<Welcome_Video_Data> getData() {
        return data;
    }

    public void setData(List<Welcome_Video_Data> data) {
        this.data = data;
    }
}

