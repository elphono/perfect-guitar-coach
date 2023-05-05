package net.ali.pgc.model;

public enum ChordType {
    MAJOR("Major"),
    MINOR("Minor"),
    DOMINANT_7("7"),
    MAJOR_7("maj7"),
    MINOR_7("m7"),
    DIMINISHED("dim"),
    AUGMENTED("aug");

    private String displayName;

    ChordType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
