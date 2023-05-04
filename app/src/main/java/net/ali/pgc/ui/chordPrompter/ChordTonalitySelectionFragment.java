package net.ali.pgc.ui.chordPrompter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;

import net.ali.pgc.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ChordTonalitySelectionFragment extends Fragment {
    // Set up the data for chord types and tonalities
    List<String> chordTypes = Arrays.asList("Major", "Minor", "7", "maj7", "m7", "dim", "aug");
    List<String> tonalities = Arrays.asList("A", "B", "C", "D", "E", "F", "G");
    private RecyclerView chordTypesRecyclerView;
    private RecyclerView tonalitiesRecyclerView;
    private Button startWorkoutButton;
    private ChordTypeAdapter chordTypeAdapter;
    private TonalityAdapter tonalityAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chord_tonality_selection, container, false);

        if (view != null) {
            // Initialize UI elements
            chordTypesRecyclerView = view.findViewById(R.id.chord_types_recycler_view);
            tonalitiesRecyclerView = view.findViewById(R.id.tonalities_recycler_view);
            startWorkoutButton = view.findViewById(R.id.start_workout_button);

            // Set up RecyclerViews for chord types and tonalities
            setupRecyclerViews();

            // Set up start workout button click listener
            if (startWorkoutButton != null) {
                startWorkoutButton.setOnClickListener(v -> {

                    ArrayList<String> selectedChordTypes = new ArrayList<>();
                    ArrayList<String> selectedTonalities = new ArrayList<>();
                    for (Integer index : chordTypeAdapter.getSelectedPositions()) {
                        selectedChordTypes.add(chordTypes.get(index));
                    }

                    for (Integer index : tonalityAdapter.getSelectedPositions()) {
                        selectedTonalities.add(tonalities.get(index));
                    }
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("selectedChordTypes", selectedChordTypes);
                    bundle.putSerializable("selectedTonalities", selectedTonalities);

                    Navigation.findNavController(v).navigate(R.id.action_chordTonalitySelectionFragment_to_chordPrompterFragment, bundle);
                });
            }
        }

        return view;
    }

    private void setupRecyclerViews() {
        // Initialize adapters
        chordTypeAdapter = new ChordTypeAdapter(chordTypes, position -> {
            // TODO: Handle chord type click events
        });
        tonalityAdapter = new TonalityAdapter(tonalities, position -> {
            // TODO: Handle tonality click events
        });

        // Set adapters for RecyclerViews
        chordTypesRecyclerView.setAdapter(chordTypeAdapter);
        tonalitiesRecyclerView.setAdapter(tonalityAdapter);
    }

}

