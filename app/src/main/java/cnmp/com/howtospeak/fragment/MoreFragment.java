package cnmp.com.howtospeak.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import cnmp.com.howtospeak.R;
import cnmp.com.howtospeak.adapter.ListViewOptionsAdapter;
import cnmp.com.howtospeak.model.Option;

public class MoreFragment extends Fragment {

    private ListView listViewOption;
    private ListViewOptionsAdapter listViewOptionsAdapter;
    private ArrayList<Option> optionArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.more);
        View contentView = inflater.inflate(R.layout.fragment_more, container, false);
        listViewOption = contentView.findViewById(R.id.listOption);
        optionArrayList = new ArrayList<>();
        listViewOptionsAdapter = new ListViewOptionsAdapter(getContext(), R.layout.item_list_options, optionArrayList);
        listViewOption.setAdapter(listViewOptionsAdapter);
        initOption();

        return contentView;
    }

    private void initOption() {
        optionArrayList.add(new Option(getResources().getString(R.string.settings), R.drawable.ic_setting));
        optionArrayList.add(new Option(getResources().getString(R.string.invite_friends), R.drawable.ic_invite));
        optionArrayList.add(new Option(getResources().getString(R.string.guide), R.drawable.ic_guide));
        optionArrayList.add(new Option(getResources().getString(R.string.rate), R.drawable.ic_rate));
        optionArrayList.add(new Option(getResources().getString(R.string.facebook_page), R.drawable.ic_facebook));
        optionArrayList.add(new Option(getResources().getString(R.string.instagram), R.drawable.ic_instagram));
        optionArrayList.add(new Option(getResources().getString(R.string.feed_back), R.drawable.ic_feedback));
        optionArrayList.add(new Option(getResources().getString(R.string.about), R.drawable.ic_about));
        listViewOptionsAdapter.notifyDataSetChanged();
    }

}
