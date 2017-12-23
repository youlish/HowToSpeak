package cnmp.com.howtospeak.views;

import android.content.Intent;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import cnmp.com.howtospeak.R;
import cnmp.com.howtospeak.model.DeveloperKey;

/**
 * Created by Dung on 12/21/2017.
 */

public abstract class YouTubeFailureRecoveryActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if(errorReason.isUserRecoverableError()){
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        }else{
            String errorMessage = String.format(getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode == RECOVERY_DIALOG_REQUEST){
           //Khởi tạo lại nếu người dùng thực hiện hành động khôi phục
           getYouTubePlayerProvider().initialize(DeveloperKey.DEVELOPER_KEY, this);
       }
    }
    protected abstract YouTubePlayer.Provider getYouTubePlayerProvider();
}
