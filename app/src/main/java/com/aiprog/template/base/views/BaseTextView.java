package com.aiprog.template.base.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Author       : Arvindo Mondal
 * Created on   : 18-10-2019
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
public class BaseTextView extends AppCompatTextView {

    public BaseTextView(Context context) {
        super(context);
        inti(context, null);
    }

    public BaseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inti(context, attrs);
    }

    public BaseTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inti(context, attrs);
    }

    private void inti(Context context, AttributeSet attrs) {

    }
}
