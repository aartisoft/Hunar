package com.info.hunar.model_pojo.registration_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raghvendra Sahu on 14-Feb-20.
 */
public class RegistrationModelData {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("address")
    @Expose
    private String address;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
