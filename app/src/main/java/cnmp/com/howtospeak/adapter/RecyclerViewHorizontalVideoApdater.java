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
import cnmp.com.howtospeak.model.DeveloperKey;
import cnmp.com.howtospeak.model.VideoModel;
import cnmp.com.howtospeak.views.ViewHolderVideo;

/**
 * Created by henry on 12/9/2017.
 */

public class RecyclerViewHorizontalVideoApdater extends RecyclerView.Adapter<ViewHolderVideo> {
    private ArrayList<VideoModel> list;
    private List<View> entryView;
    private Map<YouTubeThumbnailView, YouTubeThumbnailLoader> thumbnailViewToLoaderMap;
    private LayoutInflater inflater;
    private ThumbnailListener thumbnailListener;
    private boolean labelsVisible;
    private Context context;

    public RecyclerViewHorizontalVideoApdater(Context context, ArrayList<VideoModel> data) {
        this.list = data;
        this.context = context;
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
                .inflate(R.layout.item_list_horizontal_videos, parent, false);
        ViewHolderVideo holder = new ViewHolderVideo(view);

        return holder;
    }

    private final int BEGIN_LEVEL = 1;
    private final int INTERMEDIATE_LEVEL = 2;
    private final int ADVANCED_LEVEL = 3;

    @Override
    public void onBindViewHolder(ViewHolderVideo holder, int position) {
        VideoModel video = list.get(position);

        int level = video.getLevel();
        switch (level) {
            case BEGIN_LEVEL:
                holder.txtLevel.setText("BEG");
                holder.linearLayout.setBackground(context.getDrawable(R.drawable.background_level_blue));
                break;
            case INTERMEDIATE_LEVEL:
                holder.linearLayout.setBackground(context.getDrawable(R.drawable.background_level_yellow));
                holder.txtLevel.setText("INT");
                break;
            case ADVANCED_LEVEL:
                holder.linearLayout.setBackground(context.getDrawable(R.drawable.background_level_red));
                holder.txtLevel.setText("ADV");
                break;
        }
        YouTubeThumbnailLoader loader = thumbnailViewToLoaderMap.get(holder.thumbai);
        holder.thumbai.initialize(DeveloperKey.DEVELOPER_KEY, thumbnailListener);
        if (loader == null) {
            holder.thumbai.setTag(video.getId());
        } else {
            holder.thumbai.setTag(video.getId());
            holder.thumbai.setImageResource(R.drawable.loading_thumbnail);
            loader.setVideo(video.getId());

        }
        holder.txtVideoTitle.setText(video.getTitle());
        holder.txtVideoTitle.setVisibility(labelsVisible ? View.VISIBLE : View.GONE);
        holder.setVideoID(video.getId());

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
