package eu.fiveminutes.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

import eu.fiveminutes.R;
import eu.fiveminutes.ui.util.TypefaceUtils;

/**
 *
 */
public class TypefacedButton extends Button implements TypefacedView {

    public TypefacedButton(Context context) {
        super(context);
    }

    public TypefacedButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypefaceUtils.extractAndApplyTypeface(this, context, attrs, R.styleable.TypefacedButton, R.styleable.TypefacedButton_typeface);
    }

    public TypefacedButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypefaceUtils.extractAndApplyTypeface(this, context, attrs, R.styleable.TypefacedButton, R.styleable.TypefacedButton_typeface);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TypefacedButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypefaceUtils.extractAndApplyTypeface(this, context, attrs, R.styleable.TypefacedButton, R.styleable.TypefacedButton_typeface);
    }
}
