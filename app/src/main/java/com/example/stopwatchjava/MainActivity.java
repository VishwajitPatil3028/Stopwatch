package com.example.stopwatchjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    Button start;
    Button stop;
    Button reset;
    Handler handler;
    long millisecondTime,startTime,timeBuff,updateTime=0;
    int second,minute,milliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer=findViewById(R.id.tvTimer);
        start=findViewById(R.id.startBtn);
        stop=findViewById(R.id.stopBtn);
        reset=findViewById(R.id.resetBtn);

        handler=new Handler();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime= SystemClock.uptimeMillis();
                handler.postDelayed(runnable,0);
                reset.setEnabled(false);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeBuff+=millisecondTime;
                handler.removeCallbacks(runnable);
                reset.setEnabled(true);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                millisecondTime=0;
                startTime=0;
                timeBuff=0;
                updateTime=0;
                second=0;
                minute=0;
                milliseconds=0;
                timer.setText("00:00:00");
            }
        });


    }

    public Runnable runnable=new Runnable() {
        @SuppressLint({"DefaultLocale", "SetTextI18n"})
        @Override
        public void run() {
            millisecondTime=SystemClock.uptimeMillis()-startTime;
            updateTime=timeBuff+millisecondTime;
            second=(int)(updateTime/1000);
            minute=second/60;
            second=second%60;
            milliseconds=(int)(updateTime%1000);
            timer.setText(""+minute+":"+String.format("%02d",second)+":" +String.format("%03d",milliseconds));
            handler.postDelayed(this,0);
        }
    };

}