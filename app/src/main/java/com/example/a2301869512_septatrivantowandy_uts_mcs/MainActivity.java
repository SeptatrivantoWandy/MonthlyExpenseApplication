package com.example.a2301869512_septatrivantowandy_uts_mcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private Toolbar mainMonExToolbar;

    RecyclerView rvMonEx;
    MonExDBHelper monexdb;
    Vector<MonEx> vector_monex = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMonEx = findViewById(R.id.rvMain);


        monexdb = new MonExDBHelper(MainActivity.this);
        if(storeMonExInVector() == 1){

            MonExAdapter monexadp = new MonExAdapter(this);
            monexadp.setVector_monex(vector_monex);
            rvMonEx.setAdapter(monexadp);

//            Toast.makeText(this, "no data???", Toast.LENGTH_SHORT).show();

            rvMonEx.setLayoutManager(new GridLayoutManager(this, 1));
        }

    }

    public int storeMonExInVector () {
        Cursor cursor = monexdb.readAllMonEx();
        if(cursor.getCount() == 0) {

//            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
            return -1;

        } else {


            while (cursor.moveToNext()) {

                MonEx obj =  new MonEx(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3));
                vector_monex.add(obj);
            }
            return 1;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_monex_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mainButtonMonexAddToolbar:

                Intent intent = new Intent(this, MonExAddActivity.class);
                startActivity(intent);
                finish();

                return true;

            default:
                break;
        }

        return false;
    }
}