package com.anwesome.ui.multipleloader;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 17/04/17.
 */
public class MultipleLoaderList {
    private Activity activity;
    private ScrollView scrollView;
    private boolean isShown = false;
    public void setColor(int color) {
        if(multipleLoaderLayout!=null) {
            multipleLoaderLayout.setColor(color);
        }
    }
    private MultipleLoaderLayout multipleLoaderLayout;
    public void setSpeed(int speed) {
        multipleLoaderLayout.setSpeed(speed);
    }
    public MultipleLoaderList(Activity activity) {
        this.activity = activity;
        multipleLoaderLayout = new MultipleLoaderLayout(activity,this);
    }
    public boolean isHidden() {
        return !isShown;
    }
    public void hide() {
        if(this.scrollView!=null) {
            scrollView.setVisibility(View.INVISIBLE);
            isShown = false;
        }
    }
    public void update() {
        multipleLoaderLayout.update();
    }
    public void showAfterHide() {
        if(this.scrollView!=null) {
            scrollView.setVisibility(View.VISIBLE);
            isShown = true;
        }
    }
    public void addLoader() {
        multipleLoaderLayout.addTask();
        if(scrollView != null && !isShown) {
            showAfterHide();
        }
    }
    public void show() {
        if(!isShown) {
            multipleLoaderLayout.setBackgroundColor(Constants.color);
            scrollView = new ScrollView(activity);
            scrollView.addView(multipleLoaderLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            activity.addContentView(scrollView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            isShown = true;
        }
    }
}
