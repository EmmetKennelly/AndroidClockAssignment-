package com.example.menu;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static java.lang.System.currentTimeMillis;


public class AlarmActivity  extends AppCompatActivity {
    EditText hourT;
    EditText minuteT;
    TextView textView;
    Button setAlarm;
    Button Reset;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

         hourT = (EditText)findViewById(R.id.Hour);
         minuteT = (EditText)findViewById(R.id.Minute);
         textView = (TextView)findViewById(R.id.textView3);
         setAlarm = (Button) findViewById(R.id.set);
         Reset = (Button)findViewById(R.id.button2);

            setAlarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((TextUtils.isEmpty(hourT.getText().toString())) || (TextUtils.isEmpty(minuteT.getText().toString()))) {
                        String Text = "Invalid input for time";
                        textView.setText(Text);
                    } else {

                        int hour = Integer.parseInt(hourT.getText().toString());
                        int minute = Integer.parseInt(minuteT.getText().toString());
                        String digit;
                        String add;
                        if (minute < 10) {
                            digit = "0";
                        } else {
                            digit = "";
                        }
                        if (hour < 12) {
                            add = "am";
                        } else {
                            add = "pm";
                        }
                        if (hour <= 24 && minute <= 60) {
                            String Text = " Alarm set for " + hour + ":" + digit + minute + add;
                            textView.setText(Text);
                            Calendar c = Calendar.getInstance();
                            c.set(Calendar.HOUR_OF_DAY, hour);
                            c.set(Calendar.MINUTE, minute);
                            c.set(Calendar.SECOND, 0);
                            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                            Intent intent = new Intent(v.getContext(), Alarm.class);
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(v.getContext(), 1, intent, 0);
                            if (c.before(Calendar.getInstance())) {
                                c.add(Calendar.DATE, 1);
                            }
                            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
                        } else {
                            String Text = "Invalid input for time";
                            textView.setText(Text);
                        }

                    }
                }
            });

Reset.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(v.getContext(),Alarm.class);
        PendingIntent pendingIntent =PendingIntent.getBroadcast(v.getContext(),1,intent,0);
        alarmManager.cancel(pendingIntent);
        textView.setText("Alarm Canceled");
        ;

    }
});
    }


}
