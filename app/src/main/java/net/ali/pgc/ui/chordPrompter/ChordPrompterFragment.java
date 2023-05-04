package net.ali.pgc.ui.chordPrompter;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.ali.pgc.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class ChordPrompterFragment extends Fragment {
    private TextView chordDisplay;
    private List<String> chordTypes;
    private List<String> tonalities;
    private Handler handler;
    private Runnable updateChordRunnable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chord_prompter, container, false);

        // Get the selected chord types and tonalities from the ChordTonalitySelectionFragment
        Bundle args = getArguments();
        if (args != null) {
            HashSet<Integer> selectedChordTypes = (HashSet<Integer>) args.getSerializable("selectedChordTypes");
            HashSet<Integer> selectedTonalities = (HashSet<Integer>) args.getSerializable("selectedTonalities");

            // Assuming you have a complete list of chord types and tonalities in ChordTonalitySelectionFragment
            List<String> allChordTypes = Arrays.asList("Major", "Minor", "7", "maj7", "m7", "dim", "aug");
            List<String> allTonalities = Arrays.asList("A", "B", "C", "D", "E", "F", "G");

            chordTypes = new ArrayList<>();
            tonalities = new ArrayList<>();

            for (Integer index : selectedChordTypes) {
                chordTypes.add(allChordTypes.get(index));
            }

            for (Integer index : selectedTonalities) {
                tonalities.add(allTonalities.get(index));
            }
        }

        chordDisplay = view.findViewById(R.id.chord_display);

        // Set up the handler and runnable for updating the chord display
        handler = new Handler();
        updateChordRunnable = new Runnable() {
            @Override
            public void run() {
                String randomChord = getRandomChord();
                chordDisplay.setText(randomChord);
                handler.postDelayed(this, 4000);
            }
        };
        handler.post(updateChordRunnable);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(updateChordRunnable);
    }

    private String getRandomChord() {
        Random random = new Random();
        String randomChordType = chordTypes.get(random.nextInt(chordTypes.size()));
        String randomTonality = tonalities.get(random.nextInt(tonalities.size()));
        return randomTonality + " " + randomChordType;
    }
}
