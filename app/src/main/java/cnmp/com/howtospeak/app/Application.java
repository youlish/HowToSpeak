package cnmp.com.howtospeak.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import cnmp.com.howtospeak.network.APISever;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dung on 10/22/2017.
 */

public class Application extends MultiDexApplication {
    public static Retrofit mRetrofit;
    private static Context mContext;
    public static final String BASE_API = "http://b86f7b86.ngrok.io";

    public static APISever API;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API = mRetrofit.create(APISever.class);
    }
    public static final Context getContext() {
        return mContext;
    }



}
