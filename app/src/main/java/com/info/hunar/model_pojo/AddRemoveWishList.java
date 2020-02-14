package com.info.hunar.model_pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raghvendra Sahu on 14-Feb-20.
 */
public class AddRemoveWishList {
    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
