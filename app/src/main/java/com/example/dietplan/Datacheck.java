package com.example.dietplan;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Datacheck extends AppCompatActivity {

    Button button,showbtn;
    EditText id,name;
    TextView txtid,txtname;
    DBHelper dbHelper;
    String Fruits="Fruits";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datacheck);
        dbHelper=new DBHelper(this);
        button=findViewById(R.id.checking);
        id=findViewById(R.id.id);
        name=findViewById(R.id.name);
        showbtn=findViewById(R.id.load_data);
        txtid=findViewById(R.id.textview_id);
        txtname=findViewById(R.id.textview_name);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res =dbHelper.GET_ALL_ITEMS_PLAN("abc");
                if(res.getCount()==0)
                {
                    showMessage("Error","No Data Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("PLAN_NAMEE :"+res.getString(0)+"\n");
                    buffer.append("MEAL_TYPE :"+res.getString(1)+"\n");
                    buffer.append("F_NAME :"+res.getString(3)+"\n");
                    buffer.append("DAY :"+res.getString(2)+"\n");
                    buffer.append("QUANTITY :"+res.getString(4)+"\n");

                }
                showMessage("Data",buffer.toString());
            }
        });


    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
