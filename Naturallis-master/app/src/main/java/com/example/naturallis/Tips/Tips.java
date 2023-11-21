package com.example.naturallis.Tips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.naturallis.R;

public class Tips extends AppCompatActivity {

    private RecyclerView recyclerView1;
    private RecyclerView.LayoutManager layoutManager;
    private TipsViewAdapter adapter;
    private Context context;

    private int[] tips_images = new int[] {
            R.drawable.oleos, R.drawable.whey,
            R.drawable.graos, R.drawable.soja
    };

    private String[] tips_names = new String[]{
            "Evitar Óleos De Sementes Refinados", "Utilizar Whey Protein",
            "Preferir Grãos Germinados",  "Incluir Soja Fermentada"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        getSupportActionBar().setTitle("Dicas");

        recyclerView1 = findViewById(R.id.recyclerView1);
        layoutManager = new LinearLayoutManager(context);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(layoutManager);

        adapter = new TipsViewAdapter(tips_images, tips_names);
        recyclerView1.setAdapter(adapter);

    }
}