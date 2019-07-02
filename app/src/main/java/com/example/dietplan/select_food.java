package com.example.dietplan;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class select_food extends AppCompatActivity {
    ListView selectfood_listview;
    Spinner food_category_spinner, days_spinner,meal_type_spinner,spinner_copy_from,spinner_copy_to;
    String value,days_value;
    String Fruits = "Fruits";
    String Soup = "Soup";
    Button create,save;
    CheckBox checkBox;
    DBHelper dbH = new DBHelper(this);
    ArrayList<String> selected_item = new ArrayList<>();
    CustomAdapter1 mListAdapter=new CustomAdapter1( );
    MyRecyclerViewAdapter adapter;
    String Plan_Name="";
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> pictures = new ArrayList<>();
    ArrayList<String> unitArray = new ArrayList<>();
    ArrayList<String> calories = new ArrayList<>();

    ArrayList<String> selectedFoodnames = new ArrayList<>();
    ArrayList<String> selectedFoodunit = new ArrayList<>();
    ArrayList<String> selectedFoodcalories = new ArrayList<>();
    ArrayList<String> selectedFoodpics = new ArrayList<>();
    String Selected_Days;
     String Selected_Meal;
     String Selected_Food;
    String copy_from,copy_to;

Map<String,String> ListSelectedFoodItem=new HashMap<String, String>();
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_food);
        checkBox = findViewById(R.id.checkBox_food);
        create = findViewById(R.id.submit);

        save = findViewById(R.id.save);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = dbH.GET_TABLE_PLAN_ITEMS_FOR_COPY_PASTE(Plan_Name,copy_from);
                if (res.getCount() == 0) {
//                showMessage("Error","No Data Found");
                    return;
                }
                while (res.moveToNext()) {
                    dbH.table_plan_items(Plan_Name, res.getString(1), copy_to, res.getString(3), res.getString(4));

//                Toast.makeText(this, "helo" +names +unitArray, Toast.LENGTH_SHORT).show();

                }
            }
        });


        final List<String> days_array_Spinner = new ArrayList<>();
        days_array_Spinner.add(0,"Select Day");


        selectfood_listview = findViewById(R.id.list_view_select_food);

        food_category_spinner = (Spinner) findViewById(R.id.spinner_select_food_categories);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.add_food_category, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        food_category_spinner.setAdapter(adapter);

//         copy days from spinner start

        spinner_copy_from = (Spinner) findViewById(R.id.spinner_copy_from);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, days_array_Spinner);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_copy_from.setAdapter(dataAdapter1);
        spinner_copy_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                copy_from = spinner_copy_from.getItemAtPosition(position).toString();
//
//                Toast.makeText(parent.getContext(),
//                        "Days : " + parent.getItemAtPosition(position).toString(),
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//copy from spinner end


        //         copy days to spinner start

        spinner_copy_to = (Spinner) findViewById(R.id.spinner_copy_to);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, days_array_Spinner);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_copy_to.setAdapter(dataAdapter2);
        spinner_copy_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                copy_to = spinner_copy_to.getItemAtPosition(position).toString();
//
//                Toast.makeText(parent.getContext(),
//                        "Days : " + parent.getItemAtPosition(position).toString(),
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//copy to spinner end


        days_spinner = (Spinner) findViewById(R.id.spinner_days);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, days_array_Spinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        days_spinner.setAdapter(dataAdapter);

        meal_type_spinner = (Spinner) findViewById(R.id.spinner_meal);
        ArrayAdapter<CharSequence> meal_adapter = ArrayAdapter.createFromResource(this, R.array.Meal_type, android.R.layout.simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal_type_spinner.setAdapter(meal_adapter);


        food_category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Selected_Food = food_category_spinner.getItemAtPosition(position).toString();


                value = food_category_spinner.getItemAtPosition(position).toString();

                names.clear();
                calories.clear();
                unitArray.clear();
                pictures.clear();

                CustomAdapter1 customAdapter1 = new CustomAdapter1();
                selectfood_listview.setAdapter(customAdapter1);
                Cursor res = dbH.getnewData(value);
                if (res.getCount() == 0) {
//                showMessage("Error","No Data Found");
                    return;
                }
                while (res.moveToNext()) {


                    names.add(res.getString(2));
                    unitArray.add(res.getString(4));

                    calories.add(res.getString(3));
                    pictures.add(res.getString(5));
                    mListAdapter.notifyDataSetChanged();

//                Toast.makeText(this, "helo" +names +unitArray, Toast.LENGTH_SHORT).show();

                }


//            showMessage("Data",buffer.toString());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        days_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Selected_Days = days_spinner.getItemAtPosition(position).toString();
//
//                Toast.makeText(parent.getContext(),
//                        "Days : " + parent.getItemAtPosition(position).toString(),
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        meal_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Selected_Meal = meal_type_spinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



//Get the bundle
        Bundle bundle = getIntent().getExtras();

//Extract the data…
        String stuff = bundle.getString("stuff");
        Plan_Name = bundle.getString("name");

        int res = Integer.parseInt(stuff);
        for (int i = 1; i <= res; i++) {
            days_array_Spinner.add(Integer.toString(i));
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              if(Selected_Days!="Select Day") {
                  Toast.makeText(getApplicationContext(),"Items"+ListSelectedFoodItem.size()+"  ",Toast.LENGTH_LONG).show();
                  names.clear();

//                  ListSelectedFoodItem.forEach((k, value) -> {
//                      Toast.makeText(getApplicationContext(),"Items"+k+"  "+value,Toast.LENGTH_LONG).show();
////
//                      dbH.TABLE_PLAN_ITEMS(Plan_Name, Selected_Meal, Selected_Days, k, value);
//
//                  });
//                  for (Map.Entry<String, String> entry : ListSelectedFoodItem.entrySet()) {
//                      String key = entry.getKey();
//                      String value = entry.getValue();
//                      Toast.makeText(getApplicationContext(),"Items"+key+"  "+value,Toast.LENGTH_LONG).show();
//
//                      dbH.TABLE_PLAN_ITEMS(Plan_Name, Selected_Meal, Selected_Days, key, value);
//                  }

                  Iterator<Map.Entry<String, String>> it = ListSelectedFoodItem.entrySet().iterator();

                  while (it.hasNext()) {
                      Map.Entry<String, String> entry = it.next();
                      String key = entry.getKey();
                      String value = entry.getValue();
                      Toast.makeText(getApplicationContext(),"Items"+key+"  "+value,Toast.LENGTH_LONG).show();

                      dbH.table_plan_items(Plan_Name, Selected_Meal, Selected_Days, key, value);
                  }



                  Toast.makeText(getApplicationContext(),"Item Added",Toast.LENGTH_LONG).show();

              }
                else{
                  Toast.makeText(getApplicationContext(),"Please Select your Days",Toast.LENGTH_LONG).show();

              }
      //          db.execSQL("create table "+TABLE_PLAN_ITEMS+"(pid int,meal_type text,day int,food_name text,qty int, FOREIGN KEY(pid)REFERENCES TABLE_DIET_PLAN (oid))");
            }
        });


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


            view = getLayoutInflater().inflate(R.layout.custom_listview, null);
            ImageView imageView = view.findViewById(R.id.imageView);
            TextView cal = view.findViewById(R.id.textView_food_calories);
            TextView name = view.findViewById(R.id.textView_foodname);
            TextView unit = view.findViewById(R.id.textView_food_unit);
            TextView quanity = view.findViewById(R.id.textView_food_qty);
            final EditText quanityNumber=view.findViewById(R.id.editText_food_qty);
            final CheckBox selectFood=view.findViewById(R.id.checkBox_food);
            selectFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(selectFood.isChecked())
                    {
                        AddItemInArray(names.get(position),unitArray.get(position));
                    }
                    else{
                        RemoveItemInArray(names.get(position),quanityNumber.getText().toString());

                    }

                    }
            });


//            Toast.makeText(select_food.this, ""+selectedFoodnames+selectedFoodcalories+selectedFoodunit, Toast.LENGTH_SHORT).show();

            imageView.setImageResource(Integer.parseInt(pictures.get(position)));
            cal.setText(calories.get(position));
            name.setText(names.get(position));
            unit.setText(unitArray.get(position));

            return view;


        }
    }

    private void RemoveItemInArray(String s, String s1) {
        ListSelectedFoodItem.remove(s);
        Toast.makeText(getApplicationContext(),"value of s"+s  ,Toast.LENGTH_LONG).show();

    }

    private void AddItemInArray(String selectedFoodnames, String selectedFoodunit) {
        ListSelectedFoodItem.put(selectedFoodnames,selectedFoodunit);
        Toast.makeText(getApplicationContext(),"s"+ selectedFoodnames+selectedFoodunit ,Toast.LENGTH_LONG).show();
    }


}
