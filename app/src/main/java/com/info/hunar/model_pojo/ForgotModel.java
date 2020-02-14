package com.info.hunar.model_pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raghvendra Sahu on 14-Feb-20.
 */
public class ForgotModel {
    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("error_msg")
    @Expose
    private String error_msg;

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
