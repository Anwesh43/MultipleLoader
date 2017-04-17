package com.anwesome.ui.multipleloader;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 17/04/17.
 */
public class MultipleLoader {
    private ConcurrentLinkedQueue<LoaderTask> loaderTasks = new ConcurrentLinkedQueue<>();
    private MultipleLoaderLayout layout;
    private boolean allLoadersDone = true;
    private LoaderTask currTask;
    private int speed;
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void initOrUpdateCurrentTask() {
        for(LoaderTask loaderTask:loaderTasks) {
            currTask = loaderTask;
            break;
        }
    }

    public void removeCurrentTask() {
        CustomLoader customLoader = currTask.customLoader;
        loaderTasks.remove(currTask);
        layout.removeLoader(customLoader);
        if(loaderTasks.size()>0) {
            initOrUpdateCurrentTask();
        }
        else {
            layout.hide();
        }
    }
    public boolean isAllLoadersDone() {
        return allLoadersDone;
    }
    public void addTask(CustomLoader customLoader) {
        loaderTasks.add(new LoaderTask(customLoader));
        if(loaderTasks.size() == 1) {
            initOrUpdateCurrentTask();
        }
    }
    public MultipleLoader(MultipleLoaderLayout layout) {
        this.layout = layout;
    }
    public void update(){
        if(loaderTasks.size() != 0 && currTask!=null) {
            currTask.update();
            if (currTask.isDone()) {
                removeCurrentTask();
            }
        }
    }
    private class LoaderTask {
        private CustomLoader customLoader;
        private float value = 0.0f,maxValue = 100.0f;
        public LoaderTask(CustomLoader customLoader) {
            this.customLoader = customLoader;
        }
        public boolean isDone() {
            return value >= maxValue;
        }
        public void update() {
            if (value < maxValue) {
                value+=speed;
                float factor = value/maxValue;
                customLoader.update(factor);
            }
        }
    }
}
