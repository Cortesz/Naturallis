package com.example.naturallis.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.naturallis.Login;
import com.example.naturallis.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserFragment extends Fragment {

    private Button buttonLogout, buttonEditar,buttonOkPerfil;
    private TextView txtNomeP;
    private TextView txtEmailP;
    private TextView txtObs;
    private TextView txtNomePerfil;
    private DatabaseReference mDatabaseP;
    private ImageView imageView17;
    private ImageView imageView31;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user,container,false);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Recuperar dados pelo google
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        //
        mDatabaseP = FirebaseDatabase.getInstance().getReference();
        buttonLogout = view.findViewById(R.id.buttonLogout);
        buttonEditar = view.findViewById(R.id.buttonEditar);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disconnect();
            }
        });

        buttonEditar = view.findViewById(R.id.buttonEditar);
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPerfil();
            }
        });

        buttonOkPerfil = view.findViewById(R.id.buttonOkPerfil);
        buttonOkPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPerfilOK();
            }
        });

        txtNomeP = view.findViewById(R.id.edtNamePerfil);
        txtNomePerfil = view.findViewById(R.id.txtNomePerfil);
        txtEmailP = view.findViewById(R.id.edtEmailPerfil);
        txtObs = view.findViewById(R.id.edtCarPerfil);
        imageView17 = view.findViewById(R.id.imageView17);
        imageView31 = view.findViewById(R.id.imageView31);
        mDatabaseP = FirebaseDatabase.getInstance().getReference();

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference uidRef = userRef.child(FirebaseAuth.getInstance().getUid());
        DatabaseReference usernameRef = uidRef.child("username");

        uidRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userSnapshot: snapshot.getChildren()){
                    String username=snapshot.child("username").getValue(String.class);
                    String email= snapshot.child("email").getValue(String.class);
                    String sexo= snapshot.child("sexo").getValue(String.class);
                    Boolean veggie= (Boolean) snapshot.child("veggie").getValue();
                    String Obs = snapshot.child("doenças").getValue(String.class);

                    txtNomeP.setText(username );
                    txtEmailP.setText(email);
                    txtObs.setText(Obs);

                    if(veggie){
                        txtNomePerfil.setText("Nome(Vegetariano)");
                    } else{
                        txtNomePerfil.setText("Nome(Não Vegetariano)");
                    }

                    if (sexo.equals("M")){
                        imageView17.setVisibility(View.GONE);
                    }

                    if (sexo.equals("F")){
                        imageView31.setVisibility(View.GONE);
                    }



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Erro ao ler perfil", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static UserFragment newInstance(){
        return new UserFragment();
    }

    private void editPerfil(){
        txtNomeP.setEnabled(true);
        txtNomeP.requestFocus();

        txtEmailP.setEnabled(true);
        txtObs.setEnabled(true);
        buttonOkPerfil.setVisibility(View.VISIBLE);
        buttonEditar.setVisibility(View.INVISIBLE);
    }

    private void editPerfilOK(){
        String NomeP = txtNomeP.getText().toString();
        String EmailP = txtEmailP.getText().toString();
        String CarP = txtObs.getText().toString();

        FirebaseAuth mAuth1;
        mAuth1 = FirebaseAuth.getInstance();
        String userId = mAuth1.getCurrentUser().getUid();

        mDatabaseP.child("users").child(userId).child("username").setValue(NomeP);
        mDatabaseP.child("users").child(userId).child("email").setValue(EmailP);
        mDatabaseP.child("users").child(userId).child("doenças").setValue(CarP);

        txtNomeP.setEnabled(false);
        txtEmailP.setEnabled(false);
        txtObs.setEnabled(false);
        buttonEditar.setVisibility(View.VISIBLE);
        buttonOkPerfil.setVisibility(View.INVISIBLE);
    }

    private void disconnect(){

        FirebaseAuth.getInstance().signOut();
        closePrincipal();
    }

    private void closePrincipal(){
        Intent i = new Intent(getActivity(), Login.class);
        startActivity(i);
        getActivity().finish();
    }



}


