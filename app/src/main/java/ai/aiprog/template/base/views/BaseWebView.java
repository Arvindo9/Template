package ai.aiprog.template.base.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.webkit.WebView;

import ai.aiprog.template.base.utils.Dimensions;

/**
 * Author       : Arvindo Mondal
 * Created date : 13-08-2019
 * Email        : arvindo@aiprog.in
 * Company      : AIPROG
 * Designation  : Programmer
 * About        : I am a human can only think, I can't be a person like machine which have lots of memory and knowledge.
 * Quote        : No one can measure limit of stupidity but stupid things bring revolutions
 * Strength     : Never give up
 * Motto        : To be known as great Mathematician
 * Skills       : Algorithms and logic
 * Website      : www.aiprog.in
 */
public class BaseWebView extends WebView {
    private Context context;
    private int width;
    private int height;
    private int radius;
    private Path path;
    private RectF rectF;


    /**
     * Construct a new WebView with a Context object.
     *
     * @param context A Context object used to access application assets.
     */
    public BaseWebView(Context context) {
        super(context);
        init(context, null);
    }

    /**
     * Construct a new WebView with layout parameters.
     *
     * @param context A Context object used to access application assets.
     * @param attrs   An AttributeSet passed to our parent.
     */
    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * Construct a new WebView with layout parameters and a default style.
     *
     * @param context      A Context object used to access application assets.
     * @param attrs        An AttributeSet passed to our parent.
     * @param defStyleAttr
     */
    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        this.path = new Path();
    }

    // This method gets called when the view first loads, and also whenever the
    // view changes. Use this opportunity to save the view's width and height.
    @Override
    protected void onSizeChanged(int newWidth, int newHeight, int oldWidth, int oldHeight){
        super.onSizeChanged(newWidth, newHeight, oldWidth, oldHeight);
        width = newWidth;
        height = newHeight;
        radius = Dimensions.dpToPx(context, 5);
        rectF = new RectF(0, getScrollY(), width,getScrollY() + height);
    }


    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        path.setFillType(Path.FillType.INVERSE_WINDING);
//        path.addRoundRect(new RectF(0, getScrollY(), width,
//                getScrollY() + height), radius, radius, Path.Direction.CW);
        path.addRoundRect(rectF, radius, radius, Path.Direction.CW);
        canvas.drawPath(path, createPorterDuffClearPaint());
    }

    private Paint createPorterDuffClearPaint(){
        Paint paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        return paint;
    }
}
