package ru.ant.iot.cloud.queue;

import javax.json.JsonObject;

/**
 * Created by ant on 17.05.2016.
 */
public abstract class JsonTaskFactory {
    public abstract JsonTask getTask(JsonObject json);

}
