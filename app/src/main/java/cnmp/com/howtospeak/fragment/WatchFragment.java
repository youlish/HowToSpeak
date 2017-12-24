package cnmp.com.howtospeak.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import cnmp.com.howtospeak.R;
import cnmp.com.howtospeak.ResultsSearchActivity;
import cnmp.com.howtospeak.adapter.RecyclerViewCategoryAdapter;
import cnmp.com.howtospeak.adapter.RecyclerViewHorizontalVideoApdater;
import cnmp.com.howtospeak.model.Category;
import cnmp.com.howtospeak.model.VideoModel;

public class WatchFragment extends Fragment implements SearchView.OnQueryTextListener {
    private ArrayList<VideoModel> listFeaturedVideos = new ArrayList<>();
    private RecyclerView recyclerViewFeaturedVideos;
    private RecyclerViewHorizontalVideoApdater featuredVideosAdapter;

    private ArrayList<VideoModel> listPopularVideos = new ArrayList<>();
    private RecyclerView recyclerPopularVideos;
    private RecyclerViewHorizontalVideoApdater popularVideoAdapter;

    private ArrayList<VideoModel> listRecentlyVideos = new ArrayList<>();
    private RecyclerView recyclerRecentlyVideos;
    private RecyclerViewHorizontalVideoApdater recentlyVideoAdapter;

    private ArrayList<Category> listCategory = new ArrayList<>();
    private RecyclerView recyclerCategory;
    private RecyclerViewCategoryAdapter categoryAdapter;

    private SearchView searchView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_watch, container, false);
        getActivity().setTitle(R.string.watch);

        recyclerViewFeaturedVideos = contentView.findViewById(R.id.recyclerFeaturedVideo);
        recyclerViewFeaturedVideos.setHasFixedSize(true);
        featuredVideosAdapter = new RecyclerViewHorizontalVideoApdater(getContext(), listFeaturedVideos);

        recyclerPopularVideos = contentView.findViewById(R.id.recyclerPopularVideos);
        recyclerPopularVideos.setHasFixedSize(true);
        popularVideoAdapter = new RecyclerViewHorizontalVideoApdater(getContext(), listFeaturedVideos);

        recyclerRecentlyVideos = contentView.findViewById(R.id.recyclerRecentlyVideos);
        recyclerRecentlyVideos.setHasFixedSize(true);
        recentlyVideoAdapter = new RecyclerViewHorizontalVideoApdater(getContext(), listFeaturedVideos);

        recyclerCategory = contentView.findViewById(R.id.reyclerCategory);
        recyclerCategory.setHasFixedSize(true);
        categoryAdapter = new RecyclerViewCategoryAdapter(listCategory);


        //set up list featured videos
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewFeaturedVideos.setAdapter(featuredVideosAdapter);
        recyclerViewFeaturedVideos.setLayoutManager(layoutManager);
        initFeaturedVideos();

        //set up list popular videos
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerPopularVideos.setAdapter(popularVideoAdapter);
        recyclerPopularVideos.setLayoutManager(layoutManager);
        initPopularVideos();

        //set up list recently videos
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerRecentlyVideos.setAdapter(popularVideoAdapter);
        recyclerRecentlyVideos.setLayoutManager(layoutManager);
        initRecentlyVideos();

        //set up list category
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerCategory.setAdapter(categoryAdapter);
        recyclerCategory.setLayoutManager(layoutManager);
        initCategory();

        setHasOptionsMenu(true);

        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initCategory() {
        listCategory.add(new Category("Basic Level", R.mipmap.ic_launcher));
        listCategory.add(new Category("Intermediate", R.mipmap.ic_launcher));
        listCategory.add(new Category("Advanced", R.mipmap.ic_launcher));
        listCategory.add(new Category("TED Talks", R.mipmap.ic_launcher));
        listCategory.add(new Category("TED-Ed", R.mipmap.ic_launcher));
        listCategory.add(new Category("CNN", R.mipmap.ic_launcher));
        listCategory.add(new Category("BBC", R.mipmap.ic_launcher));

        categoryAdapter.notifyDataSetChanged();
    }

    private void initFeaturedVideos() {
        listFeaturedVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 1, "XjYKsWAlR3M"));
        listFeaturedVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 2, "XjYKsWAlR3M"));
        listFeaturedVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 3, "XjYKsWAlR3M"));
        listFeaturedVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 1, "XjYKsWAlR3M"));
        listFeaturedVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 2, "XjYKsWAlR3M"));
        listFeaturedVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 3, "XjYKsWAlR3M"));
        listFeaturedVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 1, "XjYKsWAlR3M"));

        featuredVideosAdapter.notifyDataSetChanged();
    }

    private void initPopularVideos() {

        listPopularVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 1, "XjYKsWAlR3M"));
        listPopularVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 2, "XjYKsWAlR3M"));
        listPopularVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 3, "XjYKsWAlR3M"));
        listPopularVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 1, "XjYKsWAlR3M"));
        listPopularVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 2, "XjYKsWAlR3M"));
        listPopularVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 3, "XjYKsWAlR3M"));
        listPopularVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 1, "XjYKsWAlR3M"));

        popularVideoAdapter.notifyDataSetChanged();
    }

    private void initRecentlyVideos() {

        listRecentlyVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 1, "XjYKsWAlR3M"));
        listRecentlyVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 2, "XjYKsWAlR3M"));
        listRecentlyVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 3, "XjYKsWAlR3M"));
        listRecentlyVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 1, "XjYKsWAlR3M"));
        listRecentlyVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 2, "XjYKsWAlR3M"));
        listRecentlyVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 3, "XjYKsWAlR3M"));
        listRecentlyVideos.add(new VideoModel("The 5 resons why you should visit VietNam", 1, "XjYKsWAlR3M"));
        recentlyVideoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.search_view);
        searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
        EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(Color.BLACK);
        searchEditText.setHintTextColor(Color.BLACK);
        searchEditText.setHint(R.string.search);

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent = new Intent(getContext(), ResultsSearchActivity.class);
        intent.putExtra("QUERY", query);
        startActivity(intent);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
