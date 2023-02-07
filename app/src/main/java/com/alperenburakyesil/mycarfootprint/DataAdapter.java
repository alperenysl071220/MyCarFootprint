package com.alperenburakyesil.mycarfootprint;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    Context context;
    public static ArrayList<VisitData> visitDataArrayList;
    EditListener listener;

    public DataAdapter(Context context,ArrayList<VisitData> visitDataArrayList, EditListener listener) {
        this.context = context;
        this.visitDataArrayList = visitDataArrayList;
        this.listener = listener;
    }

    public DataAdapter() {
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item, parent, false);

        return new DataViewHolder(v);
    }

    @Override
    public int getItemCount() {
        if (visitDataArrayList != null){
            return visitDataArrayList.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        VisitData visitData = visitDataArrayList.get(position);

        holder.name.setText("Station: " + visitData.getStation_name());
        holder.date.setText("Date: " + visitData.getDate());
        holder.type.setText("Fuel Type: " + visitData.getType());
        holder.amount.setText("Liters: " + visitData.getAmount());
        holder.perLiter.setText("Price Per Liter: " + visitData.getPerLiter());
        holder.cost.setText(String.valueOf("Cost: " + visitData.getTotalCost()));
        holder.carbonFoot.setText(String.valueOf("Carbon Footprint: " + visitData.getCarbonFoot()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEditClicked(String.valueOf(holder.getAdapterPosition()));


            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.total_cost.setText("Total Fuel Cost\n$ " + String.valueOf(MainActivity.total_costs -= visitData.getTotalCost()));
                MainActivity.total_co2.setText("Total Carbon Footprint\n" + String.valueOf(MainActivity.total_carbon -= visitData.getCarbonFoot()) + " Kg");
                visitDataArrayList.remove(visitData);
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });



    }



    public static class DataViewHolder extends RecyclerView.ViewHolder{
        public final View view;
        public final CardView cardView;
        public final TextView name;
        public final TextView date;
        public final TextView type;
        public final TextView amount;
        public final TextView perLiter;
        public final TextView cost;
        public final TextView carbonFoot;
        public final ImageButton deleteButton;

        public DataViewHolder(View view) {
            super(view);
            this.view = view;
            cardView = view.findViewById(R.id.cardView);
            name = view.findViewById(R.id.rec_name);
            date = view.findViewById(R.id.rec_date);
            type = view.findViewById(R.id.rec_type);
            amount = view.findViewById(R.id.rec_amount);
            perLiter = view.findViewById(R.id.rec_price);
            cost = view.findViewById(R.id.rec_cost);
            carbonFoot = view.findViewById(R.id.rec_carbon);
            deleteButton = view.findViewById(R.id.delete);
        }
    }


}