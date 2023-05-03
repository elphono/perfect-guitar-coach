package net.ali.pgc.ui.chordPrompter;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.HashSet;

public class ChordPrompterFragment extends Fragment {

    private TextView selectedChordsTextView;

    public ChordPrompterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(net.ali.pgc.R.layout.fragment_chord_prompter, container, false);

        selectedChordsTextView = view.findViewById(net.ali.pgc.R.id.selected_chords_textview);

        Bundle bundle = getArguments();
        if (bundle != null) {
            HashSet<Integer> selectedChordTypes = (HashSet<Integer>) bundle.getSerializable("selectedChordTypes");
            HashSet<Integer> selectedTonalities = (HashSet<Integer>) bundle.getSerializable("selectedTonalities");

            // You can replace the following with your desired implementation
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Selected Chord Types: ").append(selectedChordTypes.toString()).append("\n");
            stringBuilder.append("Selected Tonalities: ").append(selectedTonalities.toString());

            selectedChordsTextView.setText(stringBuilder.toString());
        }

        return view;
    }
}