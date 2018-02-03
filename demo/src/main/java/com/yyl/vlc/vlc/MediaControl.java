package com.yyl.vlc.vlc;

import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import org.videolan.vlc.VlcVideoView;
import org.videolan.vlc.listener.MediaListenerEvent;
import org.videolan.vlc.listener.MediaPlayerControl;

/**
 * Created by yyl on 2016/11/3/003.
 */

public class MediaControl implements MediaListenerEvent {
    private final MediaController controller;
    private TextView logInfo;
    private String tag = "MediaControl";
    private long time;

    public MediaControl(final VlcVideoView mediaPlayer, TextView logInfo) {
        this.logInfo = logInfo;
        controller = new MediaController(mediaPlayer.getContext());
        controller.setMediaPlayer(mediaPlayer);
        controller.setAnchorView(mediaPlayer);
        mediaPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!controller.isShowing())
                    controller.show();
                else
                    controller.hide();
            }
        });
        controller.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mediaPlayer.getContext(),"点击了",Toast.LENGTH_SHORT).show();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mediaPlayer.getContext(),"点击了",Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void eventBuffing(int event, float buffing) {
    }

    @Override
    public void eventPlayInit(boolean openClose) {
        if (openClose) {
            time = System.currentTimeMillis();
        }
        logInfo.setText("加载");
    }

    @Override
    public void eventStop(boolean isPlayError) {
        logInfo.setText("Stop" + (isPlayError ? "  播放已停止   有错误" : ""));

    }

    @Override
    public void eventError(int error, boolean show) {
        logInfo.setText("地址 出错了 error=" + error);
    }

    @Override
    public void eventPlay(boolean isPlaying) {
        if (isPlaying) {
            controller.show();
            Log.i(tag, "加载耗时  time=" + (System.currentTimeMillis() - time));
            logInfo.setText("播放中");
        } else {
            logInfo.setText("暂停中");
        }

    }

}
