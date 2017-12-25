package cnmp.com.howtospeak.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeThumbnailView;

import cnmp.com.howtospeak.R;

/**
 * Created by henry on 12/9/2017.
 */

public class ViewHolderVideo extends RecyclerView.ViewHolder {
    public TextView txtVideoTitle;
    public TextView txtLevel;
    public YouTubeThumbnailView thumbai;
    public LinearLayout linearLayout;
    public ViewHolderVideo(final View itemView) {
        super(itemView);

        txtVideoTitle = itemView.findViewById(R.id.txt_video_title);
        txtLevel = itemView.findViewById(R.id.txt_level);
        linearLayout = itemView.findViewById(R.id.layout_level_background);
        thumbai = itemView.findViewById(R.id.thumbnail);
        //called when click icon of video
    }
}
