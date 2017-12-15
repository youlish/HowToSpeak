package cnmp.com.howtospeak.fragment;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import cnmp.com.howtospeak.model.DeveloperKey;
import cnmp.com.howtospeak.views.PlayVideo;

/**
 * Created by Dung on 12/14/2017.
 */

public class VideoFragment extends YouTubePlayerFragment implements YouTubePlayer.OnInitializedListener {
    private YouTubePlayer player;
    private String videoId;

    public static VideoFragment newInstance(){return new VideoFragment();}

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initialize(DeveloperKey.DEVELOPER_KEY,this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(player !=null){
            player.release();
        }
    }
    public void setVideoId(String videoId){
        if(videoId != null && !videoId.equals(this.videoId)){
            this.videoId = videoId;
            if(player != null){
                player.cueVideo(videoId);
            }

        }
    }
    public void pause(){
        if (player != null) {
            player.pause();
        }
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        this.player = youTubePlayer;
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        player.setOnFullscreenListener((PlayVideo) getActivity());
        if(!b && videoId != null){
            player.cueVideo(videoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        this.player = null;
    }
}
