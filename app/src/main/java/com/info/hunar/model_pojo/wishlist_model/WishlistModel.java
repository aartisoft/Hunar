package com.info.hunar.model_pojo.wishlist_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Raghvendra Sahu on 19-Feb-20.
 */
public class WishlistModel {
    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("data")
    @Expose
    private List<WishlistModelData> data = null;

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public List<WishlistModelData> getData() {
        return data;
    }

    public void setData(List<WishlistModelData> data) {
        this.data = data;
    }
}
