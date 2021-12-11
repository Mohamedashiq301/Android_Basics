package com.example.myapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.model.Notes;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    List<Notes> listnotes;
    public NotesAdapter(List<Notes>list){
        this.listnotes=list;
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

