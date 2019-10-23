package com.example.myapplication;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
  private MyReceiver myReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myReceiver=new MyReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("edu.niit.android.broadcast.MY_NORMAL_BROADCAST");
        filter.addAction("edu.niit.android.broadcast.MY_ORDERED_BROADCAST");
        filter.setPriority(50);
        this.registerReceiver(myReceiver,filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myReceiver!=null){
            unregisterReceiver(myReceiver);
        }
    }
}
