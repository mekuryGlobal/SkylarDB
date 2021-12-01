package com.example.skylar.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skylar.R;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CurrencyRVAdapter extends RecyclerView.Adapter<CurrencyRVAdapter.ViewHolder> {
    private ArrayList<CurrencyRVModel> currencyRVModelArrayList;
    private Context context;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public CurrencyRVAdapter(ArrayList<CurrencyRVModel> currencyRVModelArrayList, Context context) {
        this.currencyRVModelArrayList = currencyRVModelArrayList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public CurrencyRVAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.currency_rv_item,parent,false);
        return new CurrencyRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CurrencyRVAdapter.ViewHolder holder, int position) {
        CurrencyRVModel currencyRVModel = currencyRVModelArrayList.get(position);
        holder.currencyName.setText(currencyRVModel.getCurrencyName());
        holder.prize.setText("$" + df2.format(currencyRVModel.getPrize()));
        holder.balanceStellar.setText(df2.format(currencyRVModel.getBalanceStellar()) + " " + currencyRVModel.getSymbol() );
        double thePrice = currencyRVModel.getPrize(), theBalance = currencyRVModel.getBalanceStellar();
        holder.balanceDollar.setText("$" + df2.format(currencyRVModel.getBalanceDollar()));
    }

    @Override
    public int getItemCount() {
        return currencyRVModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyName,balanceStellar, prize,balanceDollar;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            currencyName = itemView.findViewById(R.id.idRLCurrencyName);
            prize = itemView.findViewById(R.id.idRLPrice);
            balanceStellar = itemView.findViewById(R.id.idTBalanceDB);
            balanceDollar = itemView.findViewById(R.id.idTBalanceDollar);
        }
    }
}
