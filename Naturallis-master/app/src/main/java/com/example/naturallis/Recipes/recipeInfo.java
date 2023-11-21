package com.example.naturallis.Recipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naturallis.NewDiet1;
import com.example.naturallis.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class recipeInfo extends AppCompatActivity {

    private ImageView btnReturn;
    private TextView textViewReceitaI;
    private TextView textViewReceitaP;
    private TextView textViewReceita;
    private ImageView imageReceita;
    private String recipename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);

        textViewReceitaI = findViewById(R.id.textView22);
        textViewReceita = findViewById(R.id.textView20);
        textViewReceitaP = findViewById(R.id.textView25);
        imageReceita = findViewById(R.id.imageView29);

        Intent intent = getIntent();
        recipename = intent.getStringExtra("recipename");
        Integer recipeImage_id = intent.getIntExtra("recipeImage_id",0);

        textViewReceita.setText(recipename);
        int id = getResources().getIdentifier(String.valueOf(recipeImage_id),"drawable",getPackageName());
        imageReceita.setImageResource(id);

        textViewReceitaP.setMovementMethod(new ScrollingMovementMethod());

        ImportRecipe();

        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(recipeInfo.this, Recipes.class);
                startActivity(i);


            }
        });

    }

    public void ImportRecipe() {

        DatabaseReference recipeRef = FirebaseDatabase.getInstance().getReference("recipe");


        recipeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){

                String receitaP = (String) dataSnapshot.child(recipename).child("receita").getValue();
                String receitaI = (String) dataSnapshot.child(recipename).child("ingredientes").getValue();

                textViewReceitaI.setText(receitaI);
                textViewReceitaP.setText(receitaP);

                }

                // ...



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Erro ao recuperar alimentos", Toast.LENGTH_SHORT).show();
            }
        });
    }

}