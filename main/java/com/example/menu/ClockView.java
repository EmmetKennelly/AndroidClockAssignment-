package com.example.menu;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class ClockView extends SurfaceView implements Runnable {

    private Context c;
    private int length;
    int X = 520;
    int radius = (int) (X / 1.5);
    Paint p = new Paint();
    Paint pNum = new Paint();
    private boolean running;
    private SurfaceHolder holder = null;
    private Thread thread = null;
    int[] clockNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    Calendar cal = Calendar.getInstance();
    SharedPreferences prefs ;

    public ClockView(Context context, int length) {
        super(context);

        this.c = context;
        this.length = length;

        thread = new Thread(this);
        thread.start();
        holder = this.getHolder();
        running = true;
    }

    public void onResume() {

        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public void onPause() {
        running = false;
        boolean rentry = true;

        while (rentry) {
            try {
                thread.join();
                rentry = false;
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void run() {
        prefs = c.getSharedPreferences("bgColour", MODE_PRIVATE);
        String colour = prefs.getString("colour","Grey");
        String colourH = prefs.getString("colourC","Black");

        Calendar c = Calendar.getInstance();
        int currentHour = c.get(Calendar.HOUR_OF_DAY);
        int hour = (int) (currentHour + c.get(Calendar.MINUTE) / 60)*5;
        int sec = c.get(Calendar.SECOND);
        int min = c.get(Calendar.MINUTE);

        while (running) {

            if (holder.getSurface().isValid()) {

                Canvas canvas = holder.lockCanvas();

                Paint paint = new Paint();
                Paint pN = new Paint();
                Paint pH = new Paint();
                paint.setStrokeWidth(3);
                paint.setTextSize(35);
                pH.setStrokeWidth(17);
                pN.setStrokeWidth(8);
                Paint paintHourmarks = new Paint();

                paintHourmarks.setColor(Color.RED);

                if (colourH == "Black") {
                    paint.setColor(Color.BLACK);
                    pH.setColor(Color.BLACK);
                    pN.setColor(Color.BLACK);
                } else if (colourH == "Grey") {
                    paint.setColor(Color.GRAY);
                    pH.setColor(Color.GRAY);
                    pN.setColor(Color.GRAY);
                } else if (colourH == "White") {
                    paint.setColor(Color.WHITE);
                    pH.setColor(Color.WHITE);
                    pN.setColor(Color.WHITE);
                }


                canvas.drawPaint(paint);
                canvas.drawColor(Color.GRAY);
                if (colour == "Black") {
                    canvas.drawColor(Color.BLACK);
                } else if (colour == "Grey") {
                    canvas.drawColor(Color.GRAY);

                } else if (colour == "White") {
                    canvas.drawColor(Color.WHITE);
                }

                    RegPoly secMarks = new RegPoly(60, 450, this.getWidth() / 2, this.getHeight() / 2, canvas, paint);
                    RegPoly hourMarks = new RegPoly(12, 400, this.getWidth() / 2, this.getHeight() / 2, canvas, paintHourmarks);
                    RegPoly secHand = new RegPoly(60, this.length - 30, this.getWidth() / 2, this.getHeight() / 2, canvas, paint);
                    RegPoly minuteHand = new RegPoly(60, this.length - 30, this.getWidth() / 2, this.getHeight() / 2, canvas, pN);
                    RegPoly hourHand = new RegPoly(60, this.length - 150, this.getWidth() / 2, this.getHeight() / 2, canvas, pH);
                    secMarks.drawPoints();
                    hourMarks.drawPoints();
                    secHand.drawRadius(sec + 45);
                    minuteHand.drawRadius(min + 45);
                    hourHand.drawRadius(hour + 45);


                    for (int i : clockNumbers) {
                        double spacing = Math.PI / 6 * (i - 3);
                        int x = (int) (525 + Math.cos(spacing) * radius);
                        int y = (int) (810 + Math.sin(spacing) * radius);
                        String value = String.valueOf(i);
                        canvas.drawText(value, x, y, paint);
                    }

                    holder.unlockCanvasAndPost(canvas);


                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }

                    sec++;
                    if (sec == 61) {
                        min++;
                        sec = 1;
                    }
                    if (min == 60) {
                        hour += 5;
                        min = 0;

                    }
                }

            }
        }


}
