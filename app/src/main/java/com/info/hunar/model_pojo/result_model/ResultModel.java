package com.info.hunar.model_pojo.result_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raghvendra Sahu on 16-Feb-20.
 */
public class ResultModel {
    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("total_score")
    @Expose
    private Integer totalScore;
    @SerializedName("attempt")
    @Expose
    private Integer attempt;
    @SerializedName("not_attempt")
    @Expose
    private Integer notAttempt;
    @SerializedName("wrong")
    @Expose
    private String wrong;
    @SerializedName("right")
    @Expose
    private String right;
    @SerializedName("error_msg")
    @Expose
    private String error_msg;

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    public Integer getNotAttempt() {
        return notAttempt;
    }

    public void setNotAttempt(Integer notAttempt) {
        this.notAttempt = notAttempt;
    }

    public String getWrong() {
        return wrong;
    }

    public void setWrong(String wrong) {
        this.wrong = wrong;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
