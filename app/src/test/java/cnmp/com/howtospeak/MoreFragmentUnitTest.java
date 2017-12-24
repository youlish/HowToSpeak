package cnmp.com.howtospeak;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.test.ApplicationTestCase;
import android.widget.Button;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import cnmp.com.howtospeak.fragment.MoreFragment;

/**
 * Created by henry on 12/25/2017.
 */


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21)
public class MoreFragmentUnitTest extends ApplicationTestCase<Application> {
    MoreFragment moreFragment;

    MainActivity mainActivity;

    public MoreFragmentUnitTest(Class<Application> applicationClass) {
        super(applicationClass);
    }


    @Before
    public void setUp() throws Exception{
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        moreFragment = new MoreFragment();
        startFragment(moreFragment);
    }
    @Test
    public void testMainActivity() {
        Assert.assertNotNull(mainActivity);
    }

    private void startFragment( Fragment fragment ) {
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null );
        fragmentTransaction.commit();
    }
    @Test
    public void testLoginButton() throws Exception {
        // test
        Button buttonLogin = moreFragment.btnLogin;
        assertEquals(buttonLogin.getText(),"Login now");
        buttonLogin.performClick();
        assertEquals(buttonLogin.getText(),"Logout");
    }


}
