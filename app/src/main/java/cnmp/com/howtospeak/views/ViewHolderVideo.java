package cnmp.com.howtospeak.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cnmp.com.howtospeak.R;

/**
 * Created by henry on 12/9/2017.
 */

public class ViewHolderVideo extends RecyclerView.ViewHolder {
    public TextView txtVideoTitle;
    public ImageView imvVideoThumbnail;
    public TextView txtLevel;

    public ViewHolderVideo(final View itemView) {
        super(itemView);

        txtVideoTitle = itemView.findViewById(R.id.txt_video_title);
        txtLevel = itemView.findViewById(R.id.txt_level);
        imvVideoThumbnail = itemView.findViewById(R.id.imv_thumbnail_video);

        //called when click icon of video

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(itemView.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
