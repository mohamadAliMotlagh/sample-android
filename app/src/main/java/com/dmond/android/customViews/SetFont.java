package com.dmond.android.customViews;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SetFont {

		private ViewGroup viewGroup;
		private Typeface typeFace;

		public SetFont(ViewGroup viewGroup,
					   Typeface typeFace) {

			this.typeFace = typeFace;
			this.viewGroup = viewGroup;

		}

		public int Apply() {

			int count = viewGroup.getChildCount();
			int efectedItems = 0;
			View v;

			for (int i = 0; i < count; i++) {
				v = viewGroup.getChildAt(i);
				if (v instanceof TextView || v instanceof EditText || v instanceof Button) {
					((TextView) v).setTypeface(typeFace);
				}
				else if (v instanceof ViewGroup)
					efectedItems += new SetFont((ViewGroup) v, typeFace).Apply();
			}
			return efectedItems;
		}

	}