package com.example.dietplan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DBHelper dbHelper;
    int apple=R.drawable.apple;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new DBHelper(this);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            if (!prefs.getBoolean("firstTime", false)) {
//                // <---- run your one time code here

                dbHelper.insert_into_food_items("2","Vegetables", "Beetroot", "52 Calories", "100 grams",R.drawable.beetroot);
                dbHelper.insert_into_food_items("2","Vegetables", "Broccoli", "65 Calories", "100 grams",R.drawable.broccoli);
                dbHelper.insert_into_food_items("2","Vegetables", "Carrot", "47 Calories", "100 grams",R.drawable.carrot);
                dbHelper.insert_into_food_items("2","Vegetables", "Corn", "89 Calories", "100 grams",R.drawable.corn);
                dbHelper.insert_into_food_items("2","Vegetables", "Cucumber", "39 Calories", "100 grams",R.drawable.cucumber);
                dbHelper.insert_into_food_items("2","Vegetables", "Lettuce", "39 Calories", "100 grams",R.drawable.lettuce);
                dbHelper.insert_into_food_items("2","Vegetables", "Peas", "39 Calories", "100 grams",R.drawable.peas);
                dbHelper.insert_into_food_items("2","Vegetables", "Spinach", "39 Calories", "100 grams",R.drawable.spinach);
                dbHelper.insert_into_food_items("2","Vegetables", "Tomato", "39 Calories", "100 grams",R.drawable.tomato);
                dbHelper.insert_into_food_items("2","Vegetables", "Radish", "39 Calories", "100 grams",R.drawable.radish);


                dbHelper.insert_into_food_items("1","Fruits", "Apple", "52 Calories", "100 grams",R.drawable.apple);
                dbHelper.insert_into_food_items("1","Fruits", "Mango", "65 Calories", "100 grams",R.drawable.mango);
                dbHelper.insert_into_food_items("1","Fruits", "Orange", "47 Calories", "100 grams",R.drawable.orange);
                dbHelper.insert_into_food_items("1","Fruits", "Banana", "89 Calories", "100 grams",R.drawable.banana);
            dbHelper.insert_into_food_items("1","Fruits", "Peach", "39 Calories", "100 grams",R.drawable.peach);
            dbHelper.insert_into_food_items("1","Fruits", "Apricot", "48 Calories", "100 grams",R.drawable.apricot);
            dbHelper.insert_into_food_items("1","Fruits", "Plums", "46 Calories", "100 grams",R.drawable.plum);
            dbHelper.insert_into_food_items("1","Fruits", "Strawberry", "32 Calories", "100 grams",R.drawable.strawberry);
            dbHelper.insert_into_food_items("1","Fruits", "Grapefruit", "39 Calories", "100 grams",R.drawable.grapefruit);
            dbHelper.insert_into_food_items("1","Fruits", "Pineapple", "48 Calories", "100 grams",R.drawable.pineapple);


                dbHelper.insert_into_food_items("5","Dairy", "Milk", "39 Calories", "200 ml",R.drawable.milk);
                dbHelper.insert_into_food_items("5","Dairy", "Almond Milk", "39 Calories", "200 ml",R.drawable.almondmilk);
                dbHelper.insert_into_food_items("5","Dairy", "Yogurt", "39 Calories", "200 grams",R.drawable.yogurt);
                dbHelper.insert_into_food_items("5","Dairy", "Soy Milk", "39 Calories", "200 ml",R.drawable.soymilk);
                dbHelper.insert_into_food_items("5","Dairy", "Butter", "39 Calories", "100 grams",R.drawable.butter);


                dbHelper.insert_into_food_items("4","Beverages", "Apple Juice", "100 Calories", "200 ml",R.drawable.applejuice);
                dbHelper.insert_into_food_items("4","Beverages", "Carrot Juice", "100 Calories", "200 ml",R.drawable.carrotjuice);
                dbHelper.insert_into_food_items("4","Beverages", "Vegetable Juice", "100 Calories", "200 ml",R.drawable.vegetablejuice);
                dbHelper.insert_into_food_items("4","Beverages", "Lemon Juice", "100 Calories", "200 ml",R.drawable.lemonjuice);
                dbHelper.insert_into_food_items("4","Beverages", "Orange Juice", "100 Calories", "200 ml",R.drawable.orangejuice);


                dbHelper.insert_into_food_items("3","Soup", "Chicken Soup", "150 Calories", "200 ml",R.drawable.chickensoup);
                dbHelper.insert_into_food_items("3","Soup", "Cream Soup", "200 Calories", "200 ml",R.drawable.creamsoup);
                dbHelper.insert_into_food_items("3","Soup", "Vegetable Soup", "133 Calories", "200 ml",R.drawable.vegetablesoup);
                dbHelper.insert_into_food_items("3","Soup", "Bean Soup", "120 Calories", "200 ml",R.drawable.beabsoup);
                dbHelper.insert_into_food_items("3","Soup", "Seafood Soup", "170 Calories", "200 ml",R.drawable.seafoodsoup);


                dbHelper.insert_into_food_items("7","Junk Food", "Pizza", "170 Calories", "100 gram",R.drawable.pizza);
                dbHelper.insert_into_food_items("7","Junk Food", "Beef Burger", "170 Calories", "100 gram",R.drawable.beefbuger);
                dbHelper.insert_into_food_items("7","Junk Food", "Chicken Burger", "170 Calories", "100 gram",R.drawable.chickenburger);

                dbHelper.insert_into_food_items("6","Sea Food", "Fish", "170 Calories", "100 gram",R.drawable.fish);
                dbHelper.insert_into_food_items("6","Sea Food", "Crab", "170 Calories", "100 gram",R.drawable.crab);
                dbHelper.insert_into_food_items("6","Sea Food", "Prawn", "170 Calories", "100 gram",R.drawable.prawn);
                dbHelper.insert_into_food_items("6","Sea Food", "Lobster", "170 Calories", "100 gram",R.drawable.lobster);




            // mark first time has ran.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }


        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(MainActivity.this,HomePage.class);
                startActivity(intent);
                finish();
            }
        },4000);





    }

}
