package com.example.naturallis.Fragments;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naturallis.R;

public class DietViewHolder extends RecyclerView.ViewHolder {

    TextView name_diet;

    public DietViewHolder(@NonNull View itemView) {
        super(itemView);

        name_diet = itemView.findViewById(R.id.name_diet);

    }
}
