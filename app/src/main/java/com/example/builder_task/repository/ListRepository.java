package com.example.builder_task.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.builder_task.model.UserListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListRepository {

    private final MutableLiveData<ArrayList<UserListModel>> allCountries;
    private final ArrayList<UserListModel> countryList;


    public ListRepository(Application application) { //application is subclass of context

        //cant call abstract func but since instance is there we can do this
        countryList = new ArrayList<>();
        allCountries = new MutableLiveData<>();

    }

    public MutableLiveData<ArrayList<UserListModel>> callAPI(Context context) {

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // creating a variable for our json object request and passing our url to it.
        String url = "https://reqres.in/api/users?page=" + 1;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            // on below line we are extracting data from our json array.
                            JSONArray dataArray = response.getJSONArray("data");

                            // passing data from our json array in our array list.
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject jsonObject = dataArray.getJSONObject(i);
                                // on below line we are extracting data from our json object.
                                UserListModel data = new UserListModel(jsonObject.getString("first_name"), jsonObject.getString("last_name"), jsonObject.getString("email"), jsonObject.getString("avatar"));

                                countryList.add(data);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        allCountries.setValue(countryList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "onErrorResponse: " + error.toString());
                // handling on error listener method.
//                Toast.makeText(MainActivity.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        // calling a request queue method
        // and passing our json object
        queue.add(jsonObjectRequest);
        return allCountries;
    }
}
