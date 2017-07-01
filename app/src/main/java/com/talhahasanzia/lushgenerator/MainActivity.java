package com.talhahasanzia.lushgenerator;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private LushSurfaceView lushSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lushSurfaceView =(LushSurfaceView)findViewById(R.id.my_surface_view);



        lushSurfaceView.draw(Drawing.RECT);






    }
}
