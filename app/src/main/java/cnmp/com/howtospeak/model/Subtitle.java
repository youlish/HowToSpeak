package cnmp.com.howtospeak.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hinh1 on 12/22/2017.
 */

public class Subtitle {
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("num")
    @Expose
    private Long num;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("videiId")
    @Expose
    private String videiId;

    private Boolean isPlaying;

    public Boolean getPlaying() {
        return isPlaying;
    }

    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getVideiId() {
        return videiId;
    }

    public void setVideiId(String videiId) {
        this.videiId = videiId;
    }
}
