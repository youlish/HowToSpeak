package cnmp.com.howtospeak;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

import cnmp.com.howtospeak.adapter.RecyclerViewVideoAdapter;
import cnmp.com.howtospeak.model.VideoModel;
import cnmp.com.howtospeak.model.responses.VideoSubItem;
import cnmp.com.howtospeak.network.GetAPI;
import cnmp.com.howtospeak.utils.StringUtil;


public class ResultsSearchActivity extends AppCompatActivity {
    private String query;
    public static ArrayList<VideoModel> listVideos = new ArrayList<>();
    private RecyclerView recyclerVideos;
    private RecyclerViewVideoAdapter viewVideoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_search);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        query = intent.getStringExtra("QUERY");


        recyclerVideos = (RecyclerView) findViewById(R.id.recyclerResultsSearch);
        viewVideoAdapter = new RecyclerViewVideoAdapter(this, listVideos);
        recyclerVideos.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerVideos.setAdapter(viewVideoAdapter);
        recyclerVideos.setLayoutManager(layoutManager);
        showResults(query);

    }
    public static ArrayList<VideoModel> getListVideos(){
        return listVideos;
       }
    private void showResults(String query) {
        listVideos.clear();
        ArrayList<VideoSubItem> listVideoSub= GetAPI.getListVideoSubByText(query).getListVideoSub();
        VideoModel videoModel=null;
        for (int i=0; i<listVideoSub.size(); i++){
            videoModel=listVideoSub.get(i).getVideo();
            videoModel.setTimeStart((int) StringUtil.stringToMilis(listVideoSub.get(i).getSub().getStart()));
            listVideos.add(videoModel);
        }
        Toast.makeText(this,listVideos.size()+"",Toast.LENGTH_LONG).show();

        viewVideoAdapter.notifyDataSetChanged();
    }
}
