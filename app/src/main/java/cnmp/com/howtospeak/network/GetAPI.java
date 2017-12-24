package cnmp.com.howtospeak.network;

import java.io.IOException;

import cnmp.com.howtospeak.app.Application;
import cnmp.com.howtospeak.model.responses.ListCategory;
import cnmp.com.howtospeak.model.responses.ListSubtitles;
import cnmp.com.howtospeak.model.responses.ListVideo;
import cnmp.com.howtospeak.model.responses.ListVideoSub;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by hinh1 on 12/22/2017.
 */

public class GetAPI {
    public static ListVideo getListVideoByCategoryId(int categoryId){
        try{
            Call<ListVideo> call = Application.API.getListVideoByCategoryId(categoryId);
            ListVideo dataResponse = null;
            Response<ListVideo> response = call.execute();
            if(response != null){
                dataResponse = response.body();
            }
            return dataResponse;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ListVideo getListVideoByLevel(int level){
        try{
            Call<ListVideo> call = Application.API.getListVideoByLevel(level);
            ListVideo dataResponse = null;
            Response<ListVideo> response = call.execute();
            if(response != null){
                dataResponse = response.body();
            }
            return dataResponse;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ListSubtitles getListSubtitleByVideoId(String text){
        try{
            Call<ListSubtitles> call = Application.API.getListSubtitleByVideoId(text);
            ListSubtitles dataResponse = null;
            Response<ListSubtitles> response = call.execute();
            if(response != null){
                dataResponse = response.body();
            }
            return dataResponse;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ListVideoSub getListVideoSubByText(String text){
        try{
            Call<ListVideoSub> call = Application.API.getListVideoSubByText(text);
            ListVideoSub dataResponse = null;
            Response<ListVideoSub> response = call.execute();
            if(response != null){
                dataResponse = response.body();
            }
            return dataResponse;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ListCategory getListCategories(){
        try{
            Call<ListCategory> call = Application.API.getListCategory();
            ListCategory dataResponse = null;
            Response<ListCategory> response = call.execute();
            if(response != null){
                dataResponse = response.body();
            }
            return dataResponse;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }


}
