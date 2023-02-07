package com.alperenburakyesil.mycarfootprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<VisitData> visitData;
    public static double total_costs = 0;
    public static int total_carbon = 0;


    public static TextView total_cost, total_co2;
    RecyclerView recyclerView;
    DataAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        total_cost = findViewById(R.id.total_cost);
        total_co2 = findViewById(R.id.total_carbon_footprint);

        if(visitData != null){
            recyclerView = findViewById(R.id.station_data_recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));

            adapter = new DataAdapter(MainActivity.this, visitData, editListener);
            recyclerView.setAdapter(adapter);

            total_cost.setText("Total Fuel Cost\n$ " + String.valueOf(total_costs));
            total_co2.setText("Total Carbon Footprint\n" + String.valueOf(total_carbon) + " Kg");
        }


    }



    public static void arrayEqual(ArrayList<VisitData> visitDataArrayList){
        visitData = visitDataArrayList;
    }



    public void add_item(View view) {
        Intent add_item = new Intent(MainActivity.this, NewData.class);
        startActivity(add_item);
        finish();
    }

    private final EditListener editListener = new EditListener() {
        @Override
        public void onEditClicked(String id) {
            startActivity(new Intent(MainActivity.this, UpdateItem.class)
                    .putExtra("id", id));
            finish();
        }
    };


}