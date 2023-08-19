package com.hoanhlv.androidcourseapp.practices;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hoanhlv.androidcourseapp.R;

public class Practice6_Greeting extends AppCompatActivity {
    TextView messageFromActivity, nameFromActivity;
    Button btnBack, btnBackToMainPractice;
    String fullName, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice6_greeting);

        Intent intent = this.getIntent();
        getSupportActionBar().setTitle(intent.getStringExtra("practiceName") + "");

        messageFromActivity = findViewById(R.id.messageFromActivity);
        nameFromActivity = findViewById(R.id.nameFromActivity);
        // set message
        fullName = intent.getStringExtra("fullName");
        message = intent.getStringExtra("message");
        nameFromActivity.setText(fullName + ":");
        messageFromActivity.setText(message);

        // flow: MainActivity -> Practice6 -> Practice6_Greeting (here)
        // set back to previous (Practice6)
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        // flow: MainActivity -> Practice7 -> Practice6 -> Practice6_Greeting (here)
        // set back to main practice (Practice7)
        btnBackToMainPractice = findViewById(R.id.btnBackToMainPractice);
        if (intent.getIntExtra("practiceNumber", -1) == 7) {
            btnBackToMainPractice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent backIntent = new Intent(getApplicationContext(), Practice7.class);
                    backIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(backIntent);
                }
            });
        } else {
            btnBackToMainPractice.setVisibility(View.GONE);
        }
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("feedBack", "Hi " + fullName + ", did you just say: \"" + message + "\"?");
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }

    public void goBack() {
        this.onBackPressed();
    }
}