package com.info.hunar.model_pojo.result_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raghvendra Sahu on 16-Feb-20.
 */
public class ResultPdfModel {

    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("error_msg")
    @Expose
    private String errorMsg;
 @SerializedName("pdf")
    @Expose
    private String pdf;

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
