package com.example.cometapp;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CometAnimation extends View {
    private Paint circlePaint;
    private float centerX, centerY;
    private float radiusX, radiusY;
    private double angle = 0; // Initial angle

    public CometAnimation(Context context) {
        super(context);
        init();
    }

    private void init() {
        //color of the comet
        circlePaint = new Paint();
        circlePaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Set background color to green
        canvas.drawColor(Color.parseColor("#5F8575"));
        // Calculate center of the screen
        centerX = getWidth() / 2f;
        centerY = getHeight() / 2f;
        // Calculate radii of the ellipse
        radiusX = getWidth() / 2f;
        radiusY = getHeight() / 2f;
        // Draw comet
        drawComet(canvas);
    }

    private void drawComet(Canvas canvas) {
        // Update angle dynamically
        angle = (angle + 0.01) % (2 * Math.PI); // Adjust speed as needed

        // Calculate comet position on the elliptical path
        float x = centerX + (float) (Math.cos(angle) * (radiusX - 20)); // Subtract comet radius to keep it within bounds
        float y = centerY + (float) (Math.sin(angle) * (radiusY - 20)); // Subtract comet radius to keep it within bounds

        // Draw comet
        canvas.drawCircle(x, y, 20, circlePaint); // Adjust comet size as needed

        // Invalidate the view to trigger redraw
        invalidate();
    }
}