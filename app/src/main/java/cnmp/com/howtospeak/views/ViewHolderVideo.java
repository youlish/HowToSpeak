package cnmp.com.howtospeak.views;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeThumbnailView;

import cnmp.com.howtospeak.PlayVideoActivity;
import cnmp.com.howtospeak.R;

/**
 * Created by henry on 12/9/2017.
 */

public class ViewHolderVideo extends RecyclerView.ViewHolder {
    public TextView txtVideoTitle;
    public TextView txtLevel;
    public YouTubeThumbnailView thumbai;
    private String videoID;

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public ViewHolderVideo(final View itemView) {
        super(itemView);

        txtVideoTitle = itemView.findViewById(R.id.txt_video_title);
        txtLevel = itemView.findViewById(R.id.txt_level);
        thumbai = (YouTubeThumbnailView) itemView.findViewById(R.id.thumbnail);
        //called when click icon of video

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PlayVideoActivity.class);
                intent.putExtra("VideoID", videoID);
                view.getContext().startActivity(intent);
            }
        });
    }
}
