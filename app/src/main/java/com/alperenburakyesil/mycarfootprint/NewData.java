package com.alperenburakyesil.mycarfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class NewData extends AppCompatActivity {


    ArrayList<VisitData> visitData = new ArrayList<>();

    EditText name, date, type, amount, perPrice;
    public String name_station, date_station, type_station, amount_station, perPrice_station;
    double cost;
    int carbon_foot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);

        if (MainActivity.visitData != null){
            this.visitData = MainActivity.visitData;
        }

        createText();
    }

    private void createText() {
        name = findViewById(R.id.station_name);
        date = findViewById(R.id.date);
        type = findViewById(R.id.fuel_type);
        amount = findViewById(R.id.amount);
        perPrice = findViewById(R.id.liter_price);
    }

    public void save(View view) {
        name_station = name.getText().toString();
        date_station = date.getText().toString();
        type_station = type.getText().toString();
        amount_station = amount.getText().toString();  //How many liter
        perPrice_station = perPrice.getText().toString();

        cost = Double.parseDouble(amount_station) * Double.parseDouble(perPrice_station);

        if (type_station.equals("Gasoline") || type_station.equals("gasoline")){
            carbon_foot = (int) Math.round(2.32 * Integer.parseInt(amount_station));
        }

        else if (type_station.equals("Disel") || type_station.equals("disel")){
            carbon_foot = (int) Math.round(2.69 * Integer.parseInt(amount_station));
        }

        MainActivity.total_costs += cost;
        MainActivity.total_carbon += carbon_foot;

        VisitData data = new VisitData(name_station, date_station, type_station, amount_station, perPrice_station, cost, carbon_foot);
        visitData.add(data);
        MainActivity.arrayEqual(visitData);

        Intent main_screen = new Intent(NewData.this, MainActivity.class);
        startActivity(main_screen);
        finish();

    }

    public void cancel(View view) {
        Intent main_screen = new Intent(NewData.this, MainActivity.class);
        startActivity(main_screen);
        finish();
    }
}