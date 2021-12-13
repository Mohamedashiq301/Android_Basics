package com.example.myapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.clicklinstener.itemClickListener;
import com.example.myapp.model.Notes;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    private List<Notes> listnotes;
    private com.example.myapp.clicklinstener.itemClickListener itemClickListener;

    public NotesAdapter(List<Notes>list,itemClickListener itemClickListener){
        this.listnotes=list;
        this.itemClickListener=itemClickListener;
    }
        @NonNull
        @Override
        public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_adapter_layout,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
                Notes notes =listnotes.get(position);
                String title=notes.getTitle();
                String description=notes.getDescription();
                holder.textViewTitle.setText(title);
                holder.textViewDescription.setText(description);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onClick(notes);
                    }
                });
        }

        @Override
        public int getItemCount() {

        return listnotes.size();
        }
        static class ViewHolder extends RecyclerView.ViewHolder{
            TextView textViewTitle,textViewDescription;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewTitle=itemView.findViewById(R.id.textViewTitle);
                textViewDescription=itemView.findViewById(R.id.textViewDescription);
            }
        }
    }

