package com.example.naturallis.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.naturallis.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class DietFragment extends Fragment {

    private ArrayList<String> nameDiet = new ArrayList<>();
    private ArrayList<String> breakfast = new ArrayList<>();
    private ArrayList<String> lunch = new ArrayList<>();
    private ArrayList<String> dinner = new ArrayList<>();

    private RecyclerView recyclerView2;
    private RecyclerView.LayoutManager layoutManager;
    private DietViewAdapter adapter;
    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_diet,container,false);

        recyclerView2 = rootView.findViewById(R.id.recyclerView2);
        layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(layoutManager);



        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        retrieveDieta();

    }

    //Recuperar dados

    public void retrieveDieta(){

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference uidRef = userRef.child(FirebaseAuth.getInstance().getUid());
        final DatabaseReference dietaRef = uidRef.child("Dietas");



        dietaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userSnapshot: snapshot.getChildren()){
                    ArrayList<String> nameD = new ArrayList<>();
                    nameD.add(userSnapshot.getKey());
                    nameDiet.addAll(nameD);

                }

                adapter = new DietViewAdapter(nameDiet);
                recyclerView2.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Erro", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static DietFragment newInstance(){
        return new DietFragment();
    }


}
