package com.info.hunar.model_pojo.registration_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raghvendra Sahu on 14-Feb-20.
 */
public class RegistrationModel {
    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("data")
    @Expose
    private RegistrationModelData data;

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public RegistrationModelData getData() {
        return data;
    }

    public void setData(RegistrationModelData data) {
        this.data = data;
    }

}
