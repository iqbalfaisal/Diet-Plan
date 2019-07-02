package com.example.dietplan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class add_food extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView mImagevie;
    EditText foodname,foodcalorie,foodunit;
    Spinner categoryspinner;
    Button Add_food;
    String spinnervalue;
    String Fruits="Fruits";
    String Vegetables="Vegetables";
    String Soup="Soup";
    String Beverages="Beverages";
    String Dairy="Dairy";
    String Sea_Food="Sea Food";
    String Junk_Food="Junk Food";
    String Chicken="Chicken";
    String Mutton="Mutton";
    String Beef="Beef";
    Integer id=0;


    DBHelper dbHelper;
    private static final int image_pick_code=1000;
    private static final int permission_code=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        Button upload=(Button)findViewById(R.id.btn_upload);
        Add_food=(Button)findViewById(R.id.btn_add_food);
         mImagevie=(ImageView)findViewById(R.id.image_view);
         foodname=(EditText) findViewById(R.id.food_name);
        foodcalorie=(EditText) findViewById(R.id.food_calorie);
        foodunit=(EditText) findViewById(R.id.food_unit);
        categoryspinner=(Spinner)findViewById(R.id.spinner_add_food_category);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.add_food_category,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryspinner.setAdapter(adapter);
        categoryspinner.setOnItemSelectedListener(this);

        dbHelper=new DBHelper(this);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
                    {
                        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,permission_code);
                    }
                    else
                    {
                        pickImageFromGallery();
                    }
                }
                else
                {
                    pickImageFromGallery();
                }

            }
        });






        Add_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(spinnervalue.equals(Fruits))
                {
                    id=1;
                }
                if(spinnervalue.equals(Vegetables))
                {
                    id=2;
                }
                if(spinnervalue.equals(Soup))
                {
                    id=3;
                }
                if(spinnervalue.equals(Beverages))
                {
                    id=4;
                }
                if(spinnervalue.equals(Dairy))
                {
                    id=5;
                }
                if(spinnervalue.equals(Sea_Food))
                {
                    id=6;
                }
                if(spinnervalue.equals(Junk_Food))
                {
                    id=7;
                }
                if(spinnervalue.equals(Chicken))
                {
                    id=8;
                }
                if(spinnervalue.equals(Mutton))
                {
                    id=9;
                }
                if(spinnervalue.equals(Beef))
                {
                    id=10;
                }

                boolean show=dbHelper.insert_into_food_items(id.toString(),spinnervalue,foodname.getText().toString(),foodcalorie.getText().toString(),foodunit.getText().toString(),12);
                if(show==true)
                {
                    Toast.makeText(getApplicationContext(),"Food Item Added",Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(getApplicationContext(),"Not Added",Toast.LENGTH_SHORT).show();
                }

            }
        });




    }



    private void pickImageFromGallery() {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,image_pick_code);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case permission_code:{
                if(grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else{
                    Toast.makeText(this,"Permission Denied....",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==RESULT_OK && requestCode==image_pick_code)
        {
            mImagevie.setImageURI(data.getData());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnervalue=categoryspinner.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
