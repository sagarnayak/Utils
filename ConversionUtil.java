package com.collanomics.android.wms.driver.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * class to make various conversions.
 */
public class ConversionUtil {
    /**
     * to convert dp to px
     *
     * @param dp      your required dp
     * @param context context
     * @return the result px value
     */
    public static int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
