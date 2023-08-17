package com.hoanhlv.androidcourseapp.practices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.hoanhlv.androidcourseapp.R;

public class Practice7 extends AppCompatActivity {

    Button btnOpenPractice6, btnSearchWeb, btnSendBroadcast;
    TextView textSearchWeb, textBroadcast;
    Switch registerReceiver;
    Practice7_Receiver receiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice7);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(this.getIntent().getStringExtra("practiceName") + "");

        btnOpenPractice6 = findViewById(R.id.btnOpenPractice6);
        btnOpenPractice6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Practice6.class);
                intent.putExtra("practiceName", actionBar.getTitle());
                intent.putExtra("practiceNumber", 7);
                startActivity(intent);
            }
        });

        // search web action
        btnSearchWeb = findViewById(R.id.btnSearchWeb);
        textSearchWeb = findViewById(R.id.textSearchWeb);
        btnSearchWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = textSearchWeb.getText().toString();
                if (searchText.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Write your search text", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, searchText);
                startActivity(intent);
            }
        });

        // broadcast receiver
        intentFilter = new IntentFilter("com.hoanhlv.CUSTOM_ACTION");
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        receiver = new Practice7_Receiver();

        registerReceiver = findViewById(R.id.registerReceiver);
        btnSendBroadcast = findViewById(R.id.btnSendBroadcast);
        textBroadcast = findViewById(R.id.textBroadcast);
        registerReceiver.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                registerBroadcastReceiver();
            } else {
                unregisterBroadcastReceiver();
            }
        });
        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.hoanhlv.CUSTOM_ACTION");
                intent.putExtra("broadcastMessage", textBroadcast.getText().toString());
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (registerReceiver.isChecked()) {
            unregisterBroadcastReceiver();
        }
    }

    private void registerBroadcastReceiver() {
        registerReceiver(receiver, intentFilter);
        Toast.makeText(getApplicationContext(), "Registered Receiver", Toast.LENGTH_SHORT).show();
    }

    private void unregisterBroadcastReceiver() {
        unregisterReceiver(receiver);
        Toast.makeText(getApplicationContext(), "Unregistered Receiver", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }
}