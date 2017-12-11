package cnmp.com.howtospeak.model;

/**
 * Created by henry on 12/9/2017.
 */

public class Video {

    private String videoTitle;
    private String level;
    private int resImage;

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getResImage() {
        return resImage;
    }

    public void setResImage(int resImage) {
        this.resImage = resImage;
    }

    public Video() {

    }

    public Video(String videoTitle, String level, int resImage) {

        this.videoTitle = videoTitle;
        this.level = level;
        this.resImage = resImage;
    }
}
