package com.jummania;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

/**
 * Extended implementation of com.google.android.material.imageview.ShapeableImageView
 * with additional customization for corner shape and radius.
 */
public class ShapeableImageView extends com.google.android.material.imageview.ShapeableImageView {

    private CornerType cornerType = CornerType.DEFAULT;
    private float radius = -1;

    /**
     * Constructs a new ShapeableImageView.
     *
     * @param context The context in which the view is created.
     */
    public ShapeableImageView(Context context) {
        super(context);
        initialize(context, null);
    }

    /**
     * Constructs a new ShapeableImageView with attribute set.
     *
     * @param context The context in which the view is created.
     * @param attrs   The attribute set.
     */
    public ShapeableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    /**
     * Constructs a new ShapeableImageView with attribute set and style.
     *
     * @param context     The context in which the view is created.
     * @param attrs       The attribute set.
     * @param defStyleRes The default style resource.
     */
    public ShapeableImageView(Context context, @Nullable AttributeSet attrs, int defStyleRes) {
        super(context, attrs, defStyleRes);
        initialize(context, attrs);
    }

    /**
     * Sets the corner shape based on the current corner type and radius.
     */
    public void setCornerShape() {
        this.setShapeAppearanceModel(this.getShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(getCornerFamily(), getRadius())
                .build());
    }

    private int getCornerFamily() {
        return getCornerType().getCornerType();
    }

    /**
     * Sets the corner shape with the specified corner type and radius.
     *
     * @param cornerType The type of corners to be applied.
     * @param radius     The radius of the corners.
     */
    public void setCornerShape(CornerType cornerType, float radius) {
        setCornerType(cornerType);
        setRadius(radius);
        setCornerShape();
    }

    private void initialize(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeableImageView);

        try {
            // Get values from the TypedArray
            setCornerType(CornerType.values()[typedArray.getInt(R.styleable.ShapeableImageView_cornerMode, /* default value */ CornerType.DEFAULT.ordinal())]);
            setRadius(typedArray.getDimension(R.styleable.ShapeableImageView_radius, /* default value */ -1));

            // Apply corner shape if both radius and corner type are specified
            if (radius > -1 && getCornerType() != CornerType.DEFAULT)
                setCornerShape();

        } finally {
            // Ensure the TypedArray is recycled to avoid memory leaks
            typedArray.recycle();
        }
    }

    /**
     * Called when the size of the view changes.
     * Automatically sets rounded corners if the current corner type is DEFAULT.
     *
     * @param width     The new width of the view.
     * @param height    The new height of the view.
     * @param oldWidth  The old width of the view.
     * @param oldHeight The old height of the view.
     */
    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        if (getCornerType() == CornerType.DEFAULT)
            setCornerShape(CornerType.ROUNDED, Math.min(width, height) * (50 / 100f));
    }

    /**
     * Gets the radius of the corners.
     *
     * @return The radius of the corners.
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the corners.
     *
     * @param radius The radius to be set.
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * Gets the current corner type.
     *
     * @return The current corner type.
     */
    public CornerType getCornerType() {
        return cornerType;
    }

    /**
     * Sets the corner type.
     *
     * @param cornerType The corner type to be set.
     */
    public void setCornerType(CornerType cornerType) {
        this.cornerType = cornerType;
    }
}
