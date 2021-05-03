package br.crateus.jander.petnatututor;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LineHolder extends RecyclerView.ViewHolder {

    public TextView nomePet;
    public ImageButton btMore;

    public LineHolder(@NonNull View itemView) {
        super(itemView);
        nomePet = (TextView) itemView.findViewById(R.id.etMeusPets);
        btMore = (ImageButton) itemView.findViewById(R.id.ibVerMaisPets);
    }
}
