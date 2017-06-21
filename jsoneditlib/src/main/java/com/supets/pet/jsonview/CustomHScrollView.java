package com.supets.pet.jsonview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class CustomHScrollView extends HorizontalScrollView {
    private GestureDetector mGestureDetector;
    private OnTouchListener mGestureListener;

    private static final String TAG = "CustomHScrollView";


    /**
     * @param context Interface to global information about an application environment.
     * @function CustomHScrollView constructor
     */
    public CustomHScrollView(Context context) {
        super(context);
        mGestureDetector = new GestureDetector(new HScrollDetector());
        setFadingEdgeLength(0);
    }


    /**
     * @param context Interface to global information about an application environment.
     * @param attrs   A collection of attributes, as found associated with a tag in an XML document.
     * @function CustomHScrollView constructor
     */
    public CustomHScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mGestureDetector = new GestureDetector(new HScrollDetector());
        setFadingEdgeLength(0);
    }

    /**
     * @param context  Interface to global information about an application environment.
     * @param attrs    A collection of attributes, as found associated with a tag in an XML document.
     * @param defStyle style of view
     * @function CustomHScrollView constructor
     */
    public CustomHScrollView(Context context, AttributeSet attrs,
                             int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        mGestureDetector = new GestureDetector(new HScrollDetector());
        setFadingEdgeLength(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
    }

    // Return false if we're scrolling in the y direction
    class HScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (Math.abs(distanceX) > Math.abs(distanceY)) {
                return true;
            }

            return false;
        }
    }

} 