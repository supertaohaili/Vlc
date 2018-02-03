package com.yyl.vlc.vlc;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.yyl.vlc.MainActivity;
import com.yyl.vlc.R;

import org.videolan.libvlc.Media;
import org.videolan.vlc.VlcVideoView;
import org.videolan.vlc.util.VLCInstance;

public class VlcPlayerActivity extends AppCompatActivity {

    private VlcVideoView vlcVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlc_player);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        vlcVideoView = (VlcVideoView) findViewById(R.id.player);
        TextView logInfo = (TextView) findViewById(R.id.info);

        Media media = new Media(VLCInstance.get(this), Uri.parse(MainActivity.getUrl(this)));
        media.setHWDecoderEnabled(true, false);
        vlcVideoView.setMedia(media);
        vlcVideoView.startPlay(MainActivity.getUrl(this));
        vlcVideoView.setMediaListenerEvent(new MediaControl(vlcVideoView, logInfo));    }

    @Override
    public void onResume() {
        super.onResume();
        vlcVideoView.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        vlcVideoView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        vlcVideoView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vlcVideoView.onDestory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
            getSupportActionBar().hide();
        } else {
            getSupportActionBar().show();
        }
    }

}
