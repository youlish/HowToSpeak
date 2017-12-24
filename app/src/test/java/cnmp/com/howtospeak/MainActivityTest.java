package cnmp.com.howtospeak;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.view.View;

import com.ss.bottomnavigation.BottomNavigation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by henry on 12/25/2017.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest extends ApplicationTestCase<Application> {

    MainActivity mainActivity;

    public MainActivityTest(Class<Application> applicationClass) {
        super(applicationClass);
    }


    @Before
    public void setUp() throws Exception {
        mainActivity = Robolectric.setupActivity(MainActivity.class);

    }

    @Test
    public void testMainActivity() {
        Assert.assertNotNull(mainActivity);
    }

    @Test
    public void testTabBar() throws Exception {

        View viewById = mainActivity.findViewById(R.id.bottom_navigation);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(BottomNavigation.class));
        BottomNavigation bottomNavigation = (BottomNavigation) viewById;

        assertEquals(bottomNavigation.getChildCount(), "4");
        assertTrue(bottomNavigation.getChildAt(0).getId() == R.id.tab_watch);
    }


}
