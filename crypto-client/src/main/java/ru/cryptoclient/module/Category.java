package ru.cryptoclient.module;

public enum Category {
    VISUAL("Visual"),
    HUD("HUD"),
    PERFORMANCE("Performance"),
    UTILITY("Utility"),
    PVP("PvP Legit"),
    MISC("Misc");
    public final String title;
    Category(String title) { this.title = title; }
}
