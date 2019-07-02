package com.example.dietplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    DBHelper dbHelper;
    static  private int temp;
    Button btnNewInstalled;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        btnNewInstalled = findViewById(R.id.btnnewinstalled);
        Button bmi=(Button)findViewById(R.id.button_bmi);
        Button create=(Button)findViewById(R.id.button_create);
        Button existing=(Button)findViewById(R.id.button_existing_plans);
        Button add=(Button)findViewById(R.id.add_items);
        Button share=(Button)findViewById(R.id.shared_plans);

        dbHelper=new DBHelper(this);

        if(btnNewInstalled.getText().equals("New")) {

        }


        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i=new Intent(HomePage.this,BMI.class);
                startActivity(i);
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomePage.this,create_plan.class);
                startActivity(i);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomePage.this,add_food.class);
                startActivity(i);
            }
        });
        existing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomePage.this,existing_diet_plan.class);
                startActivity(i);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomePage.this,shared_plans.class);
                startActivity(i);
            }
        });
    }
}
