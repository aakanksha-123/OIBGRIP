package com.example.unit__converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    EditText EnterNum;
    Button btn_meters,btn_centimeters,btn_miles,btn_kilometers;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EnterNum=findViewById(R.id.edit);
        btn_meters=findViewById(R.id.meter);
        btn_centimeters=findViewById(R.id.centimeter);
        btn_miles=findViewById(R.id.miles);
        btn_kilometers=findViewById(R.id.kilometer);
        result = findViewById(R.id.result);


        btn_meters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = EnterNum.getText().toString();
                int number = Integer.parseInt(num);
                double meters = (number * 1000);
                result.setText("Value in meters: "+meters);

            }
        });

        btn_centimeters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = EnterNum.getText().toString();
                int number = Integer.parseInt(num);
                double centimeters = (number * 100);
                result.setText("Value in centimeters: "+centimeters);

            }
        });


        btn_miles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = EnterNum.getText().toString();
                int number = Integer.parseInt(num);
                double miles =  (number / 1.609);
                result.setText("Value in miles: "+miles);

            }
        });
        btn_kilometers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = EnterNum.getText().toString();
                int number = Integer.parseInt(num);
                double kilometers = (number / 1000);
                result.setText("Value in kilometers: "+kilometers);

            }
        });
    }
}

