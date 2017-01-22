package com.dmond.android.customViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;



/**
 * Created by hosein on 6/7/16.
 */

public class cTextView extends AppCompatTextView {


    public cTextView(Context context) {
        super(context);
        init(context);
    }

    public cTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public cTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);

    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);


    }
    public void init(Context context){
//        if (LocaleHelper.getLanguage(getContext()).equals("fa")){
//            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), M.font);
//            setTypeface(tf);
//           // setGravity(Gravity.RIGHT);
//        }else{
//            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), M.fontEn);
//            setTypeface(tf);
//           // setGravity(Gravity.LEFT);
//        }

    }

}
