package com.gacon.julien.moodtracker4.views.swipe;

// Vertical ViewPager

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class VerticalViewPager extends ViewPager {
    public VerticalViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    public VerticalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // Scroll Horizontally

    @Override
    public boolean canScrollHorizontally (int direction) {
        return false;
    }

    // Scroll Vertically

    @Override
    public boolean canScrollVertically (int direction) {
        return super.canScrollHorizontally(direction);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean toIntercept = super.onInterceptTouchEvent((flipXY(ev)));
        flipXY(ev);
        return toIntercept;
    }

    /* Customize the animation using PageTransformer :

    "The interface exposes a single method, transformPage().
    At each point in the screen's transition, this method is called once for each visible page
    (generally there's only one visible page) and for adjacent pages just off the screen.
    For example, if page three is visible and the user drags towards page four, transformPage() is called for pages two,
    three, and four at each step of the gesture." Google Doc
     */

    private static final class VerticalPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(@NonNull View view, float position) {
            final int pageWidth = view.getWidth();
            final int pageHeight = view.getHeight();


            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // (0,1]
                // Use slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(pageWidth * -position);
                float yPosition = position * pageHeight;
                view.setTranslationY(yPosition);
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    // OnTouchEvent for scrolling

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(flipXY(ev));
    }

    // Init PageTransformer and Scroll

    private void init() {
        setPageTransformer(true, new VerticalPageTransformer());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    // FlipXY for vertically scroll

    private MotionEvent flipXY(MotionEvent ev) {
        final float width = getWidth();
        final float height = getHeight();
        final float x = (ev.getY() / height) * width;
        final float y = (ev.getX() / width) * height;
        ev.setLocation(x, y);
        return ev;
    }

}
