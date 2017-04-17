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
    private TaskRunner taskRunner = new TaskRunner();
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
    public void startTaskRunner() {
        taskRunner.start();
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
    public void addTask(CustomLoader customLoader,OnTaskUpdateListener onTaskUpdateListener) {
        loaderTasks.add(new LoaderTask(customLoader,onTaskUpdateListener));
        if(loaderTasks.size() == 1) {
            initOrUpdateCurrentTask();
            startTaskRunner();
        }
    }
    public MultipleLoader(MultipleLoaderLayout layout) {
        this.layout = layout;
    }
    private class TaskRunner {
        public void start() {
            while(loaderTasks.size() != 0) {
                currTask.update();
                if(currTask.isDone()) {
                    removeCurrentTask();
                }
            }
        }
    }
    private class LoaderTask {
        private OnTaskUpdateListener onTaskUpdateListener;
        private CustomLoader customLoader;
        public OnTaskUpdateListener getOnTaskUpdateListener() {
            return onTaskUpdateListener;
        }
        public LoaderTask(CustomLoader customLoader,OnTaskUpdateListener onTaskUpdateListener) {
            this.customLoader = customLoader;
            this.onTaskUpdateListener = onTaskUpdateListener;
        }
        public boolean isDone() {
            return customLoader.isDone();
        }
        public void update() {
            if(onTaskUpdateListener!=null) {
                float factor =onTaskUpdateListener.getFactor();
                if (factor <= 1) {
                    customLoader.update(factor);
                }
            }
        }
    }
    public interface OnTaskUpdateListener {
        float getFactor();
    }
}
