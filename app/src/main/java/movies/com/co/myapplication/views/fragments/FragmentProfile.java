package movies.com.co.myapplication.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import movies.com.co.myapplication.R;
import retrofit2.Call;


public class FragmentProfile extends BaseFragment {

    private String TAG = "FragmentProfile";

    private LoginButton loginButton;
    private CallbackManager callbackManagerFacebook;

    private TwitterLoginButton twitterAuth;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Twitter.initialize(getBaseActivityFragment());
        FacebookSdk.sdkInitialize(getBaseActivityFragment().getApplicationContext());
        AppEventsLogger.activateApp(getBaseActivityFragment());

        View view = inflater.inflate(R.layout.activity_profile, container, false);
        initFacebook(view);
        initTwitter(view);



        return view;
    }

    private void initFacebook(View view) {
        callbackManagerFacebook = CallbackManager.Factory.create();
        loginButton = view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManagerFacebook, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Toast.makeText(getBaseActivityFragment(), "Token "+loginResult.getAccessToken(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {
                Toast.makeText(getBaseActivityFragment(), "Canceled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getBaseActivityFragment(), exception.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, exception.getMessage(),exception);
            }
        });



    }


    private void initTwitter(View view){
        twitterAuth = view.findViewById(R.id.twitterAuth);
        twitterAuth.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                if (authToken == null || (authToken!= null && authToken.isExpired())) {
                    Call<User> userCall = TwitterCore.getInstance().getApiClient(session)
                            .getAccountService().verifyCredentials(true, true, true);
                    userCall.enqueue(new Callback<User>() {
                        @Override
                        public void success(Result<User> result) {
                            String description = result.data.screenName;
                            Toast.makeText(getBaseActivityFragment(), description, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void failure(TwitterException exception) {
                            Toast.makeText(getBaseActivityFragment(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getBaseActivityFragment(), exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManagerFacebook.onActivityResult(requestCode, resultCode, data);
    }
}
