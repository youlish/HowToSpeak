package cnmp.com.howtospeak.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hinh1 on 12/22/2017.
 */

public class ListSubtitles {
    @SerializedName("listSub")
    @Expose
    private ArrayList<Subtitle> listSub = null;

    public ArrayList<Subtitle> getListSub() {
        return listSub;
    }

    public void setListSub(ArrayList<Subtitle> listSub) {
        this.listSub = listSub;
    }
}
