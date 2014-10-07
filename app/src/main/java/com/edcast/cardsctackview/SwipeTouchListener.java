package com.edcast.cardsctackview;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by saadati on 10/2/14.
 */
public class SwipeTouchListener implements View.OnTouchListener {
    private static final float CARDS_SWIPE_LENGTH = 200;
    public enum Orientation {Horizontal, Vertical};
    private float originalX = 0;
    private float originalY = 0;
    private float startMoveX = 0;
    private float startMoveY = 0;
    private View view;
    private OnCardMovement listener;
    private Orientation orientation = Orientation.Horizontal;

    public SwipeTouchListener(View view, OnCardMovement listener, Orientation orientation) {
        this.view = view;
        this.listener = listener;
        originalX = view.getX();
        originalY = view.getY();
        this.orientation = orientation;
    }

    public void setStartPoints(float x, float y) {
        startMoveX = x;
        startMoveY = y;
    }

    public boolean onTouch(View v, MotionEvent event) {
        boolean processed = true;
        float X = event.getRawX();
        float Y = event.getRawY();
        float deltaX = X - startMoveX;
        float deltaY = Y - startMoveY;
        float distance = orientation == Orientation.Horizontal? deltaX: deltaY;
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                originalX = view.getX();
                originalY = view.getY();
                startMoveX = X;
                startMoveY = Y;
                break;
            case MotionEvent.ACTION_UP:
                boolean swiped = false;
                if ( Math.abs(distance) > CARDS_SWIPE_LENGTH ) { // Moved far enough to be an event
                    if ( orientation == Orientation.Vertical ) {
                        swiped = true;
                        if (deltaY > 0) {
                            handleSwipeDown();
                        } else {
                            handleSwipeUp();
                        }
                    } else { // Horizontal move
                        swiped = true;
                        if ( deltaX > 0 ) {
                            handleSwipeRight();
                        } else {
                            handleSwipeLeft();
                        }
                    }
                }
                if ( !swiped ) {
                    view.setX(originalX);
                    view.setY(originalY);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if ( orientation == Orientation.Vertical ) {
                    view.setTranslationY(deltaY);
                }
                if ( orientation == Orientation.Horizontal ) {
                    view.setTranslationX(deltaX);
                }
                break;
            default:
                processed = false;
                break;
        }
        listener.onMovedFromCenter(distance);
        if (processed) {
            view.invalidate();
        }
        return processed;
    }

    private void handleSwipeLeft() {
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_left);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                listener.onSwipedLeft();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    private void handleSwipeRight() {
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_right);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                listener.onSwipedRight();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    private void handleSwipeUp() {
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_up);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                listener.onSwipedUp();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    private void handleSwipeDown() {
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_down);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                listener.onSwipedDown();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    public interface OnCardMovement {
        public void onSwipedLeft();
        public void onSwipedRight();
        public void onSwipedUp();
        public void onSwipedDown();
        public void onMovedFromCenter(float distance);
    }

}