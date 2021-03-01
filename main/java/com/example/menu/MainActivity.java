package com.example.menu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ClockView clock;
    private int length = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clock = new ClockView(this, length);
        setContentView(clock);
    }

    @Override
    protected void onPause() {
        super.onPause();

        clock.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        clock.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // inflate the menu
        getMenuInflater().inflate(R.menu.activity_menu, menu);

        // setup some menu components with their listeners

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int itemID = item.getItemId();

        switch (itemID){
            case R.id.about:
                // do a dialog box to say who you are
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Emmet Kennelly \n\nMScCS@2020\n\nUCC")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        });

                builder.show();

                break;

            case R.id.set:
                Intent intent1 = new Intent(MainActivity.this, PrefActivity.class);
                startActivity(intent1);
                break;
            case R.id.timer:
                Intent intent2 = new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(intent2);

        }

        return false;
    }
}