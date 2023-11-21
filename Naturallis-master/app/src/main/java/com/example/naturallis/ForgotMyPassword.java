package com.example.naturallis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotMyPassword extends AppCompatActivity {

    private EditText edtEmailForgot;
    private FirebaseAuth mAuthF;
    private Button btnForgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_my_password);

        mAuthF = FirebaseAuth.getInstance();
        edtEmailForgot = findViewById(R.id.edtEmailForgot);
        btnForgotPass = findViewById(R.id.btnForgotPass);



        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Esquecer(edtEmailForgot.getText().toString());

            }
        });

    }

    private void Esquecer(String emailForgot) {

        mAuthF.sendPasswordResetEmail(emailForgot)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotMyPassword.this, "Email enviado", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), Login.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(ForgotMyPassword.this, "Erro ao mandar email ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}