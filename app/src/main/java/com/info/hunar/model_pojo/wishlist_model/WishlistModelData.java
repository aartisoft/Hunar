package com.info.hunar.model_pojo.wishlist_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raghvendra Sahu on 19-Feb-20.
 */
public class WishlistModelData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("p_cat_id")
    @Expose
    private String pCatId;
    @SerializedName("subcat_id")
    @Expose
    private String subcatId;
    @SerializedName("video_name")
    @Expose
    private String videoName;
    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("time_duration")
    @Expose
    private String timeDuration;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date")
    @Expose
    private String date;

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

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
