package cnmp.com.howtospeak.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cnmp.com.howtospeak.R;
import cnmp.com.howtospeak.adapter.RecyclerViewCategoryAdapter;
import cnmp.com.howtospeak.adapter.RecyclerViewVideoApdater;
import cnmp.com.howtospeak.model.Category;
import cnmp.com.howtospeak.model.Video;

public class WatchFragment extends Fragment {
    private ArrayList<Video> listFeaturedVideos = new ArrayList<>();
    private RecyclerView recyclerViewFeaturedVideos;
    private RecyclerViewVideoApdater featuredVideosAdapter;

    private ArrayList<Video> listPopularVideos = new ArrayList<>();
    private RecyclerView recyclerPopularVideos;
    private RecyclerViewVideoApdater popularVideoAdapter;

    private ArrayList<Video> listRecentlyVideos = new ArrayList<>();
    private RecyclerView recyclerRecentlyVideos;
    private RecyclerViewVideoApdater recentlyVideoAdapter;

    private ArrayList<Category> listCategory = new ArrayList<>();
    private RecyclerView recyclerCategory;
    private RecyclerViewCategoryAdapter categoryAdapter;


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
        featuredVideosAdapter = new RecyclerViewVideoApdater(listFeaturedVideos);

        recyclerPopularVideos = contentView.findViewById(R.id.recyclerPopularVideos);
        recyclerPopularVideos.setHasFixedSize(true);
        popularVideoAdapter = new RecyclerViewVideoApdater(listFeaturedVideos);

        recyclerRecentlyVideos = contentView.findViewById(R.id.recyclerRecentlyVideos);
        recyclerRecentlyVideos.setHasFixedSize(true);
        recentlyVideoAdapter = new RecyclerViewVideoApdater(listFeaturedVideos);

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

        return contentView;
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
        listFeaturedVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listFeaturedVideos.add(new Video("The 5 resons why you should visit VietNam", "INT", R.mipmap.ic_launcher));
        listFeaturedVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listFeaturedVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listFeaturedVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listFeaturedVideos.add(new Video("The 5 resons why you should visit VietNam", "INT", R.mipmap.ic_launcher));
        listFeaturedVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listFeaturedVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listFeaturedVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listFeaturedVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));

        featuredVideosAdapter.notifyDataSetChanged();
    }

    private void initPopularVideos() {
        listPopularVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listPopularVideos.add(new Video("The 5 resons why you should visit VietNam", "INT", R.mipmap.ic_launcher));
        listPopularVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listPopularVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listPopularVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listPopularVideos.add(new Video("The 5 resons why you should visit VietNam", "INT", R.mipmap.ic_launcher));
        listPopularVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listPopularVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listPopularVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listPopularVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));

        popularVideoAdapter.notifyDataSetChanged();
    }

    private void initRecentlyVideos() {
        listRecentlyVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listRecentlyVideos.add(new Video("The 5 resons why you should visit VietNam", "INT", R.mipmap.ic_launcher));
        listRecentlyVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listRecentlyVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listRecentlyVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listRecentlyVideos.add(new Video("The 5 resons why you should visit VietNam", "INT", R.mipmap.ic_launcher));
        listRecentlyVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listRecentlyVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listRecentlyVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));
        listRecentlyVideos.add(new Video("The 5 resons why you should visit VietNam", "BEG", R.mipmap.ic_launcher));

        recentlyVideoAdapter.notifyDataSetChanged();
    }


}
