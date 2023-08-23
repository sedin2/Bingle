package com.bingle.playerranking.model;

public enum Position {
    TOP("Top Lane"),
    JGL("Jungle"),
    MID("Mid Lane"),
    ADC("Attack Damage Carry"),
    SUP("Support");

    private final String displayName;

    Position(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
