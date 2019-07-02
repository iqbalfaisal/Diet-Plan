package com.example.dietplan;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class shared_plans extends AppCompatActivity {

    ListView sharedplans;
    DBHelper dbH = new DBHelper(this);
    public static int pid;
    public static ArrayList<String> sharedid = new ArrayList<>();
    public static ArrayList<String> sharednames = new ArrayList<>();
    public static ArrayList<String> shareddays = new ArrayList<>();
    public static ArrayList<String> sharedcalorie = new ArrayList<>();
    public static ArrayList<String> sharedmealtype = new ArrayList<>();
    public static ArrayList<String> sharedplannames = new ArrayList<>();
    public static ArrayList<String> shareditemdays = new ArrayList<>();
    public static ArrayList<String> sharedfoodname = new ArrayList<>();
    public static ArrayList<String> sharedqty = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_plans);
        sharedplans=(ListView)findViewById(R.id.shared_plans_listview);
        CustomAdapter customAdapter = new CustomAdapter();
        sharedplans.setAdapter(customAdapter);

        Fetch process=new Fetch(this);
        process.execute();

       /* Cursor res = dbH.view_plans();
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {
            sharednames.add(res.getString(0));
            shareddays.add(res.getString(1));
            sharedcalorie.add(res.getString(2));

        }
        */
    }

    class CustomAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return sharednames.size();
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
            view = getLayoutInflater().inflate(R.layout.listview_shared_plans, null);
            TextView plan_name = view.findViewById(R.id.pname);
            TextView plan_days = view.findViewById(R.id.pdays);
            TextView calories_per_day = view.findViewById(R.id.pcalories);
            TextView planid= view.findViewById(R.id.pid);
          final Button downloadbutton = view.findViewById(R.id.btn_download);

            plan_name.setText(sharednames.get(position));
            plan_days.setText(shareddays.get(position));
            calories_per_day.setText(sharedcalorie.get(position));
            planid.setText(sharedid.get(position));


            downloadbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbH.diet_plan(plan_name.getText().toString(),plan_days.getText().toString(),calories_per_day.getText().toString());
                    pid=Integer.parseInt(planid.getText().toString());
                    Fetchitem process=new Fetchitem(getApplicationContext());
                    process.execute();

                    for(int i=0;i<sharedqty.size();i++)
                    {

                        dbH.table_plan_items(sharedplannames.get(i),sharedmealtype.get(i),shareditemdays.get(i),sharedfoodname.get(i),sharedqty.get(i));

                    }

                }
            });
            return view;


        }
    }
}
