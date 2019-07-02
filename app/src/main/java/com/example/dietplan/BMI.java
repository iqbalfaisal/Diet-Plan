package com.example.dietplan;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BMI extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    RadioButton male,female;
    Button cal;
    EditText waist,height,weight;
    TextView tview;
    Spinner spinner;
    String value;
    String verylight="1.30(Very Light)";
    String light="1.55(Light)";
    String moderate="1.65(Moderate)";
    String heavy="1.80(Heavy)";
    String veryheavy="2.00(Very Heavy)";
    Double level;
    Double male_fat;
    Double female_fat;
    Double final_weight;
    Double reduce,gain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        cal=(Button)findViewById(R.id.calculate);
        waist=(EditText) findViewById(R.id.waist);
        height=(EditText) findViewById(R.id.height);
        weight=(EditText) findViewById(R.id.weight);
        male=(RadioButton)findViewById(R.id.male);
        female=(RadioButton)findViewById(R.id.female);
        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.activity_level,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder sb=new StringBuilder("Fill all fields");


                if (waist.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),sb.toString(),Toast.LENGTH_LONG).show();
                }
                if (height.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),sb.toString(),Toast.LENGTH_LONG).show();
                }
                if (weight.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),sb.toString(),Toast.LENGTH_LONG).show();
                }
                if(male.isChecked())
                {

                    String h=height.getText().toString();
                    String w=waist.getText().toString();
                    String Wt=weight.getText().toString();
                    Double weight=Double.parseDouble(Wt);
                    final_weight=weight*24;
                    Double height=Double.parseDouble(h);
                    Double height_1=height*2.54;
                    Double waist=Double.parseDouble(w);
                    Double waist_1=waist*2.54;
                    male_fat=64-(20*(height_1/waist_1));



                    if(male_fat>=10.0&&male_fat<=14.9)
                    {
                        final_weight=final_weight*1.0;
                        if(value.equals(verylight))
                        {
                            level=1.30;
                            final_weight=final_weight*1.30;

                        }
                        if(value.equals(light))
                        {
                            level=1.55;
                            final_weight=final_weight*1.55;

                        }
                        if(value.equals(moderate))
                        {
                            level=1.65;
                            final_weight=final_weight*1.65;
                            tview.setText(final_weight.toString());
                        }
                        if(value.equals(heavy))
                        {
                            level=1.80;
                            final_weight=final_weight*1.80;

                        }
                        if(value.equals(veryheavy))
                        {
                            level=2.00;
                            final_weight=final_weight*2.00;

                        }
                    }
                    if(male_fat>=15.0&&male_fat<=20.9)
                    {
                        final_weight=final_weight*0.95;

                        if(value.equals(verylight))
                        {
                            level=1.30;
                            final_weight=final_weight*1.30;

                        }
                        if(value.equals(light))
                        {
                            level=1.55;
                            final_weight=final_weight*1.55;

                        }
                        if(value.equals(moderate))
                        {
                            level=1.65;
                            final_weight=final_weight*1.65;

                        }
                        if(value.equals(heavy))
                        {
                            level=1.80;
                            final_weight=final_weight*1.80;

                        }
                        if(value.equals(veryheavy))
                        {
                            level=2.00;
                            final_weight=final_weight*2.00;

                        }
                    }
                    if(male_fat>=21.0&&male_fat<=28.0)
                    {
                        final_weight=final_weight*0.90;

                        if(value.equals(verylight))
                        {
                            level=1.30;
                            final_weight=final_weight*1.30;

                        }
                        if(value.equals(light))
                        {
                            level=1.55;
                            final_weight=final_weight*1.55;

                        }
                        if(value.equals(moderate))
                        {
                            level=1.65;
                            final_weight=final_weight*1.65;

                        }
                        if(value.equals(heavy))
                        {
                            level=1.80;
                            final_weight=final_weight*1.80;

                        }
                        if(value.equals(veryheavy))
                        {
                            level=2.00;
                            final_weight=final_weight*2.00;

                        }
                    }
                    if(male_fat>28.1)
                    {
                        final_weight=final_weight*0.85;

                        if(value.equals(verylight))
                        {
                            level=1.30;
                            final_weight=final_weight*1.30;

                        }
                        if(value.equals(light))
                        {
                            level=1.55;
                            final_weight=final_weight*1.55;

                        }
                        if(value.equals(moderate))
                        {
                            level=1.65;
                            final_weight=final_weight*1.65;

                        }
                        if(value.equals(heavy))
                        {
                            level=1.80;
                            final_weight=final_weight*1.80;

                        }
                        if(value.equals(veryheavy))
                        {
                            level=2.00;
                            final_weight=final_weight*2.00;

                        }
                    }







                }





                        if(female.isChecked())
                        {
                            String h=height.getText().toString();
                            String w=waist.getText().toString();
                            String Wt=weight.getText().toString();
                            Double weight=Double.parseDouble(Wt);
                            final_weight=weight*0.9*24;
                            Double height=Double.parseDouble(h);
                            Double height_1=height*2.54;
                            Double waist=Double.parseDouble(w);
                            Double waist_1=waist*2.54;
                            female_fat=76-(20*(height_1/waist_1));



                            if(female_fat>=14.0&&female_fat<=18.9)
                            {
                                final_weight=final_weight*1.0;
                                if(value.equals(verylight))
                                {
                                    level=1.30;
                                    final_weight=final_weight*1.30;

                                }
                                if(value.equals(light))
                                {
                                    level=1.55;
                                    final_weight=final_weight*1.55;

                                }
                                if(value.equals(moderate))
                                {
                                    level=1.65;
                                    final_weight=final_weight*1.65;

                                }
                                if(value.equals(heavy))
                                {
                                    level=1.80;
                                    final_weight=final_weight*1.80;

                                }
                                if(value.equals(veryheavy))
                                {
                                    level=2.00;
                                    final_weight=final_weight*2.00;

                                }
                            }
                            if(female_fat>=19.0&&female_fat<=28.9)
                            {
                                final_weight=final_weight*0.95;

                                if(value.equals(verylight))
                                {
                                    level=1.30;
                                    final_weight=final_weight*1.30;

                                }
                                if(value.equals(light))
                                {
                                    level=1.55;
                                    final_weight=final_weight*1.55;

                                }
                                if(value.equals(moderate))
                                {
                                    level=1.65;
                                    final_weight=final_weight*1.65;

                                }
                                if(value.equals(heavy))
                                {
                                    level=1.80;
                                    final_weight=final_weight*1.80;

                                }
                                if(value.equals(veryheavy))
                                {
                                    level=2.00;
                                    final_weight=final_weight*2.00;

                                }
                            }
                            if(female_fat>=29.0&&female_fat<=38.0)
                            {
                                final_weight=final_weight*0.90;

                                if(value.equals(verylight))
                                {
                                    level=1.30;
                                    final_weight=final_weight*1.30;

                                }
                                if(value.equals(light))
                                {
                                    level=1.55;
                                    final_weight=final_weight*1.55;

                                }
                                if(value.equals(moderate))
                                {
                                    level=1.65;
                                    final_weight=final_weight*1.65;

                                }
                                if(value.equals(heavy))
                                {
                                    level=1.80;
                                    final_weight=final_weight*1.80;

                                }
                                if(value.equals(veryheavy))
                                {
                                    level=2.00;
                                    final_weight=final_weight*2.00;

                                }
                            }
                            if(female_fat>38.1)
                            {
                                final_weight=final_weight*0.85;

                                if(value.equals(verylight))
                                {
                                    level=1.30;
                                    final_weight=final_weight*1.30;

                                }
                                if(value.equals(light))
                                {
                                    level=1.55;
                                    final_weight=final_weight*1.55;

                                }
                                if(value.equals(moderate))
                                {
                                    level=1.65;
                                    final_weight=final_weight*1.65;

                                }
                                if(value.equals(heavy))
                                {
                                    level=1.80;
                                    final_weight=final_weight*1.80;

                                }
                                if(value.equals(veryheavy))
                                {
                                    level=2.00;
                                    final_weight=final_weight*2.00;

                                }
                            }




                        }

                        reduce=final_weight-400;
                        gain=final_weight+500;

                    int newreduce=Integer.valueOf(reduce.intValue());
                    int newgain=Integer.valueOf(gain.intValue());
                    int newfinal_weight=Integer.valueOf(final_weight.intValue());

                AlertDialog.Builder a_builder = new AlertDialog.Builder(BMI.this);
                        a_builder.setMessage("Your Daily Calories Intake Is:"+newfinal_weight+"\n"+"If you want to reduce 0.5kg in one week take "+newreduce+" Calories per Day."+"\n"+
                        "If you want to gain 0.5kg in one week take "+newgain+ "Calories per Day")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();

                                    }
                                });
                        AlertDialog alert=a_builder.create();
                        alert.setTitle("Calories");
                        alert.show();








            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        value=spinner.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
