package com.example.csc413_volley_template.request;

/*
 * Created by abhijit on 12/2/16.
 */

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.csc413_volley_template.model.events;
import com.example.csc413_volley_template.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Volley request to receive JSON as response and parse it to create list of movies
 */
public class JsonRequest extends Request<List<events>> {

    // Success listener implemented in controller
    private Response.Listener<List<events>> successListener;

    /**
     * Class constructor
     * @param method            Request method
     * @param url               url to API
     * @param successListener   success listener
     * @param errorListener     failure listener
     */
    public JsonRequest(int method,
                       String url,
                       Response.Listener<List<events>> successListener,
                       Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.successListener = successListener;
    }

    @Override
    protected Response<List<events>> parseNetworkResponse(NetworkResponse response) {
        // Convert byte[] data received in the response to String
        String jsonString = new String(response.data);      //START HERE, get the string
        List<Movie> movies;
        JSONObject jsonObject;
        List<events> hold_eventers;

        JSONArray jsonArray;

        Log.i(this.getClass().getName(), jsonString);
        // Try to convert JsonString to list of movies
        try {
            // Convert JsonString to JSONObject
          //  jsonObject = new JSONObject(jsonString);    //meetup starts with an array so use
            // Get list of movies from received JSON        //jsonarrya = new jsonnarray(stringthingy)
            //movies = Movie.parseJson(jsonObject);


            jsonArray = new JSONArray(jsonString);
            hold_eventers = events.parseJson(jsonArray);



        }
        // in case of exception, return volley error
        catch (JSONException e) {
            e.printStackTrace();
            // return new volley error with message
            return Response.error(new VolleyError("Failed to process the request"));
        }
        // return list of movies
        //return Response.success(movies, getCacheEntry());

        return Response.success(hold_eventers, getCacheEntry()); //changed movies into hold-eventers
    }

    @Override
    protected void deliverResponse(List<events> movies) {
        successListener.onResponse(movies);
    }
}
