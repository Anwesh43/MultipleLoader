package com.anwesome.ui.multipleloader;

import android.app.Activity;
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
    private MultipleLoaderLayout multipleLoaderLayout;
    public MultipleLoaderList(Activity activity) {
        this.activity = activity;
        multipleLoaderLayout = new MultipleLoaderLayout(activity);
    }
    public void hide() {
        if(this.scrollView!=null) {
            scrollView.setVisibility(View.INVISIBLE);
        }
    }
    public void showAfterHide() {
        if(this.scrollView!=null) {
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    public void addLoader() {
        multipleLoaderLayout.addTask();
    }
    public void show() {
        if(!isShown) {
            multipleLoaderLayout.setBackgroundColor(Constants.color);
            scrollView = new ScrollView(activity);
            scrollView.addView(multipleLoaderLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            isShown = true;
        }
    }
}
