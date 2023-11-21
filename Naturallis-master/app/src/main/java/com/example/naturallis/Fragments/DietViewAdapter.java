package com.example.naturallis.Fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naturallis.R;

import java.util.ArrayList;

public class DietViewAdapter extends RecyclerView.Adapter<DietViewHolder> {

    private final ArrayList<String> nameDiet;

    public DietViewAdapter(ArrayList<String> nameDiet) {

        this.nameDiet = nameDiet;

    }

    @NonNull
    @Override
    public DietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.diets, parent, false);
        DietViewHolder dietViewHolder = new DietViewHolder(view);

        return dietViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DietViewHolder holder, int position) {

        final String dietName = nameDiet.get(position);

        holder.name_diet.setText(dietName);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Context context;
                Intent i;
                context = v.getContext();
                i = new Intent(v.getContext(), dietInfo.class);
                i.putExtra("dietName",dietName);
                ((AppCompatActivity) context).startActivityForResult(i,0);

            }
        });
    }


    @Override
    public int getItemCount() {
        return nameDiet.size();
    }
}
