package com.talhahasanzia.lushgenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView {

    private SurfaceHolder surfaceHolder;

    private Bitmap bmpIcon;

    private MyThread myThread;


    private  Context context;

    int xPos = 0;
    int yPos = 0;
    int deltaX = 5;
    int deltaY = 5;


    int iconWidth;
    int iconHeight;


    public MySurfaceView(Context context) {
        super(context);

        this.context=context;
        init();
    }


    public MySurfaceView(Context context,
                         AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }


    public MySurfaceView(Context context,
                         AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
        init();

    }


    private void init() {


        Drawable dr= getResources().getDrawable( R.drawable.background);

        this.setBackground(dr);

        myThread = new MyThread(this);



        surfaceHolder = getHolder();
        bmpIcon = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);

        iconWidth = bmpIcon.getWidth();
        iconHeight = bmpIcon.getHeight();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                myThread.setRunning(true);
                myThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder,
                                       int format, int width, int height) {
                // TODO Auto-generated method stub

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                myThread.setRunning(false);
                while (retry) {
                    try {
                        myThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }


    protected void drawSomething(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        canvas.drawBitmap(bmpIcon,
                getWidth() / 2, getHeight() / 2, null);


        xPos += deltaX;


        if (deltaX > 0) {
            if (xPos >= getWidth() - iconWidth) {
                deltaX *= -0.5;
            }
        } else {
            if (xPos <= 0) {
                deltaX *= -0.5;
            }
        }


        yPos += deltaY;
        if (deltaY > 0) {
            if (yPos >= getHeight() - iconHeight) {
                deltaY *= -0.5;
            }
        } else {
            if (yPos <= 0) {
                deltaY *= -0.5;
            }
        }

        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bmpIcon,
                xPos, yPos, null);

    }

    public void drawTriangle(Canvas canvas) {
        int halfWidth = iconWidth / 2;

        Paint paint = new Paint();
        paint.setColor(ActivityCompat.getColor(context,android.R.color.holo_green_dark));

        Path path = new Path();
        path.moveTo(xPos, yPos - halfWidth); // Top
        path.lineTo(xPos - halfWidth, yPos + halfWidth); // Bottom left
        path.lineTo(xPos + halfWidth, yPos + halfWidth); // Bottom right
        path.lineTo(xPos, yPos - halfWidth); // Back to Top
        path.close();

        canvas.drawPath(path, paint);
    }


}