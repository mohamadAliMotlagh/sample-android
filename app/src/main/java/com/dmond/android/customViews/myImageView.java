package com.dmond.android.customViews;

/**
 * Created by hosein on 5/11/15.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class myImageView extends ImageView {
    public myImageView(Context context) {
        super(context);
    }

    public myImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public myImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
    }
}
