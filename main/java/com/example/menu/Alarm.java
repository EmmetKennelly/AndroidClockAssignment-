package com.example.menu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class Alarm extends BroadcastReceiver {
    String message;
    final Alarm context = this;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Ring ring", Toast.LENGTH_SHORT).show();
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();
        Handler duration=new Handler();
        duration.postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.stop();
            }
        }, 3 * 1000);
        NotificationActivity notificationActivity = new NotificationActivity(context);
        NotificationCompat.Builder builder = notificationActivity.getNotify();
        notificationActivity.getManager().notify(0, builder.build());

    }
}
