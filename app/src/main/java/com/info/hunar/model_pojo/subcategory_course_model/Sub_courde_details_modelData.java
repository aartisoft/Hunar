package com.info.hunar.model_pojo.subcategory_course_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Raghvendra Sahu on 18-Jan-20.
 */
public class Sub_courde_details_modelData {
    @SerializedName("subcategory")
    @Expose
    private SubcategoryDetails subcategory;
    @SerializedName("course_data")
    @Expose
    private List<CourseData> courseData = null;

    public SubcategoryDetails getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubcategoryDetails subcategory) {
        this.subcategory = subcategory;
    }

    public List<CourseData> getCourseData() {
        return courseData;
    }

    public void setCourseData(List<CourseData> courseData) {
        this.courseData = courseData;
    }
}
