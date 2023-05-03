package net.ali.pgc.ui.chordPrompter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.List;

public class TonalityAdapter extends RecyclerView.Adapter<TonalityAdapter.TonalityViewHolder> {
    private HashSet<Integer> selectedPositions = new HashSet<>();
    private List<String> tonalities;
    private OnTonalityClickListener onTonalityClickListener;

    public TonalityAdapter(List<String> tonalities, OnTonalityClickListener onTonalityClickListener) {
        this.tonalities = tonalities;
        this.onTonalityClickListener = onTonalityClickListener;
    }

    @NonNull
    @Override
    public TonalityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        TonalityViewHolder holder = new TonalityViewHolder(view, onTonalityClickListener);
        holder.selectedPositions = selectedPositions;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TonalityViewHolder holder, int position) {
        holder.tonalityTextView.setText(tonalities.get(position));
    }

    @Override
    public int getItemCount() {
        return tonalities.size();
    }

    public static class TonalityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tonalityTextView;
        private OnTonalityClickListener onTonalityClickListener;
        private HashSet<Integer> selectedPositions;

        public TonalityViewHolder(@NonNull View itemView, OnTonalityClickListener onTonalityClickListener) {
            super(itemView);
            tonalityTextView = itemView.findViewById(android.R.id.text1);
            this.onTonalityClickListener = onTonalityClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (onTonalityClickListener != null) {
                onTonalityClickListener.onTonalityClick(position);
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

    public HashSet<Integer> getSelectedPositions() {
        return selectedPositions;
    }

    public interface OnTonalityClickListener {
        void onTonalityClick(int position);
    }
}
