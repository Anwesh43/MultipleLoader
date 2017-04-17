package com.anwesome.ui.multipleloader;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 17/04/17.
 */
public class CustomLoader extends View {
    public CustomLoader(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
