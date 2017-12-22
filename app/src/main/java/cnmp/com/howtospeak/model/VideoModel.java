package cnmp.com.howtospeak.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hinh1 on 12/22/2017.
 */

public class VideoModel {
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
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
}
