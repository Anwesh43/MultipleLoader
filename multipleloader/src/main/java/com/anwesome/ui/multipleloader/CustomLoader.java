package com.anwesome.ui.multipleloader;

import android.content.Context;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 17/04/17.
 */
public class CustomLoader extends View {
    private float endDeg = 0;
    private int color = Color.parseColor("#2196F3");
    private boolean done = false;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CustomLoader(Context context) {
        super(context);
    }
    public void setColor(int color) {
        this.color = color;
    }
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Constants.color);
        paint.setStyle(Style.STROKE);
        int r = canvas.getWidth()/2;
        paint.setStrokeWidth(Constants.LOADER_WIDTH);
        paint.setColor(Color.parseColor("#424242"));
        canvas.drawArc(new RectF(-r,-r,r,r),-90,endDeg,false,paint);
        paint.setColor(color);
        canvas.drawArc(new RectF(-r,-r,r,r),-90,360,false,paint);
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
    public void update(float factor) {
        if(factor<=1) {
            endDeg = 360 * factor;
            postInvalidate();
        }
        else {
            done = true;
        }
    }
    public boolean isDone() {
        return done;
    }
}
