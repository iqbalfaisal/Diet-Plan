package com.example.dietplan;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
//import com.google.android.gms.maps.MapFragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Fetch extends AsyncTask<Void,Void,Void> {

    Context context;
    String data="";
    ArrayList<String> sharedid = new ArrayList<>();
    ArrayList<String> sharednames = new ArrayList<>();
    ArrayList<String> shareddays = new ArrayList<>();
    ArrayList<String> sharedcalorie = new ArrayList<>();

    public Fetch(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        sharedid=new ArrayList<>();
        sharednames=new ArrayList<>();
        shareddays=new ArrayList<>();
        sharedcalorie=new ArrayList<>();

        try {

          //  URLIP obj=new URLIP();

            URL url=new URL("http://192.168.0.102/WcfService/StudentService.svc/GetPlan");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader  bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";

            while (line!=null)
            {
                line=bufferedReader.readLine();
                data+=data+line;
            }

            JSONArray js=new JSONArray(data);
            for (int k=0;k<js.length();k++)
            {
                JSONObject jsonObject= (JSONObject) js.get(k);

               sharedid.add(""+jsonObject.getInt("id"));
                String n=jsonObject.getString("plan_name");
                n=n.replaceAll("_"," ");
                sharednames.add(n);
                sharedcalorie.add(jsonObject.getString("plan_calories"));
                shareddays.add(jsonObject.getString("plan_days"));

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;

    }




    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);


        shared_plans.sharedid=this.sharedid;
        shared_plans.sharedcalorie=this.sharedcalorie;
        shared_plans.shareddays=this.shareddays;
        shared_plans.sharednames=this.sharednames;
    }

}
