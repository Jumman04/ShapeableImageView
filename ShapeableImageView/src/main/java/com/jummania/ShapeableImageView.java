package com.jummania;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class ShapeableImageView extends com.google.android.material.imageview.ShapeableImageView {

    private CornerType cornerType = CornerType.DEFAULT;
    private float radius = -1;

    public ShapeableImageView(Context context) {
        super(context);
        init(context, null);
    }

    public ShapeableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ShapeableImageView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public void setCornerShape() {
        this.setShapeAppearanceModel(this.getShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(getCornerFamily(), getRadius())
                .build());
    }

    private int getCornerFamily() {
        return getCornerType().getCornerType();
    }

    public void setCornerShape(CornerType cornerType, float radius) {
        setCornerType(cornerType);
        setRadius(radius);
        setCornerShape();
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeableImageView);

        try {
            // Get values from the TypedArray
            setCornerType(CornerType.values()[typedArray.getInt(R.styleable.ShapeableImageView_cornerMode, /* default value */ CornerType.DEFAULT.ordinal())]);
            setRadius(typedArray.getDimension(R.styleable.ShapeableImageView_radius, /* default value */ -1));
            // Use the obtained values as needed
            // ...

            if (radius > -1 && getCornerType() != CornerType.DEFAULT)
                setCornerShape();

        } finally {
            // Ensure the TypedArray is recycled to avoid memory leaks
            typedArray.recycle();
        }

    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        if (getCornerType() == CornerType.DEFAULT)
            setCornerShape(CornerType.ROUNDED, Math.min(width, height) * (50 / 100f));

    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public CornerType getCornerType() {
        return cornerType;
    }

    public void setCornerType(CornerType cornerType) {
        this.cornerType = cornerType;
    }
}