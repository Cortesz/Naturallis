package com.example.naturallis.Tips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naturallis.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class tipsInfo extends AppCompatActivity {

    private ImageView imageViewTip;
    private TextView textViewTipName, textViewTipHolder;
    private String tipName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_info);

        imageViewTip = findViewById(R.id.imageViewTip);
        textViewTipHolder = findViewById(R.id.textViewTipHolder);
        textViewTipName = findViewById(R.id.textViewTipName);

        Intent intent = getIntent();
         tipName = intent.getStringExtra("tipName");
        Integer tipImage_Id = intent.getIntExtra("tipImage_Id",0);

        textViewTipName.setText(tipName);
        int id = getResources().getIdentifier(String.valueOf(tipImage_Id),"drawable",getPackageName());
        imageViewTip.setImageResource(id);

        textViewTipHolder.setMovementMethod(new ScrollingMovementMethod());

        ImportRecipe();


    }

    private void ImportRecipe() {

        DatabaseReference tipsRef = FirebaseDatabase.getInstance().getReference("tips");


        tipsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){

                    String tip = (String) dataSnapshot.child(tipName).getValue();
                    textViewTipHolder.setText(tip);

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