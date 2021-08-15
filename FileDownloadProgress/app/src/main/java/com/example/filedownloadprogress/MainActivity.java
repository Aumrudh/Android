package com.example.filedownloadprogress;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1= findViewById(R.id.upload);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progress=new ProgressDialog(MainActivity.this);
                progress.setCancelable(true);
                progress.setMessage("Uploading File");
                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progress.setProgress(0);
                progress.show();
                final int totalTime = 100;
                final Thread t = new Thread() {
                    @Override
                    public void run() {
                        int time=0;
                        while(time <= totalTime) {
                            time += 10;
                           try{
                               sleep(200);
                               progress.setProgress(time);
                           }
                           catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(time==100){
                                progress.dismiss();
                            }
                        }
                    }
                };
                t.start();
            }
        });
    }
}