package com.jummania;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.jummania.type.CornerType;
import com.jummania.type.ShapeType;

public class ShapeableImageView extends com.google.android.material.imageview.ShapeableImageView {

    private ShapeType shapeType = ShapeType.ROUNDED;
    private CornerType cornerType = CornerType.ROUNDED;
    private float radius = -1;
    private int width, height;
    private boolean shapeSet = false;

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

    public void set() {
        setShapeAppearanceModel(getShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(getCornerType().ordinal(), getRadius())
                .build());
    }

    public CornerType getCornerType() {
        return cornerType;
    }

    public void setCornerType(CornerType cornerType) {
        this.cornerType = cornerType;
    }

    public void setShape(CornerType cornerType, float radius) {
        setCornerType(cornerType);
        setRadius(radius);
        set();
    }

    private void setAll(CornerType cornerType, ShapeType shapeType, float radius) {
        setCornerType(cornerType);
        setRadius(radius);
        shapeSet = getRadius() == -1f;
        if (shapeSet)
            setShapeType(shapeType);
        else
            set();
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeableImageView);

        try {
            setAll(CornerType.values()[typedArray.getInt(R.styleable.ShapeableImageView_cornerType, CornerType.ROUNDED.ordinal())],
                    ShapeType.values()[typedArray.getInt(R.styleable.ShapeableImageView_shapeType, /* default value */ ShapeType.ROUNDED.ordinal())],
                    typedArray.getFloat(R.styleable.ShapeableImageView_radius, /* default value */ -1));

        } finally {
            // Ensure the TypedArray is recycled to avoid memory leaks
            typedArray.recycle();
        }

    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        this.width = width;
        this.height = height;
        if (shapeSet) setShapeType(getShapeType());
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        shapeSet = true;
        this.shapeType = shapeType;
        switch (getShapeType()) {
            case ROUNDED:
                setRadius(width, height, 50, CornerType.ROUNDED);
                break;
            case CORNER_CUT:
                setRadius(width, height, 15, CornerType.CUT);
                break;
            case DIAMOND_CUT:
                setRadius(width, height, 50, CornerType.CUT);
                break;
            case ROUNDED_SQUARE:
                setRadius(width, height, 10, CornerType.ROUNDED);
                break;
            case SPECIFIC_CORNER_CUT:
                setRadius(width, height, CornerType.CUT);
                break;
            case SPECIFIC_CORNER_ROUNDED:
                setRadius(width, height, CornerType.ROUNDED);
                break;
        }
    }

    private void setRadius(int width, int height, int radius, CornerType cornerType) {
        setCornerType(cornerType);
        setShape(getCornerType(), Math.min(width, height) * (radius / 100f));
    }

    private void setRadius(int width, int height, CornerType cornerType) {
        setRadius(Math.min(width, height) * (50 / 100f));
        setCornerType(cornerType);
        setShapeAppearanceModel(getShapeAppearanceModel()
                .toBuilder()
                .setTopRightCorner(getCornerType().ordinal(), getRadius())
                .setBottomLeftCorner(getShapeType().ordinal(), getRadius())
                .build());
    }
}
