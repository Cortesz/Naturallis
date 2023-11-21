package com.example.naturallis.Classes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.naturallis.NewDiet1;
import com.example.naturallis.R;

import java.util.ArrayList;

public class MultipleChoiceDinner extends DialogFragment {

    public interface onMultipleChoiceDinner {
        void onPositiveButtonClicked(String[] list, ArrayList<String> selectedItemlist);
        void onNegativeButtonClicked();
    }

    onMultipleChoiceDinner mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener= (onMultipleChoiceDinner) context;
        } catch (Exception e) {
            throw new ClassCastException(getActivity().toString()+" onMultipleChoiceDinner must implemented");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final ArrayList<String> selectedItemList = new ArrayList<>();

        Context context;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ArrayList<String> foodList1 = new ArrayList<String>();
        foodList1 = NewDiet1.getFoodlist();

        final String[] list = new String[foodList1.size()];
        for(int i = 0; i < foodList1.size(); i++) list[i] = foodList1.get(i);

        builder.setTitle("Selecione os alimentos").setMultiChoiceItems(list, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if(b){
                    selectedItemList.add(list[i]);
                } else {
                    selectedItemList.remove(list[i]);
                }
            }
        })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onPositiveButtonClicked(list,selectedItemList);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onNegativeButtonClicked();
                    }
                });
        return builder.create();

    }
}
