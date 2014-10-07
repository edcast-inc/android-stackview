package com.edcast.cardsctackview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

/**
 * Created by Shahin Saadati on 10/4/14.
 */
public class AbstractCardsStackView extends FrameLayout implements SwipeTouchListener.OnCardMovement {
    private static final int STACK_BUFFER = 5;
    private int index = 0;
    private View emptyStackView = null;
    private boolean emptyPageAdded = false;
    private BaseAdapter adapter;
    private SwipeTouchListener.Orientation orientation = SwipeTouchListener.Orientation.Horizontal;

    public AbstractCardsStackView(Context context) {
        super(context);
    }

    public AbstractCardsStackView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbstractCardsStackView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        index = 0;
        loadNext();
        appendBottomView();
    }

    public BaseAdapter getAdapter() {
        return adapter;
    }

    public void setOrientation(SwipeTouchListener.Orientation orientation) {
        this.orientation = orientation;
    }

    public void setEmptyView(View view) {
        emptyStackView = view;
    }

    private void loadNext() {
        removeTopView();
        appendBottomView();
    }

    private boolean removeTopView() {
        boolean removed = false;
        int children = getChildCount();
        if ( children > 0 ) {
            View topView = getChildAt(children-1);
            if ( topView != emptyStackView ) {
                removeView(topView);
                removed = true;
            }
            children = getChildCount();
            if ( children > 0 ) {
                topView = getChildAt(children - 1);
                if ( topView.getAlpha() < 0.98f ) {
                    topView.setAlpha(1.0f);
                }
            }
        }
        return removed;
    }

    private boolean appendBottomView() {
        boolean added = false;
        if ( index < adapter.getCount() ) {
            View nextBottomView = adapter.getView(index++, null, this);
            if (nextBottomView != null) {
                nextBottomView.setOnTouchListener(new SwipeTouchListener(nextBottomView, this, orientation));
                addView(nextBottomView, 0);
                added = true;
                if (index + STACK_BUFFER > adapter.getCount()) {
                    onStackGettingEmpty();
                }
            }
        } else if ( emptyStackView != null && !emptyPageAdded ) {
            addView(emptyStackView, 0);
            emptyPageAdded = true;
            added = true;
        }
        return added;
    }

    @Override
    public void onSwipedLeft() {
        loadNext();
    }

    @Override
    public void onSwipedRight() {
        loadNext();
    }

    @Override
    public void onSwipedUp() {
        loadNext();
    }

    @Override
    public void onSwipedDown() {
        loadNext();
    }

    @Override
    public void onMovedFromCenter(float distance) {
        if ( getChildCount() > 1 ) {
            View bottomView = getChildAt(0);
            float alpha = Math.max(-1.0f, Math.min(1.0f, distance / 300.0f));
            bottomView.setAlpha(Math.abs(alpha));
        }
    }

    public void onStackGettingEmpty() {

    }
}
