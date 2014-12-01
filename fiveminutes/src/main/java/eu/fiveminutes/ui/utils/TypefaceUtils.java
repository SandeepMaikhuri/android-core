package eu.fiveminutes.ui.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Hashtable;

import eu.fiveminutes.ui.TypefacedView;

/**
 * Genereal purpose tools for applying custom typefaces.
 */
public class TypefaceUtils {
  private static final String TAG = TypefaceUtils.class.getSimpleName();

  private static final Hashtable<String, Typeface> CACHE = new Hashtable<String, Typeface>();


  /**
   * Extracts the desired typeface from the given style attributes.
   *
   * @param typefacedView The view to apply the typeface to. Views that implement this interface should
   *                      have a styleable attribute "typeface."
   * @param context       context
   * @param attrs         passed in public view constructors
   * @param styleSet      Styleable array for this particular view
   * @param typefaceStyle Within the styleSet array, the id of "typeface." The value in specific xml attribute
   *                      should be the full filename of the AvailableTypeface, e.g. "GothamSSm-Book.otf".
   */
  public static void extractAndApplyTypeface(TypefacedView typefacedView, Context context, AttributeSet attrs,
                                             int[] styleSet, int typefaceStyle) {
    TypedArray typedArray = context.obtainStyledAttributes(attrs, styleSet);
    String availableTypefaceString = typedArray.getString(typefaceStyle);
    Typeface typeFace = getTypeface(context, availableTypefaceString);
    if (typeFace != null) {
      typefacedView.setTypeface(typeFace);
    }
  }

  public static void applyTypefaceToTextView(TextView textView, String typefaceName) {
    textView.setTypeface(getTypeface(textView.getContext(), typefaceName));
  }

  public static void applyTypeFaceToMenuItems(Context context, Menu menu, String typefaceName) {
    for (int i = 0; i < menu.size(); i++) {
      if (TextUtils.isEmpty(menu.getItem(i).getTitle())) {
        continue;
      }

      MenuItem menuItem = menu.getItem(i);
      menuItem.setTitle(applyTypeFaceToText(context, menuItem.getTitle(), typefaceName));
    }
  }

  /**
   * Return a spannable with the given typeface applied to the input char sequence.
   */
  public static SpannableString applyTypeFaceToText(Context context, CharSequence text, String typefaceName) {
    if (TextUtils.isEmpty(text)) {
      return new SpannableString(text);
    }

    SpannableString spannableString = new SpannableString(text);
    spannableString.setSpan(new TypefaceSpan(context, typefaceName), 0, spannableString.length(),
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    return spannableString;
  }

  public static Typeface getTypeface(Context context, String typefaceName) {
    try {

      synchronized (CACHE) {
        if (CACHE.containsKey(typefaceName)) {
          return CACHE.get(typefaceName);
        }

      }

      String assetName = "fonts/" + typefaceName;

      Typeface typeface = Typeface.createFromAsset(context.getAssets(), assetName);
      CACHE.put(typefaceName, typeface);
      return typeface;
    } catch (Exception e) {

      Log.e(TAG, "Could not create typeface from " + typefaceName, e);
      return null;
    }
  }


}
