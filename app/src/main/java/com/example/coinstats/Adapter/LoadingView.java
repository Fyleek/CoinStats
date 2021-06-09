package com.example.coinstats.Adapter;

import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coinstats.R;

public class LoadingView extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;
    public LoadingView(View itemView){
        super(itemView);
        progressBar = (ProgressBar)itemView.findViewById(R.id.progress_bar);
    }
}
