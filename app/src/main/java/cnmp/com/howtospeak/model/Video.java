package cnmp.com.howtospeak.model;

/**
 * Created by henry on 12/9/2017.
 */

public class Video {

    private String videoTitle;
    private String level;
    private String videoId;

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

    public String getVideoId(){return videoId;}

    public void setVideoId(String videoId){this.videoId = videoId;}
    public Video() {

    }

    public Video(String videoTitle, String level, String videoId) {

        this.videoTitle = videoTitle;
        this.level = level;
        this.videoId = videoId;
    }
}
