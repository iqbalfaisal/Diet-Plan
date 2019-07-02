package com.example.dietplan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Blob;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="student.db";



    public static final String TABLE_FOOD_ITEMS="table_food_items";
    public static final String FOOD_NAME="name";
    public static final String FOOD_ID="id";
    public static final String FOOD_CALORIES="calories";
    public static final String FOOD_UNIT="units";
    public static final String PICTURES="pics";
    public static final String CATEGORY="category";


    public static final String TABLE_DIET_PLAN="table_diet_plan";
    public static final String PLAN_ID="plan_id";
    public static final String PLAN_NAME="plan_name";
    public static final String PLAN_DAYS="plan_days";
    public static final String PLAN_CALORIES="plan_calories";


    public static final String TABLE_PLAN_ITEMS="table_plan_items";
    public static final String PLAN_NAMEE="plan_name";
    public static final String MEAL_TYPE="meal_type";
    public static final String DAY="day";
    public static final String F_NAME="food_name";
    public static final String QUANTITY="qty";





    SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null,4 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_FOOD_ITEMS+"(id int,category text,name text,calories text,units text,pics int)");
        db.execSQL("create table "+TABLE_DIET_PLAN+"(plan_name text,plan_days text,plan_calories text)");
        db.execSQL("create table "+TABLE_PLAN_ITEMS+"(plan_name text,meal_type text,day text,food_name text,qty text)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("drop table if exists "+TABLE_NAME );
        onCreate(db);
    }



    public boolean insert_into_food_items(String id,String category, String name, String calories, String unit, int pic)
    {
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(FOOD_ID,id);
        contentValues.put(CATEGORY,category);
        contentValues.put(FOOD_NAME,name);
        contentValues.put(FOOD_CALORIES,calories);
        contentValues.put(FOOD_UNIT,unit);
        contentValues.put(PICTURES,pic);



        long check= db.insert(TABLE_FOOD_ITEMS,null,contentValues);
        if(check==-1)
            return false;
        else
            return true;
    }



    public boolean table_plan_items(String plan_name,String meal_type, String day, String food_name, String qty)
    {
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(PLAN_NAMEE,plan_name);
        contentValues.put(MEAL_TYPE,meal_type);
        contentValues.put(F_NAME,food_name);
        contentValues.put(DAY,day);
        contentValues.put(QUANTITY,qty);


        long check= db.insert(TABLE_PLAN_ITEMS,null,contentValues);
        if(check==-1)
            return false;
        else
            return true;
    }


    public boolean diet_plan(String name, String days, String calories)
    {
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
//        contentValues.put(PLAN_ID,id);
        contentValues.put(PLAN_NAME,name);
        contentValues.put(PLAN_DAYS,days);
        contentValues.put(PLAN_CALORIES,calories);




        long check= db.insert(TABLE_DIET_PLAN,null,contentValues);
        if(check==-1)
            return false;
        else
            return true;
    }


    public Cursor getnewData(String str){
        db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from table_food_items WHERE category = '"+str+"'",null  );
        return res;
    }


    public Cursor GET_TABLE_PLAN_ITEMS(String name,String type,String day){
        db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from table_plan_items WHERE plan_name = '"+name+"' AND meal_type ='"+type+"' AND  day = '"+day+"'  ",null  );
        return res;
    }
    public Cursor GET_TABLE_PLAN_ITEMS_FOR_COPY_PASTE(String name,String day){
        db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from table_plan_items WHERE plan_name = '"+name+"' AND day = '"+day+"'  ",null  );
        return res;
    }

    public Cursor GET_ALL_ITEMS_PLAN(String name){
        db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from table_plan_items WHERE plan_name = '"+name+"' ",null  );
        return res;
    }



    public Cursor get_food(String name){
        db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from TABLE_FOOD_ITEMS WHERE name = '"+name+"'",null  );

        return res;
    }

    public Cursor view_plans(){
        db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from TABLE_DIET_PLAN ",null  );
        return res;
    }


    public void deleteRow(String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_DIET_PLAN+ " WHERE "+PLAN_NAME+"='"+value+"'");
        db.close();
    }
    public void deleteItemRow(String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLAN_ITEMS+ " WHERE "+PLAN_NAME+"='"+value+"'");
        db.close();
    }




}