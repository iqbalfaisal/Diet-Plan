package com.example.dietplan;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.*;

import java.util.ArrayList;
import java.util.Calendar;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.ClientError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.util.Log;

public class existing_diet_plan extends AppCompatActivity {

    ListView view_plans;
    DBHelper dbH = new DBHelper(this);
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> days = new ArrayList<>();
    ArrayList<String> calorie = new ArrayList<>();
    private AlarmManager alarmMgr;
    private PendingIntent pendingIntent;
    Context context=existing_diet_plan.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_diet_plan);
        view_plans = (ListView) findViewById(R.id.list_view_my_plans);
        CustomAdapter customAdapter = new CustomAdapter();
        view_plans.setAdapter(customAdapter);


        Cursor res = dbH.view_plans();
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {
            names.add(res.getString(0));
            days.add(res.getString(1));
            calorie.add(res.getString(2));

        }


    }

    class CustomAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return names.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.activity_custom_plans_view, null);
            TextView plan_name = view.findViewById(R.id.plane_name);
            TextView plan_days = view.findViewById(R.id.plan_days);
            TextView calories_per_day = view.findViewById(R.id.calories_per_day);
            final Button activate = view.findViewById(R.id.btn_activate);
            final Button delete = view.findViewById(R.id.btn_delete);
            final Button share = view.findViewById(R.id.btn_share);
            final Button viewbutton = view.findViewById(R.id.btn_view);

            plan_name.setText(names.get(position));
            plan_days.setText(days.get(position));

            calories_per_day.setText(calorie.get(position));

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(existing_diet_plan.this);
                    a_builder.setMessage("Are you sure you want to delete plan?")
                            .setCancelable(false)
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String n = names.get(position);
                                    dbH.deleteRow(n);
                                    dbH.deleteItemRow(n);
                                    finish();
                                    startActivity(getIntent());

                                }
                            });
                    a_builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = a_builder.create();
                    alert.setTitle("Warning");
                    alert.show();

                }

            });
            activate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("hello", String.valueOf(activate.getText()));
                    if (String.valueOf(activate.getText()).equals("Activate")) {

                        activate.setText("Deactivate");
                        start();

                    } else if (String.valueOf(activate.getText()).equals( "Deactivate")) {

                        activate.setText("Activate");
                        if (alarmMgr != null) {
                            cancel();
                        }
                    }

                }
            });

            viewbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(existing_diet_plan.this, view_plan.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("stuff", plan_days.getText().toString());

                    bundle.putString("name", plan_name.getText().toString());

//Add the bundle to the intent
                    i.putExtras(bundle);
                    startActivity(i);
                }
            });
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String pn = plan_name.getText().toString();
                    String pd = plan_days.getText().toString();
                    String pc = calories_per_day.getText().toString();

                    shareplan(pn, pd, pc);
                    shareplanitem(pn);
                }
            });

            return view;
        }
    }
    public void start() {
        Toast.makeText(context, "Activated", Toast.LENGTH_SHORT).show();

        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);

        Calendar firstTurn = Calendar.getInstance();
        Calendar secondTurn = Calendar.getInstance();
        Calendar thirdTurn = Calendar.getInstance();

        // set times
        firstTurn.set(Calendar.HOUR_OF_DAY, 14);
        firstTurn.set(Calendar.MINUTE, 35);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        secondTurn.set(Calendar.HOUR_OF_DAY, 14);
        secondTurn.set(Calendar.MINUTE, 16);
        PendingIntent alarmIntent2 = PendingIntent.getBroadcast(context, 2, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        thirdTurn.set(Calendar.HOUR_OF_DAY, 14);
        thirdTurn.set(Calendar.MINUTE,18);
        PendingIntent alarmIntent3 = PendingIntent.getBroadcast(context, 3, intent, PendingIntent.FLAG_CANCEL_CURRENT);

//        alarmMgr.cancel(alarmIntent);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstTurn.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
//        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, secondTurn.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent2);
//        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, thirdTurn.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent3);
    }

    public void cancel() {
        Toast.makeText(context, "Deactivated", Toast.LENGTH_SHORT).show();

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }


    public void shareplan(String pn, String pd, String pc) {
        // String lin ="http://192.168.0.102/WcfService/StudentService.svc/SetPlanItem/newplan212/lunch/1/apple/2/";
        pn = pn.replaceAll("\\s+", "_");

        String lin = "http://192.168.0.102/WcfService/StudentService.svc//SetPlan/" + pn + "/" + pd + "/" + pc;

        Log.i("URI", lin);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, lin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(existing_diet_plan.this.getApplicationContext(), "Saves", Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(existing_diet_plan.this.getApplicationContext(), "Error Occured" + error.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        requestQueue.start();

    }

    public void shareplanitem(String pn) {
        // /*
        ArrayList<String> n = new ArrayList<String>();
        ArrayList<String> mt = new ArrayList<String>();
        ArrayList<String> d = new ArrayList<String>();
        ArrayList<String> fn = new ArrayList<String>();
        ArrayList<String> q = new ArrayList<String>();


        Cursor res = dbH.GET_ALL_ITEMS_PLAN(pn);
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {
            if (res.getString(0) != null) {

                n.add(res.getString(0).toString());
                mt.add(res.getString(1).toString());
                d.add(res.getString(2).toString());
                fn.add(res.getString(3).toString());
                q.add(res.getString(4).toString());
            }
        }
        for (int i = 0; i < n.size(); i++) {
            String n1 = n.get(i);
            String n2 = mt.get(i);
            String n3 = d.get(i);
            String n4 = fn.get(i);
            String n5 = q.get(i);
            n1 = n1.replaceAll("\\s+", "_");
            n2 = n2.replaceAll("\\s+", "_");
            n3 = n3.replaceAll("\\s+", "_");
            n4 = n4.replaceAll("\\s+", "_");
            n5 = n5.replaceAll("\\s+", "_");
            //*/    String lin ="http://192.168.0.102/WcfService/StudentService.svc/SetPlanItem/newplan 400/lunch/1/apple/2/";
            String lin = "http://192.168.0.102/WcfService/StudentService.svc/SetPlanItem/" + n1 + "/" + n2 + "/" + n3 + "/" + n4 + "/" + n5 + "/";
            Log.i("URI", lin);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, lin, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(existing_diet_plan.this.getApplicationContext(), "Saves", Toast.LENGTH_SHORT).show();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(existing_diet_plan.this.getApplicationContext(), "Error Occured" + error.getMessage(), Toast.LENGTH_LONG).show();
                }

            });

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
            requestQueue.start();


        }


    }
}
