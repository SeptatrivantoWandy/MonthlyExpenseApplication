package com.example.a2301869512_septatrivantowandy_uts_mcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class MonExAddActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        startActivity(intent);
        finish();

        super.onBackPressed();
    }

    EditText etMonExName;
    EditText etMonExPrice;

    Button buttonMonExSubmit;

    Calendar calendar = Calendar.getInstance();
    Date cDate = new Date();
    String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);

    public static Vector<MonExDBHelper> db_helper_vector = new Vector<MonExDBHelper>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_ex_add);

        etMonExName = findViewById(R.id.etAddMonExName);
        etMonExPrice = findViewById(R.id.etMonExPrice);

        buttonMonExSubmit = findViewById(R.id.buttonAddMonex);

        buttonMonExSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int correct = validation();

                if(correct == 1){}
                else{
                    String saveEtMonexName = etMonExName.getText().toString().trim();
                    int saveEtMonexPrice = Integer.parseInt(etMonExPrice.getText().toString().trim());
                    String saveEtMonexDate = currentDate.trim();

                    MonExDBHelper db_Helper = new MonExDBHelper(MonExAddActivity.this);
                    db_Helper.insertMonEx(new MonEx(  "1", saveEtMonexName, saveEtMonexPrice, saveEtMonexDate));
                    db_helper_vector.add(db_Helper);


//
//                    db_Helper.insertMonEx(new MonEx(-1,
//                            saveEtMonexName,
//                            saveEtMonexPrice,
//                            saveEtMonexDate
//                    ));
//
//                    monex = db_Helper.getMonEx();
//
//                    for (String data : monex){
//                        Log.i("SELECT ALL MONEX", data);
//                    }

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        });



    }

    public int validation() {
        if(TextUtils.isEmpty(etMonExName.getText().toString().trim())){

            etMonExName.setError("input your spending name");
            etMonExName.requestFocus();
            return 1;
        }
        if(TextUtils.isEmpty(etMonExPrice.getText().toString().trim())){

            etMonExPrice.setError("input your spending nominal");
            etMonExPrice.requestFocus();
            return 1;
        }

        if(Integer.parseInt(etMonExPrice.getText().toString().trim()) > 999999999 ||etMonExPrice.getText().toString().trim().startsWith("0")){

            etMonExPrice.setError("your nominal input is not valid");
            etMonExPrice.requestFocus();
            return 1;

        }

        return 0;
    }


}