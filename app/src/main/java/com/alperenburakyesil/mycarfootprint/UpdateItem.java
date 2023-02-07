package com.alperenburakyesil.mycarfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdateItem extends AppCompatActivity {

    String id, type_station;
    EditText name, date, type, amount, perLiter;
    int carbon_foot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
        id = getIntent().getStringExtra("id");

        createText();

        writeData();
    }

    private void writeData() {
        name.setText(DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).getStation_name());
        date.setText(DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).getDate());
        type.setText(DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).getType());
        amount.setText(DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).getAmount());
        perLiter.setText(DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).getPerLiter());

    }

    private void createText() {
        name = findViewById(R.id.station_name_edit);
        date = findViewById(R.id.date_edit);
        type = findViewById(R.id.fuel_type_edit);
        amount = findViewById(R.id.amount_edit);
        perLiter = findViewById(R.id.liter_price_edit);
    }

    public void update(View view) {
        //Delete old total value

        MainActivity.total_costs -= DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).getTotalCost();
        MainActivity.total_carbon -= DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).getCarbonFoot();

        //Update Data
        DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).setStation_name(name.getText().toString());
        DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).setDate(date.getText().toString());
        DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).setType(type.getText().toString());
        type_station = DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).getType();
        DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).setAmount(amount.getText().toString());
        DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).setPerLiter(perLiter.getText().toString());
        DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).setTotalCost(Integer.parseInt(amount.getText().toString()) * Double.parseDouble(perLiter.getText().toString()));

        if (type_station.equals("Gasoline") || type_station.equals("gasoline")){
            carbon_foot = (int) Math.round(2.32 * Integer.parseInt(DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).getAmount()));
        }

        else if (type_station.equals("Disel") || type_station.equals("disel")){
            carbon_foot = (int) Math.round(2.69 * Integer.parseInt(DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).getAmount()));
        }

        DataAdapter.visitDataArrayList.get(Integer.parseInt(id)).setCarbonFoot(carbon_foot);

        //Add new total value
        MainActivity.total_costs += Integer.parseInt(amount.getText().toString()) * Double.parseDouble(perLiter.getText().toString());
        MainActivity.total_carbon += carbon_foot;

        startActivity(new Intent(UpdateItem.this, MainActivity.class));
        finish();
    }

    public void cancel(View view) {
        startActivity(new Intent(UpdateItem.this, MainActivity.class));
        finish();
    }
}