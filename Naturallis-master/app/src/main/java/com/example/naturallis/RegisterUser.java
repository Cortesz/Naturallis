package com.example.naturallis;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class RegisterUser extends AppCompatActivity {

    Spinner spin;
    Spinner spin2;
    private EditText edtEmailC;
    private EditText edtNameC;
    private EditText edtPasswordC;
    private EditText edtObs;
    private FirebaseAuth mAuth1;
    private DatabaseReference mDatabaseR;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        requireNonNull(getSupportActionBar()).hide();

        spin = findViewById(R.id.spin);
        spin2 = findViewById(R.id.spin2);
        mAuth1 = FirebaseAuth.getInstance();
        edtEmailC = findViewById(R.id.edtEmail);
        edtNameC = findViewById(R.id.edtName);
        edtObs = findViewById(R.id.edtObs);
        edtPasswordC = findViewById(R.id.edtPassword);
        mDatabaseR = FirebaseDatabase.getInstance().getReference();

    }



    public void back(View v){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public void Register(View v) {


        final String email = edtEmailC.getText().toString();
        final String password = edtPasswordC.getText().toString();
        final String name = edtNameC.getText().toString();
        final String Doenca = edtObs.getText().toString();

        final Boolean veggie;
        if (spin.getSelectedItem().equals("Sou vegetariano")) {
             veggie = true;
        } else {
             veggie = false;
        }

        final String sexo;
        if (spin2.getSelectedItem().equals("Masculino")) {
            sexo = "M";
        } else {
            sexo = "F";
        }





        mAuth1.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth1.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Sucesso.", Toast.LENGTH_SHORT).show();
                            verificarEmail();
                            writeNewUser(mAuth1.getCurrentUser().getUid(),name,email,veggie,sexo,Doenca);
                            Intent i = new Intent(getApplicationContext(), Login.class);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Erro ao criar o Login.", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

    private void writeNewUser(String userId, String name, String email, Boolean veggie,String sexo, String Doenca) {
        mDatabaseR.child("users").child(userId).child("username").setValue(name);
        mDatabaseR.child("users").child(userId).child("email").setValue(email);
        mDatabaseR.child("users").child(userId).child("veggie").setValue(veggie);
        mDatabaseR.child("users").child(userId).child("sexo").setValue(sexo);
        mDatabaseR.child("users").child(userId).child("doenças").setValue(Doenca);
    }

    private void verificarEmail(){

        FirebaseUser user = mAuth1.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "Email sent.");
                            Toast.makeText(RegisterUser.this, "Email de verificação enviado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }




    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = mAuth1.getCurrentUser();
        if(user != null){
            Toast.makeText(getApplicationContext(), "Usuário conectado.", Toast.LENGTH_SHORT).show();
        }

    }

}
