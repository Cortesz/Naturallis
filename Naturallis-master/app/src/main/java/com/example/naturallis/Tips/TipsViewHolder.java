package com.example.naturallis.Tips;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naturallis.R;

public class TipsViewHolder extends RecyclerView.ViewHolder {

    ImageView tipImage;
    TextView tipName;

    public TipsViewHolder(@NonNull View itemView) {
        super(itemView);

        tipImage = itemView.findViewById(R.id.tip_Image);
        tipName = itemView.findViewById(R.id.tip_Name);
    }
}
