package com.example.csc413_volley_template.model;

import android.app.usage.UsageEvents;

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
    private String city;
    private String photo_link;
    private String members;

/**
    public static List<events> parseJson(JSONArray jsonArray) throws JSONException {
        List<events> List_events = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject jjsonobj = jsonArray.getJSONObject(i);

            List_events.add(new events(jsonArray.getJSONObject(i)));
        }


        return List_events;
    }
    **/
     public static List<events> parseJson (JSONObject jsonObject) throws JSONException{
     List<events> events = new ArrayList<>();
     if (jsonObject.has("results")){
     JSONArray jsonArray = jsonObject.getJSONArray("results");
     for (int i = 0; i<jsonArray.length(); i++){
        events.add(new events(jsonArray.getJSONObject(i)));
            }
        }
        return events;
     }


    private events(JSONObject jsonObject) throws JSONException {
        if(jsonObject.has("id")) this.setId(jsonObject.getString("id"));
        if(jsonObject.has("name")) this.setName(jsonObject.getString("name"));
        if(jsonObject.has("city")) this.setCity(jsonObject.getString("city"));
        if(jsonObject.has("members")) this.setMembers(jsonObject.getString("members"));
       // if(jsonObject.has("photo_link")) this.setPhoto_link(jsonObject.getString("photo_link"));
        if(jsonObject.getJSONObject("group_photo").has("photo_link")) this.setPhoto_link(jsonObject.getJSONObject("group_photo").getString("photo_link"));
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoto_link() {
        return photo_link;
    }

    public void setPhoto_link(String photo_link) {
        this.photo_link = photo_link;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

}