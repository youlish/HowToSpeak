package cnmp.com.howtospeak.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import cnmp.com.howtospeak.R;
import cnmp.com.howtospeak.fragment.VideoFragment;
import cnmp.com.howtospeak.fragment.WatchFragment;

/**
 * Created by Dung on 12/14/2017.
 */

public class PlayVideo extends Activity implements YouTubePlayer.OnFullscreenListener {
    /**Khoảng thời gian hoạt hình trượt lên trong video theo chân dung*/
    private static final int ANIMATION_DURATION_MILLIS = 300;
    /**Khoảng đệm giữa danh sách video và video theo hướng ngang.*/
    private static final int LANDSCAPE_VIDEO_PADDING_DP = 5;
    /**Mã yêu cầu khi gọi startActivityForResult để phục hồi từ lỗi dịch vụ API.*/
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    private VideoFragment videoFragment;
    private View videoBox;
    //private View closeButton;
    private boolean isFullscreen;
    private String videoId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_video);
        videoFragment = (VideoFragment) getFragmentManager().findFragmentById(R.id.video_fragment_container);

        videoBox = findViewById(R.id.video_box);
        //closeButton = findViewById(R.id.close_button);
        videoBox.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        videoId = intent.getExtras().getString("VideoID");
        Toast.makeText(this, videoId, Toast.LENGTH_SHORT).show();
        videoFragment.setVideoId(videoId);
        if(videoBox.getVisibility() != View.VISIBLE){
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                videoBox.setTranslationY(videoBox.getHeight());
            }
            videoBox.setVisibility(View.VISIBLE);
        }
        if (videoBox.getTranslationY() > 0) {
            videoBox.animate().translationY(0).setDuration(ANIMATION_DURATION_MILLIS);
        }
        layout();

        checkYouTubeApi();
    }
    private void checkYouTubeApi(){
        YouTubeInitializationResult errorReason = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this);
        if(errorReason .isUserRecoverableError()){
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        }else if(errorReason != YouTubeInitializationResult.SUCCESS){
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
        layout();
    }

    @Override
    public void onFullscreen(boolean b) {
        layout();
    }
    private void layout(){
        boolean isPortrait =  getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
//        closeButton.setVisibility(isPortrait ? View.VISIBLE : View.GONE);
        if(isFullscreen){
            videoBox.setTranslationY(0);
            setLayoutSize(videoFragment.getView(), ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.MATCH_PARENT);
            setLayoutSizeAndGravity(videoBox,  ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.MATCH_PARENT, Gravity.TOP | Gravity.LEFT);
        }else if(isPortrait){

            setLayoutSize(videoFragment.getView(),  ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.WRAP_CONTENT);
            setLayoutSizeAndGravity(videoBox,  ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.TOP);
        }else{
            videoBox.setTranslationY(0); // Reset any translation that was applied in portrait.
            int screenWidth = dpToPx(getResources().getConfiguration().screenWidthDp);

            //int videoWidth = screenWidth - screenWidth / 4 - dpToPx(LANDSCAPE_VIDEO_PADDING_DP);
            setLayoutSize(videoFragment.getView(), ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.WRAP_CONTENT);
            setLayoutSizeAndGravity(videoBox, ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        }
    }
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
}
