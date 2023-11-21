package com.example.naturallis.Tips;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naturallis.R;
import com.example.naturallis.Recipes.recipeInfo;

public class TipsViewAdapter extends RecyclerView.Adapter<TipsViewHolder> {

    private int[] tips_images;
    private String[] tips_names;

    public TipsViewAdapter(int[] tips_images, String[] tips_names){
        this.tips_images = tips_images;
        this.tips_names = tips_names;
    }

    @NonNull
    @Override
    public TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.tips, parent, false);
        TipsViewHolder tipsViewHolder = new TipsViewHolder(view);

        return tipsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TipsViewHolder holder, int position) {
        final int tipImage_Id = tips_images[position];
        final String tipName = tips_names[position];

        holder.tipImage.setImageResource(tipImage_Id);
        holder.tipName.setText(tipName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context;
                Intent i;
                context = v.getContext();
                i = new Intent(v.getContext(), tipsInfo.class);
                i.putExtra("tipName",tipName);
                i.putExtra("tipImage_Id",tipImage_Id);
                ((AppCompatActivity) context).startActivityForResult(i, 0);
            }
        });
    }


    @Override
    public int getItemCount() {
        return tips_images.length;
    }
}
