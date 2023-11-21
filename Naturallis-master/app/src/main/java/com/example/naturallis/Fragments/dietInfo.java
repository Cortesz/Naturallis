package com.example.naturallis.Fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naturallis.MainActivity;
import com.example.naturallis.NewDiet1;
import com.example.naturallis.R;
import com.example.naturallis.Recipes.Recipes;
import com.example.naturallis.Recipes.recipeInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dietInfo extends AppCompatActivity {

    private EditText edtDietaBreakfast, edtDietaLunch, edtDietaDinner;
    private TextView txtDietName,txtDietaCategoria;
    private String breakfast, dinner, lunch,dietName="",categoria;
    private Button buttonDeleteDiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_info);


        Intent intent = getIntent();
         dietName = intent.getStringExtra("dietName");

         edtDietaBreakfast =findViewById(R.id.edtDietaBreakfast);
         edtDietaLunch =findViewById(R.id.edtDietaLunch);
         edtDietaDinner = findViewById(R.id.edtDietaDinner);
         txtDietName = findViewById(R.id.txtDietName);
        txtDietaCategoria = findViewById(R.id.txtDietCategoria);
        buttonDeleteDiet = findViewById(R.id.buttonDeleteDiet);

        txtDietName.setText(dietName);


        recuperarDadosDieta();

        buttonDeleteDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           deleteDieta();

            }
        });

    }

    public void recuperarDadosDieta(){

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference uidRef = userRef.child(FirebaseAuth.getInstance().getUid());
        final DatabaseReference dietaRef = uidRef.child("Dietas");


        dietaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){

                    breakfast = (String) dataSnapshot.child(dietName).child("breakfast").getValue();
                    lunch = (String) dataSnapshot.child(dietName).child("lunch").getValue();
                    dinner = (String) dataSnapshot.child(dietName).child("dinner").getValue();
                    categoria = (String) dataSnapshot.child(dietName).child("Categoria").getValue();

                    edtDietaLunch.setText(lunch);
                    edtDietaBreakfast.setText(breakfast);
                    edtDietaDinner.setText(dinner);
                    txtDietaCategoria.setText(categoria);

                }

                // ...



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Erro ao recuperar alimentos", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void deleteDieta(){

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference uidRef = userRef.child(FirebaseAuth.getInstance().getUid());
        final DatabaseReference dietaRef = uidRef.child("Dietas");


        dietaRef.child(dietName).child("breakfast").removeValue();
        dietaRef.child(dietName).child("dinner").removeValue();
        dietaRef.child(dietName).child("lunch").removeValue();
        dietaRef.child(dietName).child("Nome").removeValue();
        dietaRef.child(dietName).child("Dia").removeValue();
        dietaRef.child(dietName).child("Categoria").removeValue();


        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);

    }

}