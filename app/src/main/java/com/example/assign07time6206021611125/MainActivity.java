package com.example.assign07time6206021611125;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private long mStartTime = 0;
    private TextView show;
    private Handler mHandler = new Handler(Looper.myLooper());
    private Button btnStart, btnStop;
    private boolean time_start = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = (TextView) findViewById(R.id.show);
        show.setText("00:00");
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnStart) {
            mStartTime = SystemClock.uptimeMillis();
            mHandler.postDelayed(mUpdateTimeTask, 100);
            time_start = true;
            show.setText("00:00");
            Toast.makeText(this, "Time Start", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.btnStop) {
            time_start = false;
            mHandler.removeCallbacks(mUpdateTimeTask);
            Toast.makeText(this, "Time Stop", Toast.LENGTH_SHORT).show();
        }
    }

    public Runnable mUpdateTimeTask = new Runnable() {
        @Override
        public void run() {
            if (time_start)
                mHandler.postDelayed(this, 100);
            final long nowTime = SystemClock.uptimeMillis();
            final long startTime = mStartTime;
            long millis = (nowTime - startTime);
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            show.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));

        }
    };
}