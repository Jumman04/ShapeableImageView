package com.jummania;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class ShapeableImageView extends com.google.android.material.imageview.ShapeableImageView {

    private int cornerMode = CornerMode.ROUNDED.ordinal();
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
                .setAllCorners(getCornerMode().ordinal(), getRadius())
                .build());
    }

    public void setCornerShape(CornerMode cornerMode, float radius) {
        setCornerMode(cornerMode);
        setRadius(radius);
        setCornerShape();
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeableImageView);

        try {
            // Get values from the TypedArray
            setCornerMode(CornerMode.values()[typedArray.getInt(R.styleable.ShapeableImageView_cornerMode, /* default value */ CornerMode.ROUNDED.ordinal())]);
            setRadius(typedArray.getDimension(R.styleable.ShapeableImageView_radius, /* default value */ -1));
            // Use the obtained values as needed
            // ...

        } finally {
            // Ensure the TypedArray is recycled to avoid memory leaks
            typedArray.recycle();
        }


        if (radius > -1)
            setCornerShape();

    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        if (radius == -1) {
            setRadius((float) width / 2);
            setCornerShape();
        }
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public CornerMode getCornerMode() {
        if (cornerMode == 0)
            return CornerMode.ROUNDED;
        else return CornerMode.CUT;
    }

    public void setCornerMode(CornerMode cornerMode) {
        this.cornerMode = cornerMode.ordinal();
    }
}
