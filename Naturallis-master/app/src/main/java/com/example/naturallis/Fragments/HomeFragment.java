package com.example.naturallis.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.naturallis.AlertScreen;
import com.example.naturallis.R;
import com.example.naturallis.Recipes.Recipes;
import com.example.naturallis.Tips.Tips;


public class HomeFragment extends Fragment  {

    CardView btnNewDiet;
    CardView btnRecipe;
    CardView btnTips;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnNewDiet = view.findViewById(R.id.btnNewDiet);

        btnNewDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AlertScreen.class);
                startActivity(i);
            }
        });

        btnRecipe = view.findViewById(R.id.btnRecipe);

        btnRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Recipes.class);
                startActivity(i);
            }
        });

        btnTips = view.findViewById(R.id.btnTips);

        btnTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Tips.class);
                startActivity(i);
            }
        });

    }

    public static HomeFragment newInstance(){
        return new HomeFragment();

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}

