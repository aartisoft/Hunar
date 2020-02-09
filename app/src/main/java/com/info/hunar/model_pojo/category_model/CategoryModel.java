package com.info.hunar.model_pojo.category_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.info.hunar.model_pojo.category_model.CategoryModelData;

import java.util.List;

/**
 * Created by Raghvendra Sahu on 15-Jan-20.
 */
public class CategoryModel {

    @SerializedName("category")
    @Expose
    private List<CategoryModelData> category = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    @SerializedName("data")
    @Expose
    private List<CategoryModelData> data = null;

    public List<CategoryModelData> getData() {
        return data;
    }

    public void setData(List<CategoryModelData> data) {
        this.data = data;
    }

    public List<CategoryModelData> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryModelData> category) {
        this.category = category;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
