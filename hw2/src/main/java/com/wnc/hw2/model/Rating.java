package com.wnc.hw2.model;

public enum Rating {
    GENERAL_AUDIENCES("G"),
    PARENTAL_GUIDANCE_SUGGESTED("PG"),
    PARENTS_STRONGLY_CAUTIONED("PG-13"),
    RESTRICTED("R"),
    ADULTS_ONLY("NC-17");

    String code;

    Rating(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
