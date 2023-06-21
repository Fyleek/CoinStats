package com.example.coinstats.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coinstats.Interface.onLoad;
import com.example.coinstats.Model.CoinStructure;
import com.example.coinstats.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    onLoad onLoadMore;
    boolean isLoading;
    Activity activity;
    List<CoinStructure> items;

    int visibleThreshold = 5,lastVisibleItem,totalItemCount;

    public CoinAdapter(RecyclerView recyclerView,Activity activity, List<CoinStructure> items) {
        this.activity = activity;
        this.items = items;

        LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if(!isLoading && totalItemCount <= (lastVisibleItem)){
                    if(onLoadMore != null){
                        onLoadMore.onLoadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    public void setLoad(onLoad load) {
        this.onLoadMore = load;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity)
                .inflate(R.layout.coin,parent,false);
        return new CoinView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        CoinStructure item = items.get(position);
        CoinView holderItem = (CoinView)holder;
        holderItem.coin_name.setText(item.getName());
        holderItem.coin_symbol.setText(item.getSymbol());

        holderItem.price_btc.setText(item.getPriceBtc());
        holderItem.price_btc.setTextColor(Color.parseColor("#E3C011"));

        holderItem.coin_price.setText(item.getprice());
        holderItem.price_1_hour.setText(item.getPriceChange1h()+"%");
        holderItem.price_24_hour.setText(item.getPriceChange1d()+"%");
        holderItem.price_1_week.setText(item.getPriceChange1w()+"%");

        Picasso.get()
                .load(item.getIcon())
                .into(holderItem.coin_icon);

        holderItem.price_1_hour.setTextColor(item.getPriceChange1h().contains("-")? Color.parseColor("#FF0000"):Color.parseColor("#32CD32"));
        holderItem.price_24_hour.setTextColor(item.getPriceChange1d().contains("-")? Color.parseColor("#FF0000"):Color.parseColor("#32CD32"));
        holderItem.price_1_week.setTextColor(item.getPriceChange1w().contains("-")? Color.parseColor("#FF0000"):Color.parseColor("#32CD32"));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setLoaded(){isLoading = true;}

    public void updateData(List<CoinStructure> coinStructureList){
        this.items = coinStructureList;
        notifyDataSetChanged();
    }
}
