package cnmp.com.howtospeak.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cnmp.com.howtospeak.R;

public class LearnFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView  =  inflater.inflate(R.layout.fragment_learn, container, false);
        getActivity().setTitle(R.string.learn);
        return contentView;
    }

}
