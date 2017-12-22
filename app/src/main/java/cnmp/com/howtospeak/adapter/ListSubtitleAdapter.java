package cnmp.com.howtospeak.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cnmp.com.howtospeak.R;
import cnmp.com.howtospeak.model.Subtitle;

/**
 * Created by henry on 12/22/2017.
 */

public class ListSubtitleAdapter extends ArrayAdapter<Subtitle> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Subtitle> subtitleArrayList;

    public ListSubtitleAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Subtitle> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.subtitleArrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_list_subtitle, parent, false);
            viewHolder.txtSubtitle = convertView.findViewById(R.id.txt_subtitle);
            viewHolder.layoutContent = convertView.findViewById(R.id.layout_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Subtitle subtitle = subtitleArrayList.get(position);

        if (subtitle != null) {
            //if (subtitle.getPlaying()) {
              //  viewHolder.txtSubtitle.setTextColor(context.getColor(R.color.colorWhite));
                //viewHolder.layoutContent.setBackground(context.getDrawable(R.color.colorBlack));
            //} else {
//                viewHolder.txtSubtitle.setTextColor(context.getColor(R.color.colorBlack));
  //              viewHolder.layoutContent.setBackground(context.getDrawable(R.color.colorWhite));
            //}

            viewHolder.txtSubtitle.setText(subtitle.getText());
        }

        return convertView;
    }

    public void refreshData(ArrayList<Subtitle> arrayList){
        subtitleArrayList.addAll(arrayList);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return subtitleArrayList.size();
    }

    public class ViewHolder {
        public TextView txtSubtitle;
        public LinearLayout layoutContent;
    }
}
