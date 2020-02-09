package com.info.hunar.model_pojo.subcategory_course_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raghvendra Sahu on 18-Jan-20.
 */
public class Sub_course_details_model {
    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("data")
    @Expose
    private Sub_courde_details_modelData data;

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public Sub_courde_details_modelData getData() {
        return data;
    }

    public void setData(Sub_courde_details_modelData data) {
        this.data = data;
    }
}
