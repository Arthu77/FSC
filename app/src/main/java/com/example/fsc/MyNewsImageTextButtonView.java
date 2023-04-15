package com.example.fsc;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class MyNewsImageTextButtonView extends androidx.appcompat.widget.AppCompatTextView {

    public MyNewsImageTextButtonView(Context context){
        super(context);
    }

    public MyNewsImageTextButtonView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    public MyNewsImageTextButtonView(Context context, AttributeSet attributeSet, int defStyle){
        super(context,attributeSet,defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundResource(R.color.gray);
    }

    @Override
    public void setClickable(boolean clickable) {
        super.setClickable(true);
    }
}
