package cnmp.com.howtospeak.model.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import cnmp.com.howtospeak.model.VideoModel;

/**
 * Created by hinh1 on 12/22/2017.
 */

public class ListVideo {
    @SerializedName("listVideo")
    @Expose
    private ArrayList<VideoModel> listVideo = null;

    public ArrayList<VideoModel> getListVideo() {
        return listVideo;
    }
}
