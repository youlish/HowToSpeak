package cnmp.com.howtospeak.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hinh1 on 12/22/2017.
 */

public class VideoModel {
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("channelId")
    @Expose
    private String channelId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("title")
    @Expose
    private String title;

    public VideoModel(String title, Integer level, String id) {
        this.id = id;
        this.level = level;
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
