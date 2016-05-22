package ru.ant.iot.cloud.queue;

import org.apache.log4j.Logger;

import javax.json.JsonObject;

/**
 * Created by ant on 17.05.2016.
 */
public abstract class JsonTask {
    protected Logger log = Logger.getLogger(getClass());
    protected JsonObject json;

    public abstract void execute();
}
