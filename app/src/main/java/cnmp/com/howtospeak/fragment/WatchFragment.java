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
import cnmp.com.howtospeak.network.GetAPI;

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

    private ArrayList<Category> listCategoryItem = new ArrayList<>();
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
        popularVideoAdapter = new RecyclerViewHorizontalVideoApdater(getContext(), listPopularVideos);

        recyclerRecentlyVideos = contentView.findViewById(R.id.recyclerRecentlyVideos);
        recyclerRecentlyVideos.setHasFixedSize(true);
        recentlyVideoAdapter = new RecyclerViewHorizontalVideoApdater(getContext(), listRecentlyVideos);

        recyclerCategory = contentView.findViewById(R.id.reyclerCategory);
        recyclerCategory.setHasFixedSize(true);
        categoryAdapter = new RecyclerViewCategoryAdapter(listCategoryItem);


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
        recyclerRecentlyVideos.setAdapter(recentlyVideoAdapter);
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
        listCategoryItem.clear();
        listCategoryItem.add(new Category("Basic Level", 1, 0, R.drawable.img_1));
        listCategoryItem.add(new Category("Intermediate", 2, 0,R.drawable.img_2));
        listCategoryItem.add(new Category("Advanced", 3, 0, R.drawable.img_4));

        ArrayList<Category> categoryArrayList= GetAPI.getListCategories().getListCategory();
        Category category=null;
        for (int i=0;i<categoryArrayList.size();i++){
            category= categoryArrayList.get(i);
            category.setStatus(1);
            category.setResImage(R.drawable.img_5);
            listCategoryItem.add(category);
        }

        categoryAdapter.notifyDataSetChanged();
    }

    private void initFeaturedVideos() {
        listFeaturedVideos.clear();
        listFeaturedVideos.addAll(GetAPI.getListVideoByCategoryId(27).getListVideo());

        featuredVideosAdapter.notifyDataSetChanged();
    }

    private void initPopularVideos() {
        listPopularVideos.clear();
        listPopularVideos.addAll(GetAPI.getListVideoByCategoryId(24).getListVideo());

        popularVideoAdapter.notifyDataSetChanged();
    }

    private void initRecentlyVideos() {
        listRecentlyVideos.clear();
        listRecentlyVideos.addAll(GetAPI.getListVideoByCategoryId(25).getListVideo());
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
