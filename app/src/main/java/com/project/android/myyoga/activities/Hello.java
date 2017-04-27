package com.project.android.myyoga.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.project.android.myyoga.R;
import com.project.android.myyoga.config.Config;
import com.project.android.myyoga.config.ObjectPreferences;
import com.project.android.myyoga.config.SessionManager;
import com.project.android.myyoga.model.User;

public class Hello extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final String TAG = "Hello";
    private String username = "";
    private static final int RECOVERY_REQUEST =1;
    private YouTubePlayerView youTubePlayerView;
    private ObjectPreferences objectPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       /* SharedPreferences prefs = getSharedPreferences("myYoga-project", MODE_PRIVATE);
        username = prefs.getString("username", "UNKNOWN");
        TextView textView = (TextView) findViewById(R.id.nameView);
        textView.setText(username);*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(Config.YOUTUBE_API_KEY,this);
        objectPreferences = (ObjectPreferences) this.getApplication();
        SessionManager sessionManager = objectPreferences.getSessionManager();
        User user = sessionManager.getObject("User", User.class);
        Log.i(TAG, "User Name is " + user.getName());
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean restored) {
        if(!restored){
            youTubePlayer.cueVideo("q5nyrD4eM64");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), youTubeInitializationResult.toString());
            Toast.makeText(this,error,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RECOVERY_REQUEST){
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY,this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubePlayerView;
    }
}
