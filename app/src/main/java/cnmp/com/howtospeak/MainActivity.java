package cnmp.com.howtospeak;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

import cnmp.com.howtospeak.fragment.LearnFragment;
import cnmp.com.howtospeak.fragment.MoreFragment;
import cnmp.com.howtospeak.fragment.MyPageFragment;
import cnmp.com.howtospeak.fragment.WatchFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigation bottomNavigation;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        bottomNavigation = (BottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.setDefaultItem(0);

        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int itemId) {
                switch (itemId) {
                    case R.id.tab_watch:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_fragment_containers, new WatchFragment());
                        break;
                    case R.id.tab_learn:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_fragment_containers, new LearnFragment());
                        break;
                    case R.id.tab_my_page:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_fragment_containers, new MyPageFragment());
                        break;
                    case R.id.tab_more:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_fragment_containers, new MoreFragment());
                        break;
                }
                transaction.commit();
            }
        });
    }

}
