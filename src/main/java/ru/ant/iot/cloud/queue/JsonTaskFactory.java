package ru.ant.iot.cloud.queue;

import org.apache.log4j.Logger;

import javax.json.JsonObject;

/**
 * Created by ant on 17.05.2016.
 */
public abstract class JsonTaskFactory {
    protected Logger log = Logger.getLogger(getClass());
    public abstract JsonTask getTask(JsonObject json);

}
