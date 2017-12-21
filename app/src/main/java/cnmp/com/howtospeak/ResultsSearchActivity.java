package cnmp.com.howtospeak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import cnmp.com.howtospeak.adapter.RecyclerViewVideoAdapter;
import cnmp.com.howtospeak.model.Video;

public class ResultsSearchActivity extends AppCompatActivity {

    private String query;
    private ArrayList<Video> listVideos = new ArrayList<>();
    private RecyclerView recyclerVideos;
    private RecyclerViewVideoAdapter viewVideoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_search);
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

    private void showResults(String query) {
        listVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", "Y_UmWdcTrrc"));
        listVideos.add(new Video("The 5 resons why you should visit VietNam", "INT", "1KhZKNZO8mQ"));
        listVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", "UiLSiqyDf4Y"));
        listVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", "re0VRK6ouwI"));
        listVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", "blB_X38YSxQ"));
        listVideos.add(new Video("The 5 resons why you should visit VietNam", "INT", "Bu927_ul_X0"));
        listVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", "3I24bSteJpw"));

        viewVideoAdapter.notifyDataSetChanged();
    }
}
