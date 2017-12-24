package cnmp.com.howtospeak;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.view.Gravity;
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


public class PlayVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnFullscreenListener, View.OnClickListener, AdapterView.OnItemClickListener,
    YouTubePlayer.OnInitializedListener{
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

    private View videoBox;
    //private View closeButton;
    private boolean isFullscreen;
    private String videoId;

    private int second;
    private int position;

    private Button btnRepeatSentence;
    private Button btnNextVideo;
    private Button btnPreviousVideo;
    private final double DEFAULT_BUTTON_ALPHA = 0.7;
    private ListView listViewSubtitle;
    private ListSubtitleAdapter listSubtitleAdapter;
    private ArrayList<Subtitle> arrayListSubtitle = new ArrayList<>();
    private ArrayList<VideoModel> listVideos;
    private ArrayList<Long> arrayListTime = new ArrayList<>();
    private YouTubePlayer youTubePlayer;
    private YouTubePlayerView youTubePlayerView;
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
            setVideoId(videoId, 2500);
        }

        btnNextVideo = findViewById(R.id.btn_next_video);
        btnRepeatSentence = findViewById(R.id.btn_repeat_sentence);
        btnPreviousVideo = findViewById(R.id.btn_previous_video);

        listViewSubtitle = findViewById(R.id.listSubtitlte);
        listSubtitleAdapter = new ListSubtitleAdapter(this, R.layout.item_list_subtitle, arrayListSubtitle);
        listViewSubtitle.setAdapter(listSubtitleAdapter);

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
        btnNextVideo.setTag(DEFAULT_BUTTON_ALPHA);
        btnRepeatSentence.setOnClickListener(this);
        btnRepeatSentence.setTag(DEFAULT_BUTTON_ALPHA);
        btnPreviousVideo.setOnClickListener(this);
        btnPreviousVideo.setTag(DEFAULT_BUTTON_ALPHA);

        //layout();
        checkYouTubeApi();

        loadSubtitle(videoId);

        //scroll list view at miliseconds
        scrollListViewByTime(StringUtil.stringToMilis("00:01:35,939"));
        listViewSubtitle.setOnItemClickListener(this);

    }

    private void autoScrollListView() {

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
        //layout();
    }

    @Override
    public void onFullscreen(boolean b) {
        //layout();
    }

   /** private void layout() {
        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
//        closeButton.setVisibility(isPortrait ? View.VISIBLE : View.GONE);
        if (isFullscreen) {
            videoBox.setTranslationY(0);
            setLayoutSize(videoFragment.getView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            setLayoutSizeAndGravity(videoBox, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.TOP | Gravity.LEFT);
        } else if (isPortrait) {

            setLayoutSize(videoFragment.getView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            setLayoutSizeAndGravity(videoBox, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.TOP);
        } else {
            videoBox.setTranslationY(0); // Reset any translation that was applied in portrait.
            int screenWidth = dpToPx(getResources().getConfiguration().screenWidthDp);

            //int videoWidth = screenWidth - screenWidth / 4 - dpToPx(LANDSCAPE_VIDEO_PADDING_DP);
            setLayoutSize(videoFragment.getView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            setLayoutSizeAndGravity(videoBox, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        }
    }*/

    //    public void onClickClose(@SuppressWarnings("unused") View view){
//        videoFragment.pause();
//        ViewPropertyAnimator animator = videoBox.animate()
//                .translationYBy(videoBox.getHeight())
//                .setDuration(ANIMATION_DURATION_MILLIS);
//        runOnAnimationEnd(animator, new Runnable() {
//            @Override
//            public void run() {
//                videoBox.setVisibility(View.INVISIBLE);
//            }
//        });
//        Intent intent = new Intent(this, WatchFragment.class);
//        view.getContext().startActivity(intent);
//    }
//    @TargetApi(16)
//    private void  runOnAnimationEnd(ViewPropertyAnimator animator, final Runnable runnable){
//        if (Build.VERSION.SDK_INT >= 16) {
//            animator.withEndAction(runnable);
//        } else {
//            animator.setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    runnable.run();
//                }
//            });
//        }
//    }
    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density + 0.5f);
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

    private static final int parseInt(String intString, int defaultValue) {
        try {
            return intString != null ? Integer.valueOf(intString) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private String formatTime(int millis) {
        int seconds = millis / 1000;
        int minutes = seconds / 60;
        int hours = minutes / 60;

        return (hours == 0 ? "" : hours + ":")
                + String.format("%02d:%02d", minutes % 60, seconds % 60);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        double tag = (double) view.getTag();
        switch (id) {
            case R.id.btn_repeat_sentence:
                if (tag == DEFAULT_BUTTON_ALPHA) {
                    tag = 1;
                } else {
                    tag = DEFAULT_BUTTON_ALPHA;
                }
                break;
            case R.id.btn_previous_video:
                if (position >= listVideos.size()) {
                    Toast toast = Toast.makeText(view.getContext(), "Bạn đã play video cuối cùng trong list video", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    position += 1;
                    setVideoId(listVideos.get(position).getId(), second);
                }

                break;
            case R.id.btn_next_video:

                break;
        }
        view.setTag(tag);
        view.setAlpha((float) tag);
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
//       youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
//        youTubePlayer.setPlaybackEventListener(playBackEventListener);
        this.youTubePlayer = youTubePlayer;
        youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        youTubePlayer.setOnFullscreenListener((PlayVideoActivity) this);
        if (!b && videoId != null) {
            youTubePlayer.loadVideo(videoId, second);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        this.youTubePlayer = null;
    }
}
