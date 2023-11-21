package com.example.naturallis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.naturallis.Classes.MultipleChoiceBreakfast;
import com.example.naturallis.Classes.MultipleChoiceDinner;
import com.example.naturallis.Classes.MultipleChoiceLunch;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class NewDiet1 extends AppCompatActivity implements MultipleChoiceBreakfast.onMultipleChoiceBreakfast, MultipleChoiceLunch.onMultipleChoiceLunch, MultipleChoiceDinner.onMultipleChoiceDinner {

    private DatabaseReference mDatabaseD1;
    private FirebaseAuth mAuth3;
    private Button buttonPronto, btnLunch, btnBreakfast, btnDinner;
    private StringBuilder stringBuilder1 = new StringBuilder("");
    private String breakfast = "", lunch = "", dinner = "", nameDiet = "";
    private Integer choice = 0;
    public  final ArrayList<String> food = new ArrayList<String>();
    public static ArrayList<String> foodList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diet1);
        getSupportActionBar().hide();
        mDatabaseD1 = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //Recuperar nome da dieta
        Intent intent = getIntent();
        nameDiet = intent.getStringExtra("nameDiet");


        ImportFood();

    }

    public void Breakfast(View view) {
        DialogFragment multiChoiceBreakfast = new MultipleChoiceBreakfast();
        multiChoiceBreakfast.setCancelable(false);
        multiChoiceBreakfast.show(getSupportFragmentManager(), "MultipleChoiceBreakfast");
        choice = 1;

    }

    public void Lunch(View view) {
        DialogFragment multiChoiceLunch = new MultipleChoiceLunch();
        multiChoiceLunch.setCancelable(false);
        multiChoiceLunch.show(getSupportFragmentManager(), "MultipleChoiceLunch");
        choice = 2;
    }

    public void Dinner(View view) {
        DialogFragment multiChoiceLunch = new MultipleChoiceLunch();
        multiChoiceLunch.setCancelable(false);
        multiChoiceLunch.show(getSupportFragmentManager(), "MultipleChoiceLunch");
        choice = 3;
    }

    public void pronto(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = "";
        if (user != null) {
            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            uid = user.getUid();
        }
        mDatabaseD1.child("users").child(uid).child("Dietas").child(nameDiet).child("breakfast").setValue(breakfast);
        mDatabaseD1.child("users").child(uid).child("Dietas").child(nameDiet).child("lunch").setValue(lunch);
        mDatabaseD1.child("users").child(uid).child("Dietas").child(nameDiet).child("dinner").setValue(dinner);

        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    @Override
    public void onPositiveButtonClicked(String[] list, ArrayList<String> selectedItemlist) {

        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("");
        for (String str : selectedItemlist) {
            stringBuilder1.append(str + " ");
        }

        updateVarDiet();


    }

    public void updateVarDiet() {

        switch (choice) {
            case 0:
                Toast.makeText(this, "Erro em selecionar", Toast.LENGTH_SHORT).show();
            case 1:
                breakfast = stringBuilder1.toString();
            case 2:
                lunch = stringBuilder1.toString();
            case 3:
                dinner = stringBuilder1.toString();
        }

    }

    @Override
    public void onNegativeButtonClicked() {
        Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
    }

    public void ImportFood() {

        DatabaseReference foodRef = FirebaseDatabase.getInstance().getReference("food");


            foodRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){

                }
                for (int i = 1; i <= dataSnapshot.getChildrenCount(); i++ ) {
                    String food1 = dataSnapshot.child(String.valueOf(i)).getValue(String.class);
                    food.add(food1);

                }
                // ...
                foodList = food;


                Toast.makeText(NewDiet1.this, ""+ food, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NewDiet1.this, "Erro ao recuperar alimentos", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static ArrayList<String> getFoodlist() {

        Collections.sort(foodList);
        return foodList;
    }
}
