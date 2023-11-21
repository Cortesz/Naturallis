package com.example.naturallis.Recipes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import com.example.naturallis.R;

public class Recipes extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecipeViewAdapter adapter;
    private Context context;
    private CardView card;



    private int[] recipe_images = new int[] {
            R.drawable.creme_espinafre, R.drawable.macarrao_alfredo,
            R.drawable.crepioca, R.drawable.sufle_chuchu,
            R.drawable.tabule, R.drawable.salada_grao
    };

    private String[] recipe_names = new String[]{
            "Creme de Espinafre", "Macarrão Alfredo",
            "Crepioca",  "Suflê de Chuchu",
            "Tabule", "Salada de Grão-de-Bico"
    };

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        getSupportActionBar().setTitle("Receitas");

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecipeViewAdapter(recipe_images, recipe_names);
        recyclerView.setAdapter(adapter);



    }



}