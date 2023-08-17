package com.hoanhlv.androidcourseapp.practices;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hoanhlv.androidcourseapp.R;

import java.io.IOException;
import java.util.List;

public class Practice6 extends AppCompatActivity {

    Button btnSendMessage, btnCallDial, btnSendSms, btnShowMap, btnMusicPlayer;
    EditText fullNameText, messageText;
    TextView feedBackText;
    final int REQUEST_CODE = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice6);

        Intent intent = this.getIntent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(intent.getStringExtra("practiceName") + "");

        // explicit
        btnSendMessage = findViewById(R.id.btnSendMessage);
        fullNameText = findViewById(R.id.fullNameText);
        messageText = findViewById(R.id.messageText);
        feedBackText = findViewById(R.id.feedbackText);

        Context context = getApplicationContext();
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent greetingIntent = new Intent(context, Practice6_Greeting.class);
                greetingIntent.putExtras(intent);
                greetingIntent.putExtra("fullName", fullNameText.getText().toString());
                greetingIntent.putExtra("message", messageText.getText().toString());
                startActivityForResult(greetingIntent, REQUEST_CODE);
            }
        });

        // implicit
        btnCallDial = findViewById(R.id.btnCallDial);
        btnCallDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:555-9992"));
                startActivity(dialIntent);
            }
        });

        btnSendSms = findViewById(R.id.btnSendSms);
        btnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:555-9992"));
                smsIntent.putExtra("sms_body", "Wanna hangout?");
                startActivity(smsIntent);
            }
        });

        btnShowMap = findViewById(R.id.btnShowMap);
        btnShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    List<Address> address = geocoder.getFromLocationName("Van Phuc", 5);
                    String geoCode = "geo:" + address.get(0).getLatitude() + "," + address.get(0).getLongitude();
                    Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoCode));
                    startActivity(viewIntent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnMusicPlayer = findViewById(R.id.btnMusicPlayer);
        btnMusicPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            feedBackText.setText(data.getStringExtra("feedBack"));
        } else {
            feedBackText.setText("!?");
        }
    }
}