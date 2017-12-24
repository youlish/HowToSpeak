package cnmp.com.howtospeak;

import org.junit.Before;
import org.junit.Test;

import cnmp.com.howtospeak.utils.StringUtil;

import static junit.framework.Assert.assertEquals;

/**
 * Created by henry on 12/25/2017.
 */

public class StringUtilsTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void stringToMiliSecondsTest1() throws Exception {
        assertEquals(90500, StringUtil.stringToMilis("00:01:30.500"));
    }

    @Test
    public void stringToMiliSecondsTest2() throws Exception {
        assertEquals(90500, StringUtil.stringToMilis("00:01:30.5"));
    }

    @Test
    public void stringToMiliSecondsTest3() throws Exception {
        assertEquals(90501, StringUtil.stringToMilis("00:01:30.500"));
    }
}
