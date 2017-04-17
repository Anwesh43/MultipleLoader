package com.anwesome.ui.multipleloaderdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.multipleloader.MultipleLoaderList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MultipleLoaderList multipleLoaderList = new MultipleLoaderList(this);
        multipleLoaderList.setColor(Color.parseColor("#009688"));
        multipleLoaderList.setSpeed(5);
        multipleLoaderList.addLoader();
        multipleLoaderList.addLoader();
        multipleLoaderList.addLoader();
        multipleLoaderList.addLoader();
        multipleLoaderList.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true && !multipleLoaderList.isHidden()) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                multipleLoaderList.update();
                            }
                        });

                        try {
                            Thread.sleep(200);
                        }
                        catch (Exception ex) {

                        }
                    }
                    catch (Exception ex) {

                    }
                }
            }
        }).start();
    }
}
