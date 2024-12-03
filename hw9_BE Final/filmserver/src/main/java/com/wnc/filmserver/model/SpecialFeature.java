package com.wnc.filmserver.model;

public enum SpecialFeature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String feature;

    SpecialFeature(String feature) {
        this.feature = feature;
    }

    public String getFeature() {
        return feature;
    }

    public static boolean isValidFeature(String feature) {
        for (SpecialFeature sf : SpecialFeature.values()) {
            if (sf.getFeature().equalsIgnoreCase(feature)) {
                return true;
            }
        }
        return false;
    }
}
