package ai.aiprog.template.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Author       : Arvindo Mondal
 * Created on   : 13-10-2019
 * Email        : arvindo@aiprog.ai
 * Company      : AIPROG
 * Designation  : Programmer
 * About        : I am a human can only think, I can't be a person like machine which have lots of memory and knowledge.
 * Quote        : No one can measure limit of stupidity but stupid things bring revolutions
 * Strength     : Never give up
 * Motto        : To be known as great Mathematician
 * Skills       : Algorithms and logic
 * Website      : www.aiprog.ai
 */
public class Dimensions {

    private static int WIDTH_TARGET_INFO_IMAGE = 0;
    private static int HEIGHT_TARGET_INFO_IMAGE = 0;
    private static int HEIGHT_SCREEN_HALF = 0;


    private Dimensions() {
    }

    public static int getScreenWidth(Context context) {
        Point size = new Point();
        ((Activity) context).getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }

    public static int getScreenHeight(Context context) {
        Point size = new Point();
        ((Activity) context).getWindowManager().getDefaultDisplay().getSize(size);
        return size.y;
    }

    public static int getScreenHeightFalf(Context context) {
        if(HEIGHT_SCREEN_HALF == 0) {
            return HEIGHT_SCREEN_HALF = getScreenHeight(context)/2;
        }
        else{
            return HEIGHT_SCREEN_HALF;
        }
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    private static float getPixelScaleFactor(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static int dpToPx(Context context, int dp) {
        return Math.round(dp * getPixelScaleFactor(context));
    }

    public static int dpToPx2(Context context, int dp) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        if (wm != null) {
            display = wm.getDefaultDisplay();
            display.getMetrics(displaymetrics);
        }


        return (int) (dp * displaymetrics.density + 0.5f);
    }

    public static int dpToPixel(int dp, Resources resources) {

        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.getDisplayMetrics()
        );
        return (int) px;
    }

    public static float convertDpToPixel(float dp, Resources resources) {
        return dp * ((float) resources.getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }


    public static int getTextViewWeight(View textView) {
        WindowManager wm =
                (WindowManager) textView.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = null;
        if (wm != null) {
            display = wm.getDefaultDisplay();
        }

        int deviceWidth;

        Point size = new Point();
        display.getSize(size);
        deviceWidth = size.x;

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredWidth();
    }

    public static int getTargetProfileImageWidth(Context context){
        if(WIDTH_TARGET_INFO_IMAGE == 0) {
            int totalLength = Dimensions.getScreenWidth(context);
            int sideMargin = Dimensions.dpToPx(context, 10);    // 10 = layoutMarginTarget
            int length = totalLength - (sideMargin * 2);
            int totalWidth = length / 6;       // 6 is divide ratio
            int spaceMargin = Dimensions.dpToPx(context, 2);
//            int width = totalWidth - spaceMargin;     //6 card gap is 5

//            int widthHalf = width / 2;     //single unit of width
//            int height = widthHalf * 3;         // as 3:2 = h:w

            WIDTH_TARGET_INFO_IMAGE = totalWidth - spaceMargin;
        }
        return WIDTH_TARGET_INFO_IMAGE;
    }

    public static int getTargetProfileImageHeight(Context context){
        if(HEIGHT_TARGET_INFO_IMAGE == 0) {
            int totalLength = Dimensions.getScreenWidth(context);
            int sideMargin = Dimensions.dpToPx(context, 10);    // 10 = layoutMarginTarget
            int length = totalLength - (sideMargin * 2);
            int totalWidth = length / 6;       // 6 is divide ratio
            int spaceMargin = Dimensions.dpToPx(context, 2);
            int width = totalWidth - spaceMargin;     //6 card gap is 5

            int widthHalf = width / 2;     //single unit of width

            HEIGHT_TARGET_INFO_IMAGE = widthHalf * 3;
        }
        return HEIGHT_TARGET_INFO_IMAGE;
    }

}
