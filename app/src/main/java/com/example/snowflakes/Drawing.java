package com.example.snowflakes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import java.util.Random;

class Snowflake {
    public Snowflake() {
        Random r = new Random();
        this.x = r.nextInt(Drawing.view_Width);
        do {
            this.velocity = r.nextFloat() * 4;
        }while (this.velocity < 0.4);
        rad = new Paint(Drawing.p);
        do {
            rad.setTextSize(r.nextInt(250));
        }while (rad.getTextSize() < 20);
        ror = r.nextInt(359);
        rad.setTextAlign(Paint.Align.CENTER);
        rad.setColor(Color.parseColor("#E6E6FA"));
    }

    float x, y, velocity, ror;
    Paint rad;
    public void fall() {
        y += velocity;
        if(y > Drawing.view_Height + this.rad.getTextSize()) this.y = - this.rad.getTextSize();
    }
}

public class Drawing extends View {
    Snowflake[] snowflakes;
    static Paint p = new Paint();
    Resources res = getResources();
    static public int view_Height = 2000;
    static public int view_Width = 2000;

    public Drawing(Context context) {
        super(context);
    }

    public Drawing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        snowflakes = new Snowflake[100];
        for (int i = 0; i < snowflakes.length; i++) {
            snowflakes[i] = new Snowflake();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        for (int i = 0; i < snowflakes.length; i++) {
            canvas.drawText("*", snowflakes[i].x, snowflakes[i].y, snowflakes[i].rad);
        }
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        view_Height = height;
        view_Width = width;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (int i = 0; i < snowflakes.length; i++) {
            snowflakes[i].fall();
        }
        invalidate();
        return true;
    }
}
