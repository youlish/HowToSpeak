package cnmp.com.howtospeak.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import cnmp.com.howtospeak.PlayVideoActivity;
import cnmp.com.howtospeak.model.DeveloperKey;

/**
 * Created by Dung on 12/14/2017.
 */

public class VideoFragment extends YouTubePlayerFragment implements YouTubePlayer.OnInitializedListener {
    public static YouTubePlayer player;
    private String videoId;
    private int timeStart;

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initialize(DeveloperKey.DEVELOPER_KEY, this);

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
        }
    }

    public void setVideoId(String videoId, int timeStart) {
        if (videoId != null && !videoId.equals(this.videoId)) {
            this.videoId = videoId;
            this.timeStart = timeStart;
            if (player != null) {
                player.loadVideo(videoId, timeStart);
            }

        }
    }

    public void pause() {
        if (player != null) {
            player.pause();
        }
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        this.player = youTubePlayer;
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        player.setOnFullscreenListener((PlayVideoActivity) getActivity());
        if (!b && videoId != null) {
            player.loadVideo(videoId, timeStart);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        this.player = null;
    }

}
