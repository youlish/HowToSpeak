package cnmp.com.howtospeak.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dung on 12/22/2017.
 */

public class FormatTime {
    public long formarTime(String s){
        long mili =0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss,SSS");
        Date date = null;
        try {
            date = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mili = date.getTime();

        return mili;
    }
}
