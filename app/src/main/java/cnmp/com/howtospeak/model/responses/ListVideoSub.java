package cnmp.com.howtospeak.model.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hinh1 on 12/22/2017.
 */
// video and item sub
public class ListVideoSub {
    @SerializedName("listVideoSub")
    @Expose
    private ArrayList<VideoSubItem> listVideoSub = null;

    public ArrayList<VideoSubItem> getListVideoSub() {
        return listVideoSub;
    }

    public void setListVideoSub(ArrayList<VideoSubItem> listVideoSub) {
        this.listVideoSub = listVideoSub;
    }
}
