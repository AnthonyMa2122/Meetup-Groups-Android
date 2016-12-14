package com.example.csc413_volley_template.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhib on 12/12/2016.
 */

public class events {


    private String id;
    private String name;
    private String description;


    public static List<events> parseJson(JSONArray jsonArray) throws JSONException {
        List<events> List_events = new ArrayList<>();

        //JSONArray jjsonArray = new JSONArray(jsonArray);

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject jjsonobj = jsonArray.getJSONObject(i);

                List_events.add(new events(jsonArray.getJSONObject(i)));
                //List_events.add(new events(List_events.getJSONObject(i)));

                // Create new Movie object from each JSONObject in the JSONArray
              //  List_events.add(new events(jsonArray.getJSONObject(i)));
            }


        return List_events;
    }


    private events(JSONObject jsonObject) throws JSONException {
        if(jsonObject.has("id")) this.setId(jsonObject.getString("id"));
        if(jsonObject.has("name")) this.setName(jsonObject.getString("name"));
        if(jsonObject.has("description")) this.setDescription(jsonObject.getString("description"));

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
