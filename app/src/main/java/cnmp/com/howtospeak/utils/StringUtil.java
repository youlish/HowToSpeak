package cnmp.com.howtospeak.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by henry on 12/23/2017.
 */

public class StringUtil {
    private static Pattern pattern = Pattern.compile("(\\d{2}):(\\d{2}):(\\d{2}).(\\d{3})");

    public static long stringToMilis(String s) {
        Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            return Long.parseLong(matcher.group(1)) * 3600000L
                    + Long.parseLong(matcher.group(2)) * 60000
                    + Long.parseLong(matcher.group(3)) * 1000
                    + Long.parseLong(matcher.group(4));
        } else {
            throw new IllegalArgumentException("Invalid format " + s);
        }
    }

}
