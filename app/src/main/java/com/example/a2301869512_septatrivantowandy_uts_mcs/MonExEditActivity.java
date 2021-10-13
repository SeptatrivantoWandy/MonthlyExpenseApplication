package com.example.a2301869512_septatrivantowandy_uts_mcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MonExEditActivity extends AppCompatActivity {

    Button buttonEditMonEx;
    EditText etEditMonExName;
    EditText etEditMonExPrice;

    MonEx object_monex;

    String monexId;
    String monexName;
    int monexPrice;
    String monexDate;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        startActivity(intent);
        finish();

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_ex_edit);

        buttonEditMonEx = findViewById(R.id.buttonEditMonex);
        etEditMonExName = findViewById(R.id.etEditMonExName);
        etEditMonExPrice = findViewById(R.id.etEditMonExPrice);

        MonExDBHelper db_Helper = new MonExDBHelper(MonExEditActivity.this);

        Intent intent = getIntent();
        monexId = intent.getStringExtra(MonExAdapter.SEND_ID);
        monexName = intent.getStringExtra(MonExAdapter.SEND_NAME);
        monexPrice = intent.getIntExtra(MonExAdapter.SEND_PRICE, -1);
        monexDate = intent.getStringExtra(MonExAdapter.SEND_DATE);

//        Toast.makeText(this, monexPrice + "", Toast.LENGTH_SHORT).show();

        etEditMonExName.setText(monexName);
        etEditMonExPrice.setText(monexPrice + "");

        buttonEditMonEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validation() != 1){

                    object_monex = new MonEx(monexId, etEditMonExName.getText().toString().trim(), Integer.parseInt(etEditMonExPrice.getText().toString().trim()), monexDate);
                    boolean isUpdate = db_Helper.updateMonEx(object_monex);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {

//                    Toast.makeText(getApplicationContext(), "NOPE", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    public int validation() {
        if(TextUtils.isEmpty(etEditMonExName.getText().toString().trim())){

            etEditMonExName.setError("input your spending name");
            etEditMonExName.requestFocus();
            return 1;
        }
        if(TextUtils.isEmpty(etEditMonExPrice.getText().toString().trim())){

            etEditMonExPrice.setError("input your spending nominal");
            etEditMonExPrice.requestFocus();
            return 1;
        }

        if(Integer.parseInt(etEditMonExPrice.getText().toString().trim()) > 999999999 ||etEditMonExPrice.getText().toString().trim().startsWith("0")){

            etEditMonExPrice.setError("your nominal input is not valid");
            etEditMonExPrice.requestFocus();
            return 1;

        }

        return 0;
    }



}