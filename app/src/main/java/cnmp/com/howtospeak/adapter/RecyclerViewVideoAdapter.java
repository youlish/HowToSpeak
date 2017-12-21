package cnmp.com.howtospeak.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cnmp.com.howtospeak.R;
import cnmp.com.howtospeak.model.Video;
import cnmp.com.howtospeak.views.ViewHolderVideo;

/**
 * Created by henry on 12/21/2017.
 */

public class RecyclerViewVideoAdapter extends RecyclerView.Adapter<ViewHolderVideo> {
    private ArrayList<Video> list;
    private List<View> entryView;
    private Map<YouTubeThumbnailView, YouTubeThumbnailLoader> thumbnailViewToLoaderMap;
    private LayoutInflater inflater;
    private ThumbnailListener thumbnailListener;
    private boolean labelsVisible;

    public RecyclerViewVideoAdapter(Context context, ArrayList<Video> data) {
        this.list = data;
        entryView = new ArrayList<View>();
        thumbnailViewToLoaderMap = new HashMap<YouTubeThumbnailView, YouTubeThumbnailLoader>();
        inflater = LayoutInflater.from(context);
        thumbnailListener = new ThumbnailListener();
        labelsVisible = true;
    }

    public void releaseLoaders() {
        for (YouTubeThumbnailLoader loader : thumbnailViewToLoaderMap.values()) {
            loader.release();
        }
    }

    public void setLabelVisibility(boolean visible) {
        labelsVisible = visible;
        for (View view : entryView) {
            view.findViewById(R.id.txt_video_title).setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public ViewHolderVideo onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_videos, parent, false);
        ViewHolderVideo holder = new ViewHolderVideo(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderVideo holder, int position) {
        Video video = list.get(position);
        holder.txtLevel.setText(video.getLevel());

        YouTubeThumbnailLoader loader = thumbnailViewToLoaderMap.get(holder.thumbai);
        if (loader == null) {
            holder.thumbai.setTag(video.getVideoId());
        } else {
            holder.thumbai.setImageResource(R.drawable.loading_thumbnail);
            loader.setVideo(video.getVideoId());
        }
        holder.txtVideoTitle.setText(video.getVideoTitle());
        holder.txtVideoTitle.setVisibility(labelsVisible ? View.VISIBLE : View.GONE);
        holder.setVideoID(video.getVideoId());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private final class ThumbnailListener implements YouTubeThumbnailView.OnInitializedListener,
            YouTubeThumbnailLoader.OnThumbnailLoadedListener {

        @Override
        public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {

        }

        @Override
        public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
            youTubeThumbnailView.setImageResource(R.drawable.no_thumbnail);
        }

        @Override
        public void onInitializationSuccess(YouTubeThumbnailView view, YouTubeThumbnailLoader loader) {
            loader.setOnThumbnailLoadedListener(this);
            thumbnailViewToLoaderMap.put(view, loader);
            view.setImageResource(R.drawable.loading_thumbnail);
            String videoId = (String) view.getTag();
            loader.setVideo(videoId);
        }

        @Override
        public void onInitializationFailure(YouTubeThumbnailView view, YouTubeInitializationResult loader) {
            view.setImageResource(R.drawable.no_thumbnail);
        }
    }
}
