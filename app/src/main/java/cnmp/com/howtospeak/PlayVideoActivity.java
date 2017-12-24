package cnmp.com.howtospeak;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

import cnmp.com.howtospeak.adapter.ListSubtitleAdapter;
import cnmp.com.howtospeak.model.DeveloperKey;
import cnmp.com.howtospeak.model.Subtitle;
import cnmp.com.howtospeak.model.VideoModel;
import cnmp.com.howtospeak.network.GetAPI;
import cnmp.com.howtospeak.utils.StringUtil;


/**
 * Created by Dung on 12/14/2017.
 */


public class PlayVideoActivity extends YouTubeBaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener,
        YouTubePlayer.OnInitializedListener {
    /**
     * Khoảng thời gian hoạt hình trượt lên trong video theo chân dung
     */

    private static final int ANIMATION_DURATION_MILLIS = 300;
    /**
     * Khoảng đệm giữa danh sách video và video theo hướng ngang.
     */
    private static final int LANDSCAPE_VIDEO_PADDING_DP = 5;
    /**
     * Mã yêu cầu khi gọi startActivityForResult để phục hồi từ lỗi dịch vụ API.
     */
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    private MyPlaybackEventListener playbackEventListener;
    private MyPlayerStateChangeListener playerStateChangeListener;


    private View videoBox;
    //private View closeButton;
    private boolean isFullscreen;
    private String videoId;

    private int second;
    private int position;

    private Button btnRepeatSentence;
    private Button btnNextVideo;
    private Button btnPreviousVideo;
    private final float DEFAULT_BUTTON_ALPHA = (float) 0.7;
    private final float DEFAULT_BUTTON_ALPHA_1 = (float) 1.0;
    private ListView listViewSubtitle;
    private ListSubtitleAdapter listSubtitleAdapter;
    private ArrayList<Subtitle> arrayListSubtitle = new ArrayList<>();
    private ArrayList<VideoModel> listVideos;
    private ArrayList<Long> arrayListTime = new ArrayList<>();
    private YouTubePlayer youTubePlayer;
    private YouTubePlayerView youTubePlayerView;
    private MyAsync mAsync;
    private RepeatAsync repeatAsync;
    private boolean isRepeating = false;
    private long startTime;
    private long endTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.playerView);
        youTubePlayerView.initialize(DeveloperKey.DEVELOPER_KEY, this);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("Position");
        listVideos = ResultsSearchActivity.getListVideos();
        if (listVideos.size() == 0) {
            videoId = intent.getExtras().getString("VideoID");
            setVideoId(videoId, 0);
        } else {
            videoId = listVideos.get(position).getId();
            second = listVideos.get(position).getTimeStart();
            setVideoId(videoId, second);
        }

        btnNextVideo = findViewById(R.id.btn_next_video);
        btnRepeatSentence = findViewById(R.id.btn_repeat_sentence);
        btnPreviousVideo = findViewById(R.id.btn_previous_video);

        listViewSubtitle = findViewById(R.id.listSubtitlte);
        listSubtitleAdapter = new ListSubtitleAdapter(this, R.layout.item_list_subtitle, arrayListSubtitle);
        listViewSubtitle.setAdapter(listSubtitleAdapter);

        playerStateChangeListener = new MyPlayerStateChangeListener();
        playbackEventListener = new MyPlaybackEventListener();


        videoBox = findViewById(R.id.video_box);
        //closeButton = findViewById(R.id.close_button);
        videoBox.setVisibility(View.INVISIBLE);

        if (videoBox.getVisibility() != View.VISIBLE) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

                videoBox.setTranslationY(videoBox.getHeight());
            }
            videoBox.setVisibility(View.VISIBLE);
        }
        if (videoBox.getTranslationY() > 0) {
            videoBox.animate().translationY(0).setDuration(ANIMATION_DURATION_MILLIS);
        }

        //setup button control video

        btnNextVideo.setOnClickListener(this);
        btnRepeatSentence.setOnClickListener(this);
        btnPreviousVideo.setOnClickListener(this);
        if (position <= 0) {
            btnPreviousVideo.setAlpha(DEFAULT_BUTTON_ALPHA);
            btnNextVideo.setAlpha(DEFAULT_BUTTON_ALPHA_1);
        } else if (position >= listVideos.size()) {
            btnPreviousVideo.setAlpha(DEFAULT_BUTTON_ALPHA_1);
            btnNextVideo.setAlpha(DEFAULT_BUTTON_ALPHA);
        } else {
            btnPreviousVideo.setAlpha(DEFAULT_BUTTON_ALPHA_1);
            btnNextVideo.setAlpha(DEFAULT_BUTTON_ALPHA_1);
        }


        checkYouTubeApi();

        loadSubtitle(videoId);
        listViewSubtitle.setOnItemClickListener(this);


    }


    private void loadSubtitle(String videoId) {
        arrayListSubtitle = GetAPI.getListSubtitleByVideoId(videoId).getListSub();
        listSubtitleAdapter.refreshData(arrayListSubtitle);
        for (int i = 0; i < arrayListSubtitle.size(); i++) {
            String s = arrayListSubtitle.get(i).getStart();
            arrayListTime.add(i, StringUtil.stringToMilis(s));
        }
    }

    private void scrollListViewByTime(long ms) {
        int position = getPositionByTime(ms);
        for (int i = 0; i < arrayListSubtitle.size(); i++) {
            if (i == position) {
                arrayListSubtitle.get(i).setPlaying(true);
            } else {
                arrayListSubtitle.get(i).setPlaying(false);
            }
        }
        listViewSubtitle.smoothScrollToPosition(position);
        listSubtitleAdapter.refreshData(arrayListSubtitle);
    }

    private void scrollListViewByPosition(int position) {
        for (int i = 0; i < arrayListSubtitle.size(); i++) {
            if (i == position) {
                arrayListSubtitle.get(i).setPlaying(true);
            } else {
                arrayListSubtitle.get(i).setPlaying(false);
            }
        }
        listSubtitleAdapter.refreshData(arrayListSubtitle);
    }

    private int getPositionByTime(long ms) {
        int position = 0;
        for (int i = 0; i < arrayListTime.size(); i++) {
            if (ms >= arrayListTime.get(i)) {
                position = i;
            } else break;
        }
        return position;
    }

    private void checkYouTubeApi() {
        YouTubeInitializationResult errorReason = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this);
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else if (errorReason != YouTubeInitializationResult.SUCCESS) {
            String errorMessage =
                    String.format(getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Tạo lại hoạt động nếu người dùng thực hiện hành động khôi phục
            recreate();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    private static void setLayoutSize(View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    private static void setLayoutSizeAndGravity(View view, int width, int height, int gravity) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        params.gravity = gravity;
        view.setLayoutParams(params);
    }

    private void setRepeatSentence(int position) {
        Log.d("POSITION_CURRENT", String.valueOf(position));

        startTime = StringUtil.stringToMilis(arrayListSubtitle.get(position).getStart());
        endTime = StringUtil.stringToMilis(arrayListSubtitle.get(position).getEnd());
        repeatAsync = new RepeatAsync();
        mAsync.cancel(true);
        repeatAsync.execute();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_repeat_sentence:

                if (isRepeating) {
                    view.setAlpha(DEFAULT_BUTTON_ALPHA);
                    isRepeating = false;
                } else {
                    view.setAlpha(DEFAULT_BUTTON_ALPHA_1);
                    setRepeatSentence(getPositionByTime(youTubePlayer.getCurrentTimeMillis()));
                    isRepeating = true;
                }
                break;
            case R.id.btn_previous_video:
                if (position <= 0) {
                    view.setEnabled(false);
                    view.setAlpha(DEFAULT_BUTTON_ALPHA);

                } else {
                    position -= 1;
                    view.setEnabled(true);
                    second = listVideos.get(position).getTimeStart();
                    setVideoId(listVideos.get(position).getId(), second);
                    view.setAlpha(DEFAULT_BUTTON_ALPHA_1);

                }
                break;
            case R.id.btn_next_video:
                if (position >= listVideos.size()) {
                    view.setEnabled(false);
                    view.setAlpha(DEFAULT_BUTTON_ALPHA);
                } else {
                    position += 1;
                    view.setEnabled(true);
                    second = listVideos.get(position).getTimeStart();
                    setVideoId(listVideos.get(position).getId(), second);
                    view.setAlpha(DEFAULT_BUTTON_ALPHA_1);

                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (youTubePlayer != null) {
            youTubePlayer.release();
        }
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        scrollListViewByPosition(i);
        youTubePlayer.seekToMillis(arrayListTime.get(i).intValue());

    }

    public void setVideoId(String videoId, int timeStart) {
        if (videoId != null && !videoId.equals(this.videoId)) {
            this.videoId = videoId;
            this.second = timeStart;
            if (youTubePlayer != null) {
                youTubePlayer.loadVideo(videoId, timeStart);
            }

        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        this.youTubePlayer = youTubePlayer;
        if (!b && videoId != null) {
            youTubePlayer.loadVideo(videoId, second);
        }

        mAsync = new MyAsync();
        mAsync.execute();

    }

    private class MyAsync extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            do {
                final int ms = youTubePlayer.getCurrentTimeMillis();
                try {
                    if (isCancelled()) break;

                    Thread.sleep(500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //stuff that updates ui
                            scrollListViewByTime(ms);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    private class RepeatAsync extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            do {
                final int ms = youTubePlayer.getCurrentTimeMillis();

                try {
                    if (isCancelled()) break;

                    Thread.sleep(500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //stuff that updates ui
                            if (ms > endTime) {
                                youTubePlayer.seekToMillis((int) startTime);
                            }
                            Log.d("POSITION_CURRENT", String.valueOf(ms));

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (isRepeating);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    private String getTimesText() {
        int currentTimeMillis = youTubePlayer.getCurrentTimeMillis();
        int durationMillis = youTubePlayer.getDurationMillis();
        return String.valueOf(currentTimeMillis);
    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        this.youTubePlayer = null;
    }

    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {
        String playbackState = "NOT_PLAYING";
        String bufferingState = "";

        @Override
        public void onPlaying() {
            playbackState = "PLAYING";
            Log.d("PLAYING ", getTimesText());
            scrollListViewByTime(youTubePlayer.getCurrentTimeMillis());
            if (!mAsync.isCancelled())
                mAsync.cancel(true);
            mAsync = new MyAsync();
            mAsync.execute();
        }

        @Override
        public void onBuffering(boolean isBuffering) {
            bufferingState = isBuffering ? "(BUFFERING)" : "";
        }

        @Override
        public void onStopped() {
            playbackState = "STOPPED";
            if (!mAsync.isCancelled())
                mAsync.cancel(true);

        }

        @Override
        public void onPaused() {
            playbackState = "PAUSED";
            if (!mAsync.isCancelled())
                mAsync.cancel(true);
        }

        @Override
        public void onSeekTo(int endPositionMillis) {
            scrollListViewByTime(endPositionMillis);
        }
    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {
        String playerState = "UNINITIALIZED";

        @Override
        public void onLoading() {
            playerState = "LOADING";
        }

        @Override
        public void onLoaded(String videoId) {
            playerState = String.format("LOADED %s", videoId);
        }

        @Override
        public void onAdStarted() {
            playerState = "AD_STARTED";
        }

        @Override
        public void onVideoStarted() {
            playerState = "VIDEO_STARTED";
        }

        @Override
        public void onVideoEnded() {
            playerState = "VIDEO_ENDED";
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason reason) {
            playerState = "ERROR (" + reason + ")";
            if (reason == YouTubePlayer.ErrorReason.UNEXPECTED_SERVICE_DISCONNECTION) {
                // When this error occurs the player is released and can no longer be used.
                youTubePlayer = null;
            }
        }

    }

}
