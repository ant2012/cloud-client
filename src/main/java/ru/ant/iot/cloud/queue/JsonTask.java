package ru.ant.iot.cloud.queue;

import ru.ant.common.Loggable;

import javax.json.JsonObject;

/**
 * Created by ant on 17.05.2016.
 */
public abstract class JsonTask extends Loggable{
    protected JsonObject json;

    public abstract void execute();
}
