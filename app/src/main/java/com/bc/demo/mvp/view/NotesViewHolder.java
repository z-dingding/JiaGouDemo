package com.bc.demo.mvp.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bc.demo.R;


public class NotesViewHolder extends RecyclerView.ViewHolder {

    public TextView text, date;
    public ImageButton btnDelete;

    public NotesViewHolder(View itemView) {
        super(itemView);
        setupViews(itemView);
    }

    private void setupViews(View view) {
        text = (TextView) view.findViewById(R.id.note_text);
        date = (TextView) view.findViewById(R.id.note_date);
        btnDelete = (ImageButton) view.findViewById(R.id.btn_delete);
    }


}
