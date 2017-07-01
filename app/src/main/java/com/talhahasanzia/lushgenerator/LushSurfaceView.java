package com.talhahasanzia.lushgenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class LushSurfaceView extends SurfaceView implements Runnable, SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;

    private Bitmap bmpIcon;

    int multiplier;


    private Drawing drawing;

    private Context context;

    private int xPos = 0;
    private int yPos = 0;
    private int deltaX = 5;
    private int deltaY = 5;


    boolean running=false;


    private Thread thread = null;


    private int iconWidth;
    private int iconHeight;


    public LushSurfaceView(Context context) {
        super(context);

        this.context = context;
        init();
    }


    public LushSurfaceView(Context context,
                           AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }


    public LushSurfaceView(Context context,
                           AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();

    }


    private void init() {



        multiplier=1;
        //setLayerType(android.view.View.LAYER_TYPE_SOFTWARE, null);
        surfaceHolder = getHolder();


      /*  surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                Canvas canvas = holder.lockCanvas();

                canvas.drawARGB(255, 255, 255, 255);

                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });*/


    }


    /*void animateWith(final Animation animation) {


        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                animation.setRunning(true);
                animation.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder,
                                       int format, int width, int height) {
                // TODO Auto-generated method stub

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                animation.setRunning(false);
                while (retry) {
                    try {
                        animation.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });

    }
*/

    private void drawRectShape() {

        if (surfaceHolder.getSurface().isValid()) {


            Canvas canvas = surfaceHolder.lockCanvas();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (canvas != null && !surfaceHolder.isCreating()) {
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(30);


                Random random = new Random();

                int w = canvas.getWidth();
                int h = canvas.getHeight();
                int left = Math.round(w / 2);
                int top = Math.round(h / 2);

                multiplier=multiplier+10;
                int right = 200+multiplier;
                int bottom = 200;

                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);
                paint.setColor(0xff000000 + (r << 16) + (g << 8) + b);
                canvas.drawRect(new Rect(left, top, right, bottom), paint);


                surfaceHolder.unlockCanvasAndPost(canvas);

                running=false;
            }
        }

    }

    private void drawBitmap(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        canvas.drawBitmap(bmpIcon,
                getWidth() / 2, getHeight() / 2, null);


        xPos += deltaX;


        if (deltaX > 0) {
            if (xPos >= getWidth() - iconWidth) {
                deltaX *= -1;
            }
        } else {
            if (xPos <= 0) {
                deltaX *= -1;
            }
        }


        yPos += deltaY;
        if (deltaY > 0) {
            if (yPos >= getHeight() - iconHeight) {
                deltaY *= -1;
            }
        } else {
            if (yPos <= 0) {
                deltaY *= -1;
            }
        }

        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bmpIcon,
                xPos, yPos, null);

    }

    private void drawTriangle(Canvas canvas) {
        int halfWidth = iconWidth / 2;

        Paint paint = new Paint();
        paint.setColor(ActivityCompat.getColor(context, android.R.color.holo_green_dark));

        Path path = new Path();
        path.moveTo(xPos, yPos - halfWidth); // Top
        path.lineTo(xPos - halfWidth, yPos + halfWidth); // Bottom left
        path.lineTo(xPos + halfWidth, yPos + halfWidth); // Bottom right
        path.lineTo(xPos, yPos - halfWidth); // Back to Top
        path.close();

        canvas.drawPath(path, paint);
    }


    @Override
    public void run() {


        while (running) {
            if (drawing != null) {


                switch (drawing) {

                    case BITMAP:
                        break;


                    case RECT:
                        drawRectShape();
                        break;

                    case OVAL:
                        break;


                    case ARC:
                        break;


                }


            }
        }
    }


    public void draw(Drawing drawing) {


        this.drawing = drawing;


        running=true;

        if (surfaceHolder.isCreating()) {

            surfaceHolder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {


                    thread = new Thread(LushSurfaceView.this);
                    thread.start();

                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                }
            });


        } else {


            thread = new Thread(this);
            thread.start();


        }

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.surfaceHolder = holder;

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}