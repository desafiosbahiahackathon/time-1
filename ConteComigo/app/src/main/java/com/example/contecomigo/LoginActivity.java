package com.example.contecomigo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    NotificationCompat.Builder notification;
    private static final int notificationId = 1;

    Intent mapIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);
                finish();
            }
        });

        Button helpButton = (Button) findViewById(R.id.help_button);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification(v);
            }
        });
    }

    public void showNotification(View view) {
        // build the notification
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
        notification.setSmallIcon(R.drawable.ic_notification);
        notification.setLargeIcon(icon);
        notification.setTicker("Alguém precisa de sua ajuda!");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Alguém precisa de sua ajuda!");
        notification.setContentText("Uma denúncia foi feitas na proximidades");

        // when the person clicks the map
        if (mapIntent == null) {
            mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=-13.001060,-38.5079915&daddr=-13.0101574,-38.5091548"));
            PendingIntent mapPendingIntent = PendingIntent.getActivity(this, 2, mapIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.addAction(R.drawable.ic_location_on_black_24dp, "Ver rota", mapPendingIntent);

            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "7134511758"));
            PendingIntent callPendingIntent = PendingIntent.getActivity(this, 3, callIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.addAction(R.drawable.ic_call_black_24dp, "Pedir ajuda", callPendingIntent);

            // when the person clicks the notification
            Intent mainIntent = new Intent(this, LoginActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 4, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pendingIntent);
        }

        // build notification and issues it
        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification.build());
    }
}
