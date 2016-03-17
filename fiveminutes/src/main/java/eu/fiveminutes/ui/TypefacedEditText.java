package eu.fiveminutes.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import eu.fiveminutes.R;
import eu.fiveminutes.ui.util.TypefaceUtils;


/**
 *
 */
public class TypefacedEditText extends EditText implements TypefacedView{

  public TypefacedEditText(Context context) {
    super(context);
  }

  public TypefacedEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    TypefaceUtils.extractAndApplyTypeface(this, context, attrs, R.styleable.TypefacedEditText,
        R.styleable.TypefacedEditText_typeface);
  }

  public TypefacedEditText(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    TypefaceUtils.extractAndApplyTypeface(this, context, attrs, R.styleable.TypefacedEditText, R.styleable.TypefacedEditText_typeface);
  }
}
