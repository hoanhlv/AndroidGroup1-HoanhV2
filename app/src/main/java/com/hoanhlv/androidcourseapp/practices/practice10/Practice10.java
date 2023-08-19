package com.hoanhlv.androidcourseapp.practices.practice10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hoanhlv.androidcourseapp.R;

public class Practice10 extends AppCompatActivity implements View.OnClickListener {

    TextView textPlayer;
    Button btnStartPlayerService, btnStopPlayerService, btnBindPlayerService, btnUnbindPlayerService;
    Intent playerStartedIntent;
    Intent playerBoundIntent;
    private Practice10_PlayerBound boundPlayerService;
    boolean isBound;

    public ServiceConnection playerServiceCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice10);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(this.getIntent().getStringExtra("practiceName") + "");

        btnStartPlayerService = findViewById(R.id.btnStartPlayerService);
        btnStopPlayerService = findViewById(R.id.btnStopPlayerService);
        textPlayer = findViewById(R.id.textPlayer);

        btnStartPlayerService.setOnClickListener(this);
        btnStopPlayerService.setOnClickListener(this);
        textPlayer.setVisibility(View.GONE);

        // started service
        playerStartedIntent = new Intent(getBaseContext(), Practice10_PlayerStarted.class);

        // bind service
        btnUnbindPlayerService = findViewById(R.id.btnUnbindPlayerService);
        btnBindPlayerService = findViewById(R.id.btnBindPlayerService);

        btnUnbindPlayerService.setOnClickListener(this);
        btnBindPlayerService.setOnClickListener(this);
        playerServiceCon = new ServiceConnection() {

            public void onServiceConnected(ComponentName className, IBinder binder) {
                boundPlayerService = ((Practice10_PlayerBound.PlayerBinder) binder).getService();
                Log.d("ServiceConnection","connected");
                isBound = true;
            }

            public void onServiceDisconnected(ComponentName className) {
                Log.d("ServiceConnection","disconnected");
                isBound = false;
            }
        };
        playerBoundIntent = new Intent(Practice10.this, Practice10_PlayerBound.class);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onClick(View view) {
        // started service
        if (view == btnStartPlayerService) {
            startService(playerStartedIntent);
            textPlayer.setText("Playing...");
            textPlayer.setVisibility(View.VISIBLE);
        }
        if (view == btnStopPlayerService) {
            stopService(playerStartedIntent);
            textPlayer.setText("Stopped play!");
            textPlayer.setVisibility(View.VISIBLE);
        }

        // bind service
        if (view == btnBindPlayerService) {
            bindService(playerBoundIntent, playerServiceCon, Context.BIND_AUTO_CREATE);
        }
        if (view == btnUnbindPlayerService) {
            unbindService(playerServiceCon);
        }
    }
}