package com.example.user.airscort_agriculture.Classes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RadioButton;

/**
 * Created by User on 15/12/2015.
 */
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RadioButton;

import com.example.user.airscort_agriculture.R;

/**
 * This class implements a radio button with custom gravity.
 */
public class RadioButtonView extends RadioButton {

    private Drawable mButtonDrawable;

    public RadioButtonView(Context context) {
        super(context);
    }

    public RadioButtonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadioButtonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        final TypedArray attributes = context.obtainStyledAttributes(attrs,
                R.styleable.RadioButtonCenter, defStyle, 0);

        try {
            mButtonDrawable = attributes.getDrawable(R.styleable.RadioButtonCenter_android_button);
        } finally {
            attributes.recycle();
        }
        setButtonDrawable(android.R.color.transparent);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mButtonDrawable != null) {
            mButtonDrawable.setState(getDrawableState());
            final int verticalGravity = getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
            final int height = mButtonDrawable.getIntrinsicHeight();

            int y = 0;
            switch (verticalGravity) {
                case Gravity.BOTTOM:
                    y = getHeight() - height;
                    break;

                case Gravity.CENTER_VERTICAL:
                    y = (getHeight() - height) / 2;
                    break;
            }

            int buttonWidth = mButtonDrawable.getIntrinsicWidth();
            int buttonLeft = (getWidth() - buttonWidth) / 2;
            mButtonDrawable.setBounds(buttonLeft, y, buttonLeft + buttonWidth, y + height);
            mButtonDrawable.draw(canvas);
        }
    }
}