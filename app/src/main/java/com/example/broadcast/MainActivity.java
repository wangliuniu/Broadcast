package com.example.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     private LocalBroadcastManager broadcastMannager;

    private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNormal=findViewById(R.id.btn_normal);
        btnNormal.setOnClickListener(this);

        Button btnOrdered=findViewById(R.id.btn_ordered);
        btnOrdered.setOnClickListener(this);

        Button btnLocal=findViewById(R.id.btn_local);
        btnLocal.setOnClickListener(this);

        broadcastMannager= LocalBroadcastManager.getInstance(this);
        //广播接收器的动态注册
        myReceiver=new MyReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("edu.niit.android.broadcast.MY_NORMAL_BROADCAST");
        filter.addAction("edu.niit.android.broadcast.MY_ORDERED_BROADCAST");
        filter.addAction("edu.niit.android.broadcast.MY_LOCAL_BROADCAST");
        filter.setPriority(100);
//        this.registerReceiver(myReceiver,filter);

        broadcastMannager.registerReceiver(myReceiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销自定义广播接收器
        if (myReceiver!=null){
//            this.unregisterReceiver(myReceiver);
            broadcastMannager.unregisterReceiver(myReceiver);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_normal:
                Intent intent=new Intent("edu.niit.android.broadcast.MY_NORMAL_BROADCAST");
                intent.putExtra("info","标准广播");
                this.sendBroadcast(intent);

                break;
            case  R.id.btn_ordered:
                intent =new Intent("edu.niit.android.broadcast.MY_ORDERED_BROADCAST");
                intent.putExtra("info","有序广播");
                this.sendOrderedBroadcast(intent,null);
                break;
            case  R.id.btn_local:
                intent=new Intent("edu.niit.android.broadcast.MY_LOCAL_BROADCAST");
                intent.putExtra("info","本地广播");
               broadcastMannager.sendBroadcast(intent);

                break;


        }
    }
}
