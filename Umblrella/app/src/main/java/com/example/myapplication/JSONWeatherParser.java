package com.example.myapplication;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;

public class JSONWeatherParser {
    public static boolean notify=false;
    public  static String condition;
    static JSONArray jo;
    //public static DateFormat format = new SimpleDateFormat("yyyyy-mm-dd");
    //public static Date date = new Date();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Weather getWeather(String data) throws JSONException {
        Weather weather = new Weather();

        // We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);
        String j = jObj.getString("city");
        JSONArray WeatherAr = jObj.getJSONArray("list");
        int i;
        ArrayList<String> weatherStr = new ArrayList<String>();
        ArrayList<String> filteredweatherStr = null;
        for (i = 0; i < WeatherAr.length(); i++) {
            weatherStr.add(WeatherAr.getString(i));
            JSONObject l = new JSONObject(WeatherAr.getString(i));
//
            //  System.out.println(date);
            String x = l.getString("dt_txt");
            filteredweatherStr = new ArrayList<String>();
            if (x.contains(Date.getDate())) {
                System.out.println("##@@@@@@@@@@@@@@@@@@@@@@@@@");
                filteredweatherStr.add(l.getString("weather"));
                System.out.println(l.getString("weather"));
                String im=l.getString("weather");
                jo = new JSONArray(im);
            }
        }

            ArrayList<String> h = new ArrayList<String>();
            for (int n=0;n<jo.length();n++) {
                h.add(jo.getString(n));
                JSONObject nj =new JSONObject(h.get(n));

                System.out.println("############################");
                System.out.println(nj.getString("main"));
                condition=nj.getString("main");
                System.out.println("############################");

                if (nj.getString("main").equals("Rain")){
                    notify=true;
                }
            }

        System.out.println("---------------------------------------------------------");
        System.out.println(notify);


        // JSONArray j1 = (JSONArray) jObj.toJSONArray("weather");

        System.out.println("////////////////////////////////////////////");
        // System.out.println((date));
      //  System.out.println(jo.get("name"));
        System.out.println(WeatherAr);
        System.out.println(">>>>>>>>>>>>");
        // We start extracting the info
        Location loc = new Location();

//        JSONObject coordObj = getObject("coord", jObj);
//        loc.setLatitude(getFloat("lat", coordObj));
//        loc.setLongitude(getFloat("lon", coordObj));
//
//        JSONObject sysObj = getObject("sys", jObj);
//        loc.setCountry(getString("country", sysObj));
//        loc.setSunrise(getInt("sunrise", sysObj));
//        loc.setSunset(getInt("sunset", sysObj));
//        loc.setCity(getString("name", jObj));
//        weather.location = loc;

        // We get weather info (This is an array)
//        System.out.println(";;;;;;;;;;;;;;;;;" + jObj);
//        //  JSONArray jArr = jObj.getJSONArray("weather");
//
//        // Wind
//        JSONObject wObj = getObject("wind", jObj);
//        weather.wind.setSpeed(getFloat("speed", wObj));
//        weather.wind.setDeg(getFloat("deg", wObj));
//
//        // Clouds
//        JSONObject cObj = getObject("clouds", jObj);
//        weather.clouds.setPerc(getInt("all", cObj));

        // We download the icon to show


        return weather;
    }


    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

}
