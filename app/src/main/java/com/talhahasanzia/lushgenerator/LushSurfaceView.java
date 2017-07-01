package com.talhahasanzia.lushgenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class LushSurfaceView extends SurfaceView implements Runnable {

    private SurfaceHolder surfaceHolder;


    int multiplier;


    private Drawing drawing;

    private Context context;

    private int xPos = 0;
    private int yPos = 0;
    private int deltaX = 5;
    private int deltaY = 5;


    boolean running = false;


    private Thread thread = null;


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


        multiplier = 1;
        //setLayerType(android.view.View.LAYER_TYPE_SOFTWARE, null);
        surfaceHolder = getHolder();


        setZOrderOnTop(true);


        surfaceHolder.setFormat(PixelFormat.TRANSPARENT);


    }


    public void draw(Drawing drawing) {


        this.drawing = drawing;


        running = true;

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


    // blueprint for all drawings
    private void drawShape() {

        if (surfaceHolder.getSurface().isValid()) {


            Canvas canvas = surfaceHolder.lockCanvas();


            if (canvas != null && !surfaceHolder.isCreating()) {

                //paint
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setStrokeWidth(10);


                Random random = new Random();

                int w = canvas.getWidth();
                int h = canvas.getHeight();

                int left = Math.round(w / 2);
                int top = Math.round(h / 2);


                int right = 200;
                int bottom = 200;

                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);


                paint.setColor(0xff000000 + (r << 16) + (g << 8) + b);
                RectF bounds = new RectF(left, top, left + 400, 400);


                canvas.drawOval(bounds, paint);

                 r = random.nextInt(255);
                 g = random.nextInt(255);
                 b = random.nextInt(255);


                paint.setColor(0xff000000 + (r << 16) + (g << 8) + b);
                 canvas.drawCircle(left,top, 300, paint);


                 r = random.nextInt(255);
                 g = random.nextInt(255);
                 b = random.nextInt(255);


                paint.setColor(0xff000000 + (r << 16) + (g << 8) + b);
                 canvas.drawLine(200f,100f,600f,800f,paint);


                 r = random.nextInt(255);
                 g = random.nextInt(255);
                 b = random.nextInt(255);


                paint.setColor(0xff000000 + (r << 16) + (g << 8) + b);
                canvas.drawRect(new Rect(left, top, right, bottom), paint);


                float[] points = new float[]{100f, 200f, 600f, 700f, 600f, 700f, 950f, 900f, 950f, 900f, 650f, 550f, 250f, 4000f, 280f, 60f};


                canvas.drawLines(points, paint);

                r = random.nextInt(255);
                g = random.nextInt(255);
                b = random.nextInt(255);


                paint.setColor(0xff000000 + (r << 16) + (g << 8) + b);


                bounds = new RectF(left+300, top+600, left + 200, 300);


                canvas.drawArc(bounds, (float) -90, (float) (130), false, paint);


                surfaceHolder.unlockCanvasAndPost(canvas);

                running = false; // draw just once
            }
        }

    }


    private void drawLinesShape() {

        if (surfaceHolder.getSurface().isValid()) {


            Canvas canvas = surfaceHolder.lockCanvas();


            if (canvas != null && !surfaceHolder.isCreating()) {

                //paint
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setStrokeWidth(10);


                Random random = new Random();


                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);


                paint.setColor(0xff000000 + (r << 16) + (g << 8) + b);


                float[] points = new float[]{100f, 200f, 600f, 700f, 600f, 700f, 950f,
                        900f, 950f, 900f, 650f, 550f, 250f, 4000f, 280f, 60f};


                canvas.drawLines(points, paint);


                surfaceHolder.unlockCanvasAndPost(canvas);

                running = false; // draw just once
            }
        }

    }


    private void drawLineShape() {

        if (surfaceHolder.getSurface().isValid()) {


            Canvas canvas = surfaceHolder.lockCanvas();


            if (canvas != null && !surfaceHolder.isCreating()) {

                //paint
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setStrokeWidth(10);


                Random random = new Random();


                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);


                paint.setColor(0xff000000 + (r << 16) + (g << 8) + b);


                canvas.drawLine(200f, 100f, 600f, 800f, paint);

                surfaceHolder.unlockCanvasAndPost(canvas);

                running = false; // draw just once
            }
        }

    }


    private void drawArcShape() {

        if (surfaceHolder.getSurface().isValid()) {


            Canvas canvas = surfaceHolder.lockCanvas();


            if (canvas != null && !surfaceHolder.isCreating()) {

                //paint
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setStrokeWidth(10);


                Random random = new Random();


                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);


                paint.setColor(0xff000000 + (r << 16) + (g << 8) + b);
                RectF bounds = new RectF(10, 10, 400, 400);

                canvas.drawArc(bounds, (float) -90, (float) (130), false, paint);


                surfaceHolder.unlockCanvasAndPost(canvas);

                running = false; // draw just once
            }
        }

    }


    private void drawCircleShape() {

        if (surfaceHolder.getSurface().isValid()) {


            Canvas canvas = surfaceHolder.lockCanvas();


            if (canvas != null && !surfaceHolder.isCreating()) {

                //paint
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setStrokeWidth(10);


                Random random = new Random();

                int w = canvas.getWidth();
                int h = canvas.getHeight();

                int left = Math.round(w / 2);
                int top = Math.round(h / 2);


                int right = 200;
                int bottom = 200;

                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);


                paint.setColor(0xff000000 + (r << 16) + (g << 8) + b);
                RectF bounds = new RectF(left, top, left + 400, 400);



                canvas.drawCircle(left, top, 300, paint);

                surfaceHolder.unlockCanvasAndPost(canvas);

                running = false; // draw just once
            }
        }

    }

    private void drawOvalShape() {

        if (surfaceHolder.getSurface().isValid()) {


            Canvas canvas = surfaceHolder.lockCanvas();


            if (canvas != null && !surfaceHolder.isCreating()) {

                //paint
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setStrokeWidth(10);


                Random random = new Random();

                int w = canvas.getWidth();
                int h = canvas.getHeight();

                int left = Math.round(w / 2);
                int top = Math.round(h / 2);


                int right = 200;
                int bottom = 200;

                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);


                paint.setColor(0xff000000 + (r << 16) + (g << 8) + b);
                RectF bounds = new RectF(left, top, left + 400, 400);


                canvas.drawOval(bounds, paint);

                surfaceHolder.unlockCanvasAndPost(canvas);

                running = false; // draw just once
            }
        }

    }


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

                multiplier = multiplier + 10;
                int right = 200 + multiplier;
                int bottom = 200;

                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);
                paint.setColor(0xff000000 + (r << 16) + (g << 8) + b);
                canvas.drawRect(new Rect(left, top, right, bottom), paint);


                surfaceHolder.unlockCanvasAndPost(canvas);

                running = false;
            }
        }

    }

    private void drawBitmap(Canvas canvas) {


    }

    private void drawTriangle(Canvas canvas) {
        int halfWidth = getWidth() / 2;

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
                        drawShape();
                        break;

                    case OVAL:
                        break;


                    case ARC:
                        break;


                }


            }
        }
    }


}