package cnmp.com.howtospeak.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import cnmp.com.howtospeak.LoginActivity;
import cnmp.com.howtospeak.R;
import cnmp.com.howtospeak.model.Acount;
import cnmp.com.howtospeak.network.SharedPreferencesManganer;

public class LearnFragment extends Fragment {
    Button login;
    Acount acount;
    private LinearLayout layoutIntroduce;
    private LinearLayout layoutFeatures;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View contentView = inflater.inflate(R.layout.fragment_learn, container, false);
        getActivity().setTitle(R.string.learn);
        acount = SharedPreferencesManganer.getAcount(contentView.getContext());
        layoutIntroduce = contentView.findViewById(R.id.layout_introduce);
        layoutFeatures = contentView.findViewById(R.id.layout_features);
        login = (Button) contentView.findViewById(R.id.btnLog);
        if (acount == null) {
            layoutIntroduce.setVisibility(View.VISIBLE);
            layoutFeatures.setVisibility(View.GONE);
        } else {
            layoutIntroduce.setVisibility(View.GONE);
            layoutFeatures.setVisibility(View.VISIBLE);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contentView.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return contentView;
    }

}
