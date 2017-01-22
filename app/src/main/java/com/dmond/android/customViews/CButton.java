package com.dmond.android.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.dmond.android.myStatics;


public class CButton extends AppCompatButton {
    Context ctx;

    public CButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ctx = context;
        init();
    }

    public CButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
        init();
    }

    public CButton(Context context) {
        super(context);
        ctx = context;
        init();
    }

    public void init() {
        setOnTouchListener(null);
//        if (LocaleHelper.getLanguage(getContext()).equals("fa")){
//
//            //setGravity(Gravity.RIGHT);
//        }else{
//            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), myStatics.fontEn);
//            setTypeface(tf);
//           // setGravity(Gravity.LEFT);
//        }
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), myStatics.font);
        setTypeface(tf);
    }
}