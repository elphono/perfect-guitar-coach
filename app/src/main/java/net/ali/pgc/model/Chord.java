package net.ali.pgc.model;

public class Chord {
    private ChordType chordType;
    private Tonality tonality;

    public Chord(ChordType chordType, Tonality tonality) {
        this.chordType = chordType;
        this.tonality = tonality;
    }

    public ChordType getChordType() {
        return chordType;
    }

    public Tonality getTonality() {
        return tonality;
    }

    @Override
    public String toString() {
        return tonality.getDisplayName() + chordType.getDisplayName();
    }
}
