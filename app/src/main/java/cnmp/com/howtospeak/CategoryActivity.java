package cnmp.com.howtospeak;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

import cnmp.com.howtospeak.adapter.RecyclerViewVideoAdapter;
import cnmp.com.howtospeak.model.Category;
import cnmp.com.howtospeak.model.VideoModel;
import cnmp.com.howtospeak.network.GetAPI;

/**
 * Created by hinh1 on 12/24/2017.
 */

public class CategoryActivity extends AppCompatActivity {

    public static ArrayList<VideoModel> listVideos = new ArrayList<>();
    private RecyclerView recyclerVideos;
    private RecyclerViewVideoAdapter viewVideoAdapter;
    private TextView txtNotify;
    private Category category=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        category=(Category) intent.getSerializableExtra("category");
        //query = intent.getStringExtra("QUERY");

        txtNotify=(TextView) findViewById(R.id.notifyResults);
        recyclerVideos = (RecyclerView) findViewById(R.id.recyclerCategory);
        //showResults();
        viewVideoAdapter = new RecyclerViewVideoAdapter(this, listVideos);
        recyclerVideos.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerVideos.setAdapter(viewVideoAdapter);
        recyclerVideos.setLayoutManager(layoutManager);
        showResults();
    }

    public static ArrayList<VideoModel> getListVideos(){
        return listVideos;
    }
    private void showResults() {
        listVideos.clear();
        if(category.getStatus()==0){
            listVideos.addAll(GetAPI.getListVideoByLevel(category.getId()).getListVideo());
        }
        else {
            listVideos.addAll(GetAPI.getListVideoByCategoryId(category.getId()).getListVideo());
        }
        txtNotify.setText(category.getCategoryName() + " has "+listVideos.size()+" videos");
        //Toast.makeText(this,listVideos.size()+"",Toast.LENGTH_LONG).show();

        viewVideoAdapter.notifyDataSetChanged();
    }
}
