package com.example.a2301869512_septatrivantowandy_uts_mcs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class MonExAdapter extends RecyclerView.Adapter<MonExAdapter.MyViewHolder> {

    public static final String SEND_ID = "com.example.a2301869512_septatrivantowandy_uts_mcs.SEND_ID";
    public static final String SEND_NAME = "com.example.a2301869512_septatrivantowandy_uts_mcs.SEND_NAME";
    public static final String SEND_PRICE = "com.example.a2301869512_septatrivantowandy_uts_mcs.SEND_PRICE";
    public static final String SEND_DATE = "com.example.a2301869512_septatrivantowandy_uts_mcs.SEND_DATE";

    private Context ctx;
    private Vector<MonEx> vector_monex;

    public MonExAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public void setVector_monex(Vector<MonEx> vector_monex) {
        this.vector_monex = vector_monex;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.monex_display, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvMainMonExName.setText(vector_monex.get(position).getMonexName());
        holder.tvMainMonExPrice.setText("Rp." + vector_monex.get(position).getMonexPrice());
        holder.tvMainMonExDate.setText(vector_monex.get(position).getMonexDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, MonExEditActivity.class);



                intent.putExtra(SEND_ID, vector_monex.get(position).getMonexId());
                intent.putExtra(SEND_NAME, vector_monex.get(position).getMonexName());
                intent.putExtra(SEND_PRICE, vector_monex.get(position).getMonexPrice());
                intent.putExtra(SEND_DATE, vector_monex.get(position).getMonexDate());

                ctx.startActivity(intent);
                ((MainActivity)ctx).finish();

            }
        });

    }

    @Override
    public int getItemCount() {
        return vector_monex.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvMainMonExName, tvMainMonExPrice, tvMainMonExDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMainMonExName = itemView.findViewById(R.id.tvMainMonExName);
            tvMainMonExPrice = itemView.findViewById(R.id.tvMainMonExPrice);
            tvMainMonExDate = itemView.findViewById(R.id.tvMainMonExDate);
        }
    }

}