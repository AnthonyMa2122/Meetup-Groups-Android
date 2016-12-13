package com.example.csc413_volley_template.controller;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.csc413_volley_template.app.App;
import com.example.csc413_volley_template.model.events;
import com.example.csc413_volley_template.request.JsonRequest;
import com.example.csc413_volley_template.volley.VolleySingleton;

import java.util.List;

/*
 * Created by abhijit on 12/2/16.
 */

/**
 * <p> Provides interface between {@link android.app.Activity} and {@link com.android.volley.toolbox.Volley} </p>
 */
public class JsonController {

    private final int TAG = 100;

    private OnResponseListener responseListener;

    /**
     *
     * @param responseListener  {@link OnResponseListener}
     */
    public JsonController(OnResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    /**
     * Adds request to volley request queue
     * @param query query term for search
     */
    public void sendRequest(String query){

        // Request Method
        int method = Request.Method.GET;

        // Url with GET parameters
        //String url = "http://www.omdbapi.com/?s=" + Uri.encode(query) + "&t=movie";

       // String url = "https://api.meetup.com/find/topics?key=693b2553836553f1d95886f554618&sign=true&photo-host=public&query="+ query;
        String url = "https://api.meetup.com/find/groups?key=693b2553836553f1d95886f554618&text="+ query + "&page=20";
        //https://api.meetup.com/find/groups?key=693b2553836553f1d95886f554618&text=hiking&page=20


        //String url = "http://pokeapi.co/api/v2/pokemon/" + query;     pokemon


        //String url = "https://api.meetup.com/find/events?photo-host=public&sig_id=217757204&sig=b4a832a2905dfd0f24dde2370ef9ecc8c7a6d00e";
            // ^meet up

        //String url = "https://api.imgur.com/3/image/{id}\n"; imgur, type in id part???
        // Create new request using JsonRequest
        JsonRequest request
            = new JsonRequest(
                method,
                url,
                new Response.Listener<List<events>>() {
                    @Override
                   public void onResponse(List<events> eventss ) {
                        responseListener.onSuccess(eventss); //in main activity
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseListener.onFailure(error.getMessage());
                    }
                }
        );

        // Add tag to request
        request.setTag(TAG);

        // Get RequestQueue from VolleySingleton
        VolleySingleton.getInstance(App.getContext()).addToRequestQueue(request);
    }

    /**
     * <p>Cancels all request pending in request queue,</p>
     * <p> There is no way to control the request already processed</p>
     */
    public void cancelAllRequests() {
        VolleySingleton.getInstance(App.getContext()).cancelAllRequests(TAG);
    }

    /**
     *  Interface to communicate between {@link android.app.Activity} and {@link JsonRequest}
     *  <p>Object available in {@link JsonRequest} and implemented in {@link com.example.csc413_volley_template.MainActivity}</p>
     */
    public interface OnResponseListener {
        void onSuccess(List<events> eventss);
        void onFailure(String errorMessage);
    }

}