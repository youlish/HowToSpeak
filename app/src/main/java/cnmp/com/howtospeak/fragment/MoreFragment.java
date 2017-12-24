package cnmp.com.howtospeak.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import cnmp.com.howtospeak.LoginActivity;
import cnmp.com.howtospeak.R;
import cnmp.com.howtospeak.adapter.ListViewOptionsAdapter;
import cnmp.com.howtospeak.model.Option;

public class MoreFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private ListView listViewOption;
    private ListViewOptionsAdapter listViewOptionsAdapter;
    private ArrayList<Option> optionArrayList;
    private Button btnLogin;
    private GoogleApiClient mGoogleApiClient;

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
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        btnLogin = (Button) contentView.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout1();
            }
        });
        optionArrayList = new ArrayList<>();
        listViewOptionsAdapter = new ListViewOptionsAdapter(getContext(), R.layout.item_list_options, optionArrayList);
        listViewOption.setAdapter(listViewOptionsAdapter);
        initOption();

        return contentView;
    }

    private void login() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);

    }

    private void logout() {
        //DatabaseManager.getInstance(getActivity()).onUpgrade();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                Toast.makeText(getActivity(), "SignOut", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void logout1() {
        FirebaseAuth.getInstance().signOut();
        if (AccessToken.getCurrentAccessToken() != null) {
            new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest.Callback() {
                @Override
                public void onCompleted(GraphResponse response) {
                    LoginManager.getInstance().logOut();

                }
            }).executeAsync();
            Toast.makeText(getContext(), "successful btnLogin", Toast.LENGTH_LONG).show();
            return;
        } else if (mGoogleApiClient.isConnected()) {
            logout();
            Toast.makeText(getContext(), "successful btnLogin", Toast.LENGTH_LONG).show();
        }

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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPause() {
        super.onPause();
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
    }
}
