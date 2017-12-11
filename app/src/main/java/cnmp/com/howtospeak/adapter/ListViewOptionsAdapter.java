package cnmp.com.howtospeak.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cnmp.com.howtospeak.R;
import cnmp.com.howtospeak.model.Option;

/**
 * Created by henry on 12/7/2017.
 */

public class ListViewOptionsAdapter extends ArrayAdapter<Option> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Option> optionArrayList;

    public ListViewOptionsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Option> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.optionArrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_list_options, parent, false);
            viewHolder.imvIcon = convertView.findViewById(R.id.imv_icon);
            viewHolder.txtName = convertView.findViewById(R.id.txt_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Option option = optionArrayList.get(position);

        if (option != null) {
            viewHolder.txtName.setText(option.getName());
            viewHolder.imvIcon.setImageResource(option.getResIcon());
        }

        return convertView;
    }

    public class ViewHolder {
        public ImageView imvIcon;
        public TextView txtName;
    }
}
