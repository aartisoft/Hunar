package com.info.hunar.model_pojo.subcategory_course_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.info.hunar.adapter.expend_recycler.ParentListItem;


import java.util.List;

/**
 * Created by Raghvendra Sahu on 18-Jan-20.
 */
public class CourseData implements ParentListItem {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("p_cat_id")
    @Expose
    private String pCatId;
    @SerializedName("subcat_id")
    @Expose
    private String subcatId;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("mrp")
    @Expose
    private String mrp;
    @SerializedName("course_amount")
    @Expose
    private String courseAmount;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("video")
    @Expose
    private List<CourseDataVideo> video = null;

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

    public String getSubcatId() {
        return subcatId;
    }

    public void setSubcatId(String subcatId) {
        this.subcatId = subcatId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getCourseAmount() {
        return courseAmount;
    }

    public void setCourseAmount(String courseAmount) {
        this.courseAmount = courseAmount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CourseDataVideo> getVideo() {
        return video;
    }

    public void setVideo(List<CourseDataVideo> video) {
        this.video = video;
    }

    @Override
    public List<?> getChildItemList() {
        return video;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
