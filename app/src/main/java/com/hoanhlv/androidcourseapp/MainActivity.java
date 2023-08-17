package com.hoanhlv.androidcourseapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hoanhlv.androidcourseapp.practices.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    String[] practiceList = new String[] {
            "1 - Android Components",
            "2 - Layouts",
            "3 - Widgets",
            "4 - Date, Time, Tab",
            "5 - Menu",
            "6 - Intent",
            "7 - Intent Filter, Broadcast Receiver",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("Group 1");

        List<String> practiceItems = Arrays.stream(practiceList).map(i -> "Practice " + i)
                .collect(Collectors.toList());
        ListAdapter adapter = new ArrayAdapter<>(this, R.layout.activity_listpractice, practiceItems);

        ListView listView = findViewById(R.id.practice_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = null;
                Context appContext = getApplicationContext();
                switch (position + 1) {
                    case 1:
                    default:
                        intent = new Intent(appContext, Practice1.class);
                        break;
                    case 2:
                        intent = new Intent(appContext, Practice2.class);
                        break;
                    case 3:
                        intent = new Intent(appContext, Practice3.class);
                        break;
                    case 4:
                        intent = new Intent(appContext, Practice4.class);
                        break;
                    case 5:
                        intent = new Intent(appContext, Practice5.class);
                        break;
                    case 6:
                        intent = new Intent(appContext, Practice6.class);
                        break;
                    case 7:
                        intent = new Intent(appContext, Practice7.class);
                        break;
                }
                intent.putExtra("practiceName", practiceList[position]);
                startActivity(intent);
            }
        });
    }
}