package cnmp.com.howtospeak.model.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import cnmp.com.howtospeak.model.Subtitle;
import cnmp.com.howtospeak.model.VideoModel;

/**
 * Created by hinh1 on 12/22/2017.
 */

public class VideoSubItem {
    @SerializedName("sub")
    @Expose
    private Subtitle sub;
    @SerializedName("video")
    @Expose
    private VideoModel video;

    public Subtitle getSub() {
        return sub;
    }

    public void setSub(Subtitle sub) {
        this.sub = sub;
    }

    public VideoModel getVideo() {
        return video;
    }

    public void setVideo(VideoModel video) {
        this.video = video;
    }
}
