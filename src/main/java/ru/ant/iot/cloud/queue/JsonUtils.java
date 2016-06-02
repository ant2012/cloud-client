package ru.ant.iot.cloud.queue;

import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by ant on 17.05.2016.
 */
public class JsonUtils {
    public static JsonObject readJsonData(HttpServletRequest request) throws IOException {
        return readJsonData(Json.createReader(request.getReader()));
    }

    public static JsonObject readJsonData(HttpResponse response) throws IOException {
        return readJsonData(Json.createReader(response.getEntity().getContent()));
    }

    public static JsonObject readJsonData(String src) throws IOException {
        return readJsonData(Json.createReader(new StringReader(src)));
    }

    private static JsonObject readJsonData(JsonReader jsonReader) {
        try{
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            return jsonObject;
        }catch(JsonParsingException e){
            Logger.getLogger(JsonUtils.class).error("Cannot parse JSON data", e);
            return null;
        }
    }
}
