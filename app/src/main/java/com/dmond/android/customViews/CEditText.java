package com.dmond.android.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.Gravity;


import com.dmond.android.myStatics;

public class CEditText extends AppCompatEditText {
    Context ctx;

    public CEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ctx = context;
        init();
    }

    public CEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
        init();
    }

    public CEditText(Context context) {
        super(context);
        ctx = context;
        init();
    }

    public void init() {

        setOnTouchListener(null);


//        int right = (int) getResources().getDimension(R.dimen.edit_text_padding_right);
//        int left = (int) getResources().getDimension(R.dimen.edit_text_padding_left);
//        int top = (int) getResources().getDimension(R.dimen.edit_text_padding_top);
//        int bottom = (int) getResources().getDimension(R.dimen.edit_text_padding_bottom);

//        setPadding(left, top, right, bottom);
//        if (LocaleHelper.getLanguage(getContext()).equals("fa")){
//            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), M.font);
//            setTypeface(tf);
//            setGravity(Gravity.RIGHT);
//        }else{
//            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), M.fontEn);
//            setTypeface(tf);
//            setGravity(Gravity.LEFT);
//        }
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), myStatics.font);
            setTypeface(tf);
            setGravity(Gravity.RIGHT);

    }
}