package com.example.dietplan;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class create_plan extends AppCompatActivity {


    EditText name,days,calories;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
        Button done=(Button)findViewById(R.id.done);
         name =(EditText) findViewById(R.id.name);
         days =(EditText) findViewById(R.id.days);
         calories =(EditText) findViewById(R.id.calories);
        dbHelper=new DBHelper(this);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String id="";
               boolean show= dbHelper.diet_plan(name.getText().toString(),days.getText().toString(),calories.getText().toString());
                if(show==true)
                {
                    Toast.makeText(getApplicationContext(),"Plan Added",Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(getApplicationContext(),"Not Added",Toast.LENGTH_SHORT).show();
                }


                Intent i=new Intent(create_plan.this,select_food.class);
               EditText days=(EditText)findViewById(R.id.days);
                String getdays=days.getText().toString();
                //Create the bundle
                Bundle bundle = new Bundle();

//Add your data to bundle
                bundle.putString("stuff", getdays);

                bundle.putString("name", name.getText().toString());

//Add the bundle to the intent
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }
}
