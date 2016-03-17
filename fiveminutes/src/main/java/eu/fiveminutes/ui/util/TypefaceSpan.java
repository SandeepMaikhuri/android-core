package eu.fiveminutes.ui.util;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class TypefaceSpan extends MetricAffectingSpan {

  private Typeface typeface;

  /**
   * Load the Typeface and apply to a Spannable.
   */
  public TypefaceSpan(Context context, String typefaceName) {
    typeface = TypefaceUtils.getTypeface(context, typefaceName);
  }

  @Override
  public void updateMeasureState(TextPaint tp) {
    updateTextPaint(tp);
  }

  @Override
  public void updateDrawState(TextPaint tp) {
    updateTextPaint(tp);
  }

  private void updateTextPaint(TextPaint tp){
    tp.setTypeface(typeface);

    // Note: This flag is required for proper typeface rendering
    tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
  }
}