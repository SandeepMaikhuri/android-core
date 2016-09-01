package eu.fiveminutes.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.view.PagerTabStrip;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import eu.fiveminutes.R;
import eu.fiveminutes.ui.util.TypefaceUtils;


public class TypefacedPagerTabStrip extends PagerTabStrip implements TypefacedView {

    private String typeface;

    public TypefacedPagerTabStrip(Context context) {
        super(context);
        init(context, null);
    }

    public TypefacedPagerTabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TypefacedTextView);
        typeface = typedArray.getString(R.styleable.TypefacedTextView_typeface);
    }

    @Override
    public void setTypeface(final Typeface typeface) {

    }

    public void applyTypeface() {
        for (int i = 0; i < getChildCount(); ++i) {
            View nextChild = getChildAt(i);
            if (nextChild instanceof TextView) {
                TypefaceUtils.applyTypefaceToTextView((TextView) nextChild, typeface);
            }
        }
    }
}
