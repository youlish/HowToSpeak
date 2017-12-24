package cnmp.com.howtospeak.network;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cnmp.com.howtospeak.model.Acount;

/**
 * Created by Dung on 12/24/2017.
 */

public class SharedPreferencesManganer {
    public static void saveAcount(Context context, Acount acount){
        SharedPreferences sharedPreferences = context.getSharedPreferences("AcountLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(acount);
        editor.putString("Acount", json);
        editor.commit();
    }
    public static Acount getAcount(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("AcountLogin", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Acount","");
        Type type = new TypeToken<Acount>() {}.getType();
        Acount acount = gson.fromJson(json, type);
        return acount;
    }
}
