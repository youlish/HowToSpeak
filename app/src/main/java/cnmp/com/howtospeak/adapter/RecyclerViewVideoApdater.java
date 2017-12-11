package cnmp.com.howtospeak.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cnmp.com.howtospeak.R;
import cnmp.com.howtospeak.model.Video;
import cnmp.com.howtospeak.views.ViewHolderVideo;

/**
 * Created by henry on 12/9/2017.
 */

public class RecyclerViewVideoApdater extends RecyclerView.Adapter<ViewHolderVideo> {
    private ArrayList<Video> list;

    public RecyclerViewVideoApdater(ArrayList<Video> Data) {
        list = Data;
    }

    @Override
    public ViewHolderVideo onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_horizontal_videos, parent, false);
        ViewHolderVideo holder = new ViewHolderVideo(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderVideo holder, int position) {
        holder.txtLevel.setText(list.get(position).getLevel());
        holder.txtVideoTitle.setText(list.get(position).getVideoTitle());
        holder.imvVideoThumbnail.setImageResource(list.get(position).getResImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
