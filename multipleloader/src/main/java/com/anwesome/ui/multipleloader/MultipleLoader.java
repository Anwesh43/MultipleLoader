package com.anwesome.ui.multipleloader;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 17/04/17.
 */
public class MultipleLoader {
    private ConcurrentLinkedQueue<LoaderTask> loaderTasks = new ConcurrentLinkedQueue<>();
    private int currIndex = 0;
    private MultipleLoaderLayout layout;
    private boolean allLoadersDone = true;
    private LoaderTask currTask;
    public void initOrUpdateCurrentTask() {
        int i = 0;
        for(LoaderTask loaderTask:loaderTasks) {
            if(i == currIndex) {
                currTask = loaderTask;
                break;
            }
            if(i == loaderTasks.size()-1) {
                currTask = null;
                allLoadersDone = true;
            }
            i++;
        }
    }

    public void removeCurrentTask() {
        CustomLoader customLoader = currTask.customLoader;
        loaderTasks.remove(currTask);
        layout.removeLoader(customLoader);
        if(loaderTasks.size()>0) {
            currIndex++;
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
        if(loaderTasks.size() != 0) {
            currTask.update();
            if (currTask.isDone()) {
                removeCurrentTask();
            }
        }
    }
    private class LoaderTask {
        private CustomLoader customLoader;
        private int value = 0,maxValue = 100;
        public LoaderTask(CustomLoader customLoader) {
            this.customLoader = customLoader;
        }
        public boolean isDone() {
            return value == maxValue;
        }
        public void update() {
            if (value < maxValue) {
                value++;
                float factor = value/maxValue;
                customLoader.update(factor);
            }
        }
    }
}
