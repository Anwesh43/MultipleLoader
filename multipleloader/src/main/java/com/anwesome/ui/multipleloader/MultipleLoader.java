package com.anwesome.ui.multipleloader;

/**
 * Created by anweshmishra on 17/04/17.
 */
public class MultipleLoader {
    private class LoaderTask {
        private CustomLoader customLoader;
        private float value = 0.0f,maxValue  = 100.0f;
        public LoaderTask(CustomLoader customLoader) {
            this.customLoader = customLoader;
        }
        public boolean isDone() {
            return value == maxValue;
        }
        public void update() {
            if(value<maxValue) {
                value++;
                customLoader.update((value) / maxValue);
            }
        }
    }
}
