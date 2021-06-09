package com.example.coinstats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.coinstats.Adapter.CoinAdapter;
import com.example.coinstats.Interface.onLoad;
import com.example.coinstats.Model.CoinStructure;
import com.example.coinstats.Model.ResRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class MainActivity extends AppCompatActivity {

    List<CoinStructure> items = new ArrayList<>();
    CoinAdapter adapter;
    RecyclerView recyclerView;

    OkHttpClient client;
    Request request;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.coin_List_main);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadCoins_Start(0,50);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadCoins_Start(0, 50);
                items.clear();
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.coin_List);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupAdapter();
    }

    private void loadCoins_Next(int skip, int limit) {
        client = new OkHttpClient();
        request = new Request.Builder().url(String.format("https://api.coinstats.app/public/v1/coins?skip=%d&limit=%d&currency=EUR",skip,limit))
                .build();
        swipeRefreshLayout.setRefreshing(true);
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String body = response.body().string();
                        Gson gson = new Gson();
                        // Obj
                            //array
                        ResRequest res = gson.fromJson(body,ResRequest.class);
                        List<CoinStructure> newItems = res.coins;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                items.addAll(newItems);
                                adapter.setLoaded();
                                adapter.updateData(items);
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                });
    }

    private void loadCoins_Start(int skip, int limit) {
        client = new OkHttpClient();
        request = new Request.Builder().url(String.format("https://api.coinstats.app/public/v1/coins?skip=%d&limit=%d&currency=EUR",skip,limit))
                .build();
        swipeRefreshLayout.setRefreshing(true);
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String body = response.body().string();
                        Gson gson = new Gson();

                        ResRequest res = gson.fromJson(body,ResRequest.class);
                        List<CoinStructure> newItems = res.coins;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.updateData(newItems);
                            }
                        });
                    }
                });
        if(swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
    }

    private void setupAdapter() {
        adapter = new CoinAdapter(recyclerView, MainActivity.this,items);
        recyclerView.setAdapter(adapter);
    }
}