package com.example.dietplan;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class view_plan extends AppCompatActivity {

    ListView viewfood_listview;
    Spinner meal_type_spinner, spinner_days;
    DBHelper dbH = new DBHelper(this);
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> pictures = new ArrayList<>();
    ArrayList<String> unitArray = new ArrayList<>();
    ArrayList<String> calories = new ArrayList<>();
    ArrayList<String> qty = new ArrayList<>();
    String Selected_Days="1";
    String Selected_Meal="Breakfast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plan);
        viewfood_listview = findViewById(R.id.view_plan_listview);




        ArrayList<String> days_array_Spinner = new ArrayList<>();
        days_array_Spinner.add(0, "Select Days");

        Bundle bundle = getIntent().getExtras();

//Extract the data…
        String stuff = bundle.getString("stuff");
        String Plan_Name = bundle.getString("name");

        int resDays = Integer.parseInt(stuff);
        for (int i = 1; i <= resDays; i++) {
            days_array_Spinner.add(Integer.toString(i));
        }

        spinner_days = (Spinner) findViewById(R.id.view_days);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, days_array_Spinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_days.setAdapter(dataAdapter);
        spinner_days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Selected_Days = spinner_days.getItemAtPosition(position).toString();
                names.clear();
                CustomAdapter1 customAdapter1 = new CustomAdapter1();
                viewfood_listview.setAdapter(customAdapter1);
                Cursor res = dbH.GET_TABLE_PLAN_ITEMS(Plan_Name,Selected_Meal,Selected_Days);
                if (res.getCount() == 0) {
//                showMessage("Error","No Data Found");
                    return;
                }
                while (res.moveToNext()) {
            names.add(res.getString(3));
//                    unitArray.add(res.getString(0));

//                    calories.add(res.getString(3));
//            pictures.add(res.getString(5));
//

//                Toast.makeText(this, "helo" +names +unitArray, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        meal_type_spinner = (Spinner) findViewById(R.id.view_meal);
        ArrayAdapter<CharSequence> meal_adapter1 = ArrayAdapter.createFromResource(this, R.array.Meal_type, android.R.layout.simple_spinner_dropdown_item);

        meal_adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal_type_spinner.setAdapter(meal_adapter1);
        meal_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Selected_Meal = meal_type_spinner.getItemAtPosition(position).toString();
                names.clear();
                CustomAdapter1 customAdapter1 = new CustomAdapter1();
                viewfood_listview.setAdapter(customAdapter1);
                Cursor res = dbH.GET_TABLE_PLAN_ITEMS(Plan_Name,Selected_Meal,Selected_Days);
                if (res.getCount() == 0) {
//                showMessage("Error","No Data Found");
                    return;
                }
                while (res.moveToNext()) {
            names.add(res.getString(3));
//                    unitArray.add(res.getString(0));

//                    calories.add(res.getString(3));
//            pictures.add(res.getString(5));
//

//                Toast.makeText(this, "helo" +names +unitArray, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//



    }


    public class CustomAdapter1 extends BaseAdapter {


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


            view = getLayoutInflater().inflate(R.layout.listview_view_fooditems, null);
            ImageView imageView = view.findViewById(R.id.imageView);
            TextView cal = view.findViewById(R.id.textView_food_calories);
            TextView name = view.findViewById(R.id.textView_foodname);
            TextView unit = view.findViewById(R.id.textView_food_unit);
            TextView quanity = view.findViewById(R.id.textView_food_qty);


//            imageView.setImageResource(Integer.parseInt(pictures.get(position)));
//            cal.setText(calories.get(position));
            Cursor res =dbH.get_food(names.get(position));
//

            while (res.moveToNext()){
                cal.setText(res.getString(3));
               unit.setText(res.getString(4));
               imageView.setImageResource(Integer.parseInt(res.getString(5)));

            }

            name.setText(names.get(position));
//            unit.setText(unitArray.get(position));


            return view;


        }
    }

}
