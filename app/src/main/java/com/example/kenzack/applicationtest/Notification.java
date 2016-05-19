package com.example.kenzack.applicationtest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class Notification extends ActionBarActivity {
    NotificationCompat.Builder notification;
    private static final int uniqueID=12323;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notification=new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification.setSmallIcon(R.drawable.ic_menu_gallery);
                notification.setTicker("this is the ticker");
                notification.setWhen(System.currentTimeMillis());
                notification.setContentTitle("here is the title");
                notification.setContentText("this the body");

                Intent intent= new Intent(Notification.this,Notification.class);
                PendingIntent pt= PendingIntent.getActivity(Notification.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pt);

                NotificationManager nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueID,notification.build());
            }
        });

    }
}
