package cnmp.com.howtospeak.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cnmp.com.howtospeak.LoginActivity;
import cnmp.com.howtospeak.R;

public class LearnFragment extends Fragment {
    Button login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View contentView  =  inflater.inflate(R.layout.fragment_learn, container, false);
        getActivity().setTitle(R.string.learn);
        login = (Button) contentView.findViewById(R.id.btnLog);
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
