package ru.ant.iot.cloud.queue;

/**
 * Created by ant on 19.05.2016.
 */
public class CloudQueue {
    private static CloudQueue ourInstance = new CloudQueue();
    public static CloudQueue getInstance() {
        return ourInstance;
    }

    private boolean isEnabled = false;

    private CloudQueue() {
    }

    public static boolean isEnabled() {
        return ourInstance.isEnabled;
    }

    public static void toggleActivity(){
        ourInstance.isEnabled = !ourInstance.isEnabled;
    }
}
