package com.hoanhlv.androidcourseapp.practices.practice10;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

public class Practice10_PlayerBound extends Service {

    private MediaPlayer player;
    private IBinder playerBinder;

    public Practice10_PlayerBound() {
    }

    @Override
    public void onCreate() {
        player = MediaPlayer.create( this, Settings.System.DEFAULT_RINGTONE_URI );
        player.setLooping( true );
        playerBinder = new PlayerBinder();

        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Toast.makeText(this, "Bound service!", Toast.LENGTH_SHORT).show();
        System.out.println("Bound service!");
        player.start();
        return playerBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "Unbound service!", Toast.LENGTH_SHORT).show();
        System.out.println("Unbound service!");
        player.stop();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Destroy service!", Toast.LENGTH_SHORT).show();
        System.out.println("Destroy service!");
        super.onDestroy();
    }

    class PlayerBinder extends Binder {
        public Practice10_PlayerBound getService() {
            return Practice10_PlayerBound.this;
        }
    }
}