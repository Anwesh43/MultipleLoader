package com.anwesome.ui.multipleloader;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.*;
/**
 * Created by anweshmishra on 17/04/17.
 */
public class MultipleLoaderLayout extends ViewGroup{
    private boolean backgroundDrawn = false;
    private int w,h;
    private MultipleLoaderList list;
    private MultipleLoader multipleLoader;
    private int color = Color.parseColor("#2196F3");
    public void setColor(int color) {
        this.color = color;
    }
    public void setSpeed(int speed) {
        multipleLoader.setSpeed(speed);
    }
    public MultipleLoaderLayout(Context context,MultipleLoaderList list) {
        super(context);
        multipleLoader = new MultipleLoader(this);
        initDimension(context);
        this.list = list;
    }
    public void removeLoader(CustomLoader customLoader) {
        removeView(customLoader);
        requestLayout();
    }
    public void update() {
        multipleLoader.update();
    }
    public void hide() {
        setVisibility(INVISIBLE);
        list.hide();
    }
    public void addTask() {
        CustomLoader customLoader = new CustomLoader(getContext());
        customLoader.setColor(color);
        multipleLoader.addTask(customLoader);
        int wloader = Math.min(w,h)/2;
        addView(customLoader,new LayoutParams(wloader,wloader));
        requestLayout();
    }
    public void initDimension(Context context) {
        DisplayManager displayManager = (DisplayManager)context.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display != null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
    }
    public void onLayout(boolean reloaded,int a,int b,int w,int h) {
        int y = h/30,x = w/2;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            int wChild = child.getMeasuredWidth(),hChild = child.getMeasuredHeight();
            child.layout(x-wChild/2,y,x+wChild/2,y+hChild);
            y+=child.getMeasuredHeight()+h/30;
        }
    }
    public void onMeasure(int wspec,int hspec) {
        int hNew = Math.max(w,h)/30;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            measureChild(child,wspec,hspec);
            hNew += child.getMeasuredHeight()+h/30;
        }
        setMeasuredDimension(w,Math.max(h,hNew));
    }
}
