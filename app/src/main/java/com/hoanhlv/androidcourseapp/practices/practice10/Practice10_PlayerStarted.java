package com.hoanhlv.androidcourseapp.practices.practice10;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Practice10_PlayerStarted extends Service {
    private MediaPlayer player;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create( this, Settings.System.DEFAULT_RINGTONE_URI );
        player.setLooping( true );

        Toast.makeText(this, "Service starting", Toast.LENGTH_SHORT).show();
        System.out.println("Service starting");
        player.start();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service done!", Toast.LENGTH_SHORT).show();
        System.out.println("Service done!");
        player.stop();
        super.onDestroy();
    }
}
