package com.example.coinstats.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coinstats.R;

public class CoinView extends RecyclerView.ViewHolder {
    public ImageView coin_icon;
    public TextView coin_symbol,coin_name,coin_price,price_1_hour,price_24_hour,price_1_week;

    public CoinView(View itemView){
        super(itemView);
        coin_icon = (ImageView)itemView.findViewById(R.id.coin_icon);
        coin_symbol = (TextView) itemView.findViewById(R.id.coin_symbol);
        coin_name = (TextView) itemView.findViewById(R.id.coin_name);
        coin_price = (TextView) itemView.findViewById(R.id.coin_priceText);

        price_1_hour = (TextView) itemView.findViewById(R.id.coin_percent_1h);
        price_24_hour = (TextView) itemView.findViewById(R.id.coin_percent_24h);
        price_1_week = (TextView) itemView.findViewById(R.id.coin_percent_1w);
    }
}
