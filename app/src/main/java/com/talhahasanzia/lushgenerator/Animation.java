package com.talhahasanzia.lushgenerator;

import android.graphics.Canvas;

public class Animation extends Thread {

    LushSurfaceView myView;
    private boolean running = false;

    public Animation(LushSurfaceView view) {
        myView = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        while (running) {

           /* Canvas canvas = myView.getHolder().lockCanvas();

            if (canvas != null) {
                synchronized (myView.getHolder()) {
                    myView.drawBitmap(canvas);


                    //myView.drawTriangle(canvas );
                }
                myView.getHolder().unlockCanvasAndPost(canvas);
            }

            try {
                sleep(30);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
*/
        }
    }

}