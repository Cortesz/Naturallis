package com.example.naturallis.Recipes;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naturallis.R;

public class RecipeViewHolder extends RecyclerView.ViewHolder {

    ImageView recipeImage;
    TextView recipeName;

    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);

        recipeImage = itemView.findViewById(R.id.tip_Image);
        recipeName = itemView.findViewById(R.id.tip_Name);
    }



}
