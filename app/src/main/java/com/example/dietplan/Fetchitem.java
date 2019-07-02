package com.example.dietplan;

import android.content.Context;
import android.os.AsyncTask;

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

//import com.google.android.gms.maps.MapFragment;

public class Fetchitem extends AsyncTask<Void,Void,Void> {

    Context context;
    String data="";
    ArrayList<String> sharedmealtype = new ArrayList<>();
    ArrayList<String> sharedplannames = new ArrayList<>();
    ArrayList<String> shareditemdays = new ArrayList<>();
    ArrayList<String> sharedfoodname = new ArrayList<>();
    ArrayList<String> sharedqty = new ArrayList<>();

    public Fetchitem(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        sharedmealtype=new ArrayList<>();
        sharedplannames=new ArrayList<>();
        shareditemdays=new ArrayList<>();
        sharedfoodname=new ArrayList<>();
        sharedqty=new ArrayList<>();

        try {

          //  URLIP obj=new URLIP();

            URL url=new URL("http://192.168.0.102/WcfService/StudentService.svc/GetPlanItem/"+shared_plans.pid);
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
                String n1=jsonObject.getString("plan_name");
                n1=n1.replaceAll("_"," ");
                String n2=jsonObject.getString("meal_typ");
                n2=n2.replaceAll("_"," ");
                String n3=jsonObject.getString("day");
                n3=n3.replaceAll("_"," ");
                String n4=jsonObject.getString("food_name");
                n4=n4.replaceAll("_"," ");
                String n5=jsonObject.getString("qty");
                n5=n5.replaceAll("_"," ");

                sharedmealtype.add(n2);
                sharedplannames.add(n1);
                shareditemdays.add(n3);
                sharedfoodname.add(n4);
                sharedqty.add(n5);

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


        shared_plans.sharedmealtype=this.sharedmealtype;
        shared_plans.sharedplannames=this.sharedplannames;
        shared_plans.shareditemdays=this.sharedplannames;
        shared_plans.sharedfoodname=this.sharedfoodname;
        shared_plans.sharedqty=this.sharedqty;
    }

}
