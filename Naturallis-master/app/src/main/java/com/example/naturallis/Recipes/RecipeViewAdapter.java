package com.example.naturallis.Recipes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naturallis.R;

public class RecipeViewAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private int[] recipe_images;
    private String[] recipe_names;

    public RecipeViewAdapter(int[] recipe_images, String[] recipe_names){
    this.recipe_images = recipe_images;
    this.recipe_names = recipe_names;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipes, parent, false);
        RecipeViewHolder recipeViewHolder = new RecipeViewHolder(view);

        return recipeViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        final int recipeImage_id = recipe_images[position];
        final String recipename = recipe_names[position];

        holder.recipeImage.setImageResource(recipeImage_id);
        holder.recipeName.setText(recipename);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context;
                Intent i;
                context = v.getContext();
                i = new Intent(v.getContext(), recipeInfo.class);
                i.putExtra("recipename",recipename);
                i.putExtra("recipeImage_id",recipeImage_id);
                ((AppCompatActivity) context).startActivityForResult(i, 0);



            }
        });
    }

    @Override
    public int getItemCount() {
        return recipe_images.length;
    }

    
}
