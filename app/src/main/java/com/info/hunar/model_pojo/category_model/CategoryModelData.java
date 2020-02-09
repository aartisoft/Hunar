package com.info.hunar.model_pojo.category_model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.info.hunar.Api_Url.Base_Url;

/**
 * Created by Raghvendra Sahu on 15-Jan-20.
 */
public class CategoryModelData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("p_cat_id")
    @Expose
    private String pCatId;
    @SerializedName("cat_name")
    @Expose
    private String catName;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("image")
    @Expose
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPCatId() {
        return pCatId;
    }

    public void setPCatId(String pCatId) {
        this.pCatId = pCatId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getPostUrl() {
        // The URL will usually come from a model (i.e Profile)
        return Base_Url.CategoryImage_URL+image;
    }

    @BindingAdapter("postImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                // .placeholder()
                .apply(new RequestOptions())
                .into(view);
    }

}
