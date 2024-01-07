package com.jummania;

/**
 * Enum representing different corner types for a ShapeableImageView.
 */
public enum CornerType {
    /**
     * Rounded corners.
     */
    ROUNDED(0),

    /**
     * Cut corners.
     */
    CUT(0),

    /**
     * Default corners.
     */
    DEFAULT(0);

    private final int cornerType;

    /**
     * Constructs a CornerType with the specified corner type.
     *
     * @param cornerType The corner type value.
     */
    CornerType(int cornerType) {
        this.cornerType = cornerType;
    }

    /**
     * Gets the corner type value.
     *
     * @return The corner type value.
     */
    public int getCornerType() {
        return cornerType;
    }
}
