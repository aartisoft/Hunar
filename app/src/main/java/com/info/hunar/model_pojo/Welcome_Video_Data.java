package com.info.hunar.model_pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Welcome_Video_Data {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("video")
    @Expose
    private String video;

    public Welcome_Video_Data(String id, String video) {
        this.id = id;
        this.video = video;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
