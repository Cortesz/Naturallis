package com.example.naturallis;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

import static java.util.Objects.requireNonNull;

public class NewDiet extends AppCompatActivity {

    static final int DATE_DIALOG_ID = 0;
    static final int DATE_DIALOG_TO = 1;
    FloatingActionButton btnDatePicker;
    FloatingActionButton btnDatePicker2;
    private DatabaseReference mDatabaseD;
    private FirebaseAuth mAuth2;
    private EditText edtDietName;
    private EditText edtDescription;
    private Spinner spCategory;
    private String diaDieta;
    private String diaDieta1;
    public final ArrayList<String> DietN = new ArrayList<String>();
    public static ArrayList<String> DietNames = new ArrayList<String>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diet);
        requireNonNull(getSupportActionBar()).hide();
        mDatabaseD = FirebaseDatabase.getInstance().getReference();

        btnDatePicker = findViewById(R.id.btnDayPicker);
        btnDatePicker2 = findViewById(R.id.btnDayPicker2);
        edtDescription = findViewById(R.id.edtDescription);
        edtDietName = findViewById(R.id.edtDietName);
        spCategory = findViewById(R.id.spCategory);

    }

    @Override
    public Dialog onCreateDialog(int id) {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DAY_OF_MONTH);


        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, date, year, month, day);
            case DATE_DIALOG_TO:
                return new DatePickerDialog(this, date1, year1, month1, day1);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String dateOfDiet = String.valueOf(dayOfMonth) + " /" + String.valueOf(month+1) + " /" + String.valueOf(year);
             diaDieta = dateOfDiet;
            Toast.makeText(NewDiet.this, "DATA ESCOLHIDA: " + dateOfDiet, Toast.LENGTH_LONG).show();

        }
    };

    private DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String dateOfDiet1 = String.valueOf(dayOfMonth) + " /" + String.valueOf(month+1) + " /" + String.valueOf(year);
            diaDieta1 = dateOfDiet1;
            Toast.makeText(NewDiet.this, "DATA DE TÉRMINO ESCOLHIDA: " + dateOfDiet1, Toast.LENGTH_LONG).show();

        }
    };


    public void SetDate(View v){
        if(v == btnDatePicker){
            showDialog(DATE_DIALOG_ID);
        }
    }

    public void setDate1(View v){
        if(v == btnDatePicker2){
            showDialog(DATE_DIALOG_TO);
        }
    }


    public void onBack(View v){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }


    public void Continue(View view){

        String nameDiet = edtDietName.getText().toString();
        String description = edtDescription.getText().toString();
        String categoria;

        DietN.add(nameDiet);
        DietNames = DietN;

        if (spCategory.getSelectedItem().equals("Dieta vegetariana")) {
            categoria ="Dieta vegetariana";
        } else {
            categoria ="Dieta não vegetariana";
        }


        insertOnDb(nameDiet,diaDieta,categoria, description, diaDieta1);
        Intent it = new Intent(this, NewDiet1.class);
        it.putExtra("nameDiet",nameDiet);
        startActivity(it);
    }

    public void insertOnDb(String name, String dayOfDiet, String category, String description, String dayOfDiet1 ){

        String uid = "";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
         uid = user.getUid();
        }
        mDatabaseD.child("users").child(uid).child("Dietas").child(name).child("Nome").setValue(name);
        mDatabaseD.child("users").child(uid).child("Dietas").child(name).child("Dia").setValue(dayOfDiet);
        mDatabaseD.child("users").child(uid).child("Dietas").child(name).child("Categoria").setValue(category);
        mDatabaseD.child("users").child(uid).child("Dietas").child(name).child("Descricao").setValue(description);
        mDatabaseD.child("users").child(uid).child("Dietas").child(name).child("Fim").setValue(dayOfDiet1);



    }

}
