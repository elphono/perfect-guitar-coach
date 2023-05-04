package net.ali.pgc.ui.chordPrompter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ChordTypeAdapter extends RecyclerView.Adapter<ChordTypeAdapter.ChordTypeViewHolder> {

    private List<String> chordTypes;
    private OnChordTypeClickListener onChordTypeClickListener;
    private List<Integer> selectedPositions = new ArrayList<>();

    public ChordTypeAdapter(List<String> chordTypes, OnChordTypeClickListener onChordTypeClickListener) {
        this.chordTypes = chordTypes;
        this.onChordTypeClickListener = onChordTypeClickListener;
    }

    @NonNull
    @Override
    public ChordTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        ChordTypeAdapter.ChordTypeViewHolder holder = new ChordTypeAdapter.ChordTypeViewHolder(view, onChordTypeClickListener);
        holder.selectedPositions = selectedPositions;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChordTypeViewHolder holder, int position) {
        holder.chordTypeTextView.setText(chordTypes.get(position));
    }

    @Override
    public int getItemCount() {
        return chordTypes.size();
    }

    public static class ChordTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private List<Integer> selectedPositions;
        private TextView chordTypeTextView;
        private OnChordTypeClickListener onChordTypeClickListener;

        public ChordTypeViewHolder(@NonNull View itemView, OnChordTypeClickListener onChordTypeClickListener) {
            super(itemView);
            chordTypeTextView = itemView.findViewById(android.R.id.text1);
            this.onChordTypeClickListener = onChordTypeClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (onChordTypeClickListener != null) {
                onChordTypeClickListener.onChordTypeClick(position);
            }

            if (selectedPositions.contains(position)) {
                selectedPositions.remove(position);
                itemView.setBackgroundColor(Color.TRANSPARENT);
            } else {
                selectedPositions.add(position);
                itemView.setBackgroundColor(Color.LTGRAY);
            }
        }
    }

    public List<Integer> getSelectedPositions() {
        return selectedPositions;
    }

    public interface OnChordTypeClickListener {
        void onChordTypeClick(int position);
    }
}
