package ru.ant.iot.cloud.queue;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import javax.json.JsonObject;
import java.io.IOException;

/**
 * Created by ant on 17.05.2016.
 */
public class JsonTaskTrigger implements Runnable {

    private Logger log = Logger.getLogger(getClass());
    private final String taskQueueCloudKey;
    private final JsonTaskFactory jsonTaskFactory;

    public JsonTaskTrigger(String taskQueueCloudKey, JsonTaskFactory jsonTaskFactory) {
        this.taskQueueCloudKey = taskQueueCloudKey;
        this.jsonTaskFactory = jsonTaskFactory;
    }

    @Override
    public void run() {
        if(!CloudQueue.isEnabled()) return;

        String status = "data";
        while(!status.equals("EmptyQueue")){
            try {
                JsonObject json = pollJsonTaskFromCloud();
                status = json.getString("status");
                if(!status.equals("data")) continue;
                JsonTask task = jsonTaskFactory.getTask(getDataObject(json));
                if(task != null) task.execute();
            } catch (IOException e) {
                log.error("JSON read error", e);
            }catch (Exception e){
                log.error(e);
            }
        }
    }

    protected JsonObject getDataObject(JsonObject json) {
        return json.getJsonObject("data");
    }

    private JsonObject pollJsonTaskFromCloud() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(getTaskQueueUrl());
        get.setHeader(HttpHeaders.ACCEPT, "application/json; charset=UTF-8");
        HttpResponse response = client.execute(get);
        if (response.getStatusLine().getStatusCode() != 200) {
            log.error("Failed : HTTP error code : "+ response.getStatusLine().getStatusCode());
            return null;
        }
        return JsonUtils.readJsonData(response);
    }

    public String getTaskQueueUrl() {
        return String.format("http://queue.msensk.ru/task-queue?key=%1$s&direction=get", taskQueueCloudKey);
    }

}
