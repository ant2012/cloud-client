package ru.ant.iot.cloud.queue;

import ru.ant.common.Loggable;

/**
 * Created by ant on 17.05.2016.
 */
public abstract class JsonTask extends Loggable{
    public abstract void execute();
}
