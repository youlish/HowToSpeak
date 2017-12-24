package cnmp.com.howtospeak.network;

import cnmp.com.howtospeak.model.responses.ListCategory;
import cnmp.com.howtospeak.model.responses.ListSubtitles;
import cnmp.com.howtospeak.model.responses.ListVideo;
import cnmp.com.howtospeak.model.responses.ListVideoSub;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APISever {
    ///videos/category?categoryId=24
    @GET("/videos/category")
    Call<ListVideo> getListVideoByCategoryId(@Query("categoryId") int categoryId);

    // /videos/level?level=3
    @GET("/videos/level")
    Call<ListVideo> getListVideoByLevel(@Query("level") int level);

    // /caption/video?videoId=FgVmAq22HAM
    @GET("/caption/video")
    Call<ListSubtitles> getListSubtitleByVideoId(@Query("videoId") String videoId);

    // /caption/subtitle?text=and
    @GET("/caption/subtitle")
    Call<ListVideoSub> getListVideoSubByText(@Query("text") String text);

    // /videocategories
    @GET("/videocategories")
    Call<ListCategory> getListCategory();

}
