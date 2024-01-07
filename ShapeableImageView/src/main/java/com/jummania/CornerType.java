package com.jummania;

public enum CornerType {
    ROUNDED(0),
    CUT(0),
    DEFAULT(0);

    private final int cornerType;
    CornerType(int cornerType) {
        this.cornerType = cornerType;
    }

    public int getCornerType() {
        return cornerType;
    }
}
