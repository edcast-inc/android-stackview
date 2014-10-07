package com.edcast.cardsctackview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by saadati on 10/4/14.
 */
public class MyCardStackView extends AbstractCardsStackView {
    private static final String TAG = "MyCardsStackView";

    public MyCardStackView(Context context) {
        super(context);
    }

    public MyCardStackView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCardStackView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onStackGettingEmpty() {
        Log.d(TAG, "Stack getting empty");
    }

    @Override
    public void onSwipedLeft() {
        Log.d(TAG, "Swiped Left");
        super.onSwipedLeft();
    }

    @Override
    public void onSwipedRight() {
        Log.d(TAG, "Swiped Right");
        super.onSwipedRight();
    }

    @Override
    public void onSwipedUp() {
        Log.d(TAG, "Swiped Up");
        super.onSwipedUp();
    }

    @Override
    public void onSwipedDown() {
        Log.d(TAG, "Swiped Down");
        super.onSwipedDown();
    }
}
