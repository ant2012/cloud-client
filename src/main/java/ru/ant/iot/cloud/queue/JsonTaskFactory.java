package ru.ant.iot.cloud.queue;

import ru.ant.common.Loggable;

import javax.json.JsonObject;

/**
 * Created by ant on 17.05.2016.
 */
public abstract class JsonTaskFactory extends Loggable{
    public abstract JsonTask getTask(JsonObject json);

}
