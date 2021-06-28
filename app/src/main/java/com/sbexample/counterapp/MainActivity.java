package com.sbexample.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView count;
    Handler mainhandler=new Handler();
    Button strt,end,rest;
    boolean running=false;
    int cnt;
    int i;
    void startThread(){
        newThread obj=new newThread();
        obj.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count=findViewById(R.id.count);
        strt=findViewById(R.id.start);
        end=findViewById(R.id.stop);
        rest=findViewById(R.id.reset);

        strt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running=true;
                startThread();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running=false;

            }
        });
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running=false;
                cnt=0;
                count.setText(String.valueOf(cnt));
            }
        });

    }
    class newThread extends Thread{
        @Override
        public void run() {
            if(running==false){
                cnt=0;
            }
            while(running){

                cnt++;
                mainhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        count.setText(String.valueOf(cnt));
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
