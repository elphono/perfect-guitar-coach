package net.ali.pgc.ui.chordPrompter;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.ali.pgc.R;

import java.util.List;
import java.util.Random;

public class ChordPrompterFragment extends Fragment {
    private TextView chordDisplay;
    private Switch pausePlaySwitch;
    private List<String> chordTypes;
    private List<String> tonalities;
    private Handler handler;
    private Runnable updateChordRunnable;
    private boolean isPaused;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chord_prompter, container, false);

        // Get the selected chord types and tonalities from the ChordTonalitySelectionFragment
        Bundle args = getArguments();
        if (args != null) {
            chordTypes = (List<String>) args.getSerializable("selectedChordTypes");
            tonalities = (List<String>) args.getSerializable("selectedTonalities");
        }

        chordDisplay = view.findViewById(R.id.chord_display);
        pausePlaySwitch = view.findViewById(R.id.pause_play_switch);
        isPaused = false;

        // Set up the handler and runnable for updating the chord display
        handler = new Handler();
        updateChordRunnable = new Runnable() {
            @Override
            public void run() {
                if (!isPaused) {
                    String randomChord = getRandomChord();
                    chordDisplay.setText(randomChord);
                }
                handler.postDelayed(this, 4000);
            }
        };
        handler.post(updateChordRunnable);

        pausePlaySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> isPaused = isChecked);

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
