package com.jummania.shapeableimageview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jummania.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShapeableImageView shapeableImageView = findViewById(R.id.shapeableImageView);
       // shapeableImageView.setShape(CornerType.CUT, 100);
       // shapeableImageView.setShapeType(ShapeType.SPECIFIC_CORNER_ROUNDED);
    }
}