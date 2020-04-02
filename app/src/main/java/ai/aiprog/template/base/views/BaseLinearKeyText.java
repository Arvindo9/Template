package ai.aiprog.template.base.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.LinearLayoutCompat;

import java.util.ArrayList;
import java.util.List;

import ai.aiprog.template.R;

/**
 * Author       : Arvindo Mondal
 * Created on   : 19-10-2019
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
public class BaseLinearKeyText extends LinearLayoutCompat {
    @LayoutRes
    private int layout;
    private float topMargin = 20f;

    private ArrayList<BaseModelView> list;

    public BaseLinearKeyText(Context context) {
        super(context);
        init(context, null);
        list = new ArrayList<>();
    }

    public BaseLinearKeyText(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttributes(attrs);
        init(context, attrs);
        list = new ArrayList<>();
    }

    public BaseLinearKeyText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttributes(attrs);
        init(context, attrs);
        list = new ArrayList<>();
    }

    public void addItems(List<BaseModelView> model) {
        list.clear();
        list.addAll(model);
        onItemUpdates();
    }

    public void clearItems() {
        list.clear();
    }

    private void getAttributes(AttributeSet attrs) {
        TypedArray type = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.BaseLinearKeyText,
                0, 0);

        try {
            int textBackground = type.getColor(R.styleable.BaseLinearKeyText_TextBackground,
                    Color.parseColor("#FFFFFF"));
            int textColor = type.getColor(R.styleable.BaseLinearKeyText_TextColor,
                    Color.parseColor("#FFFFFF"));
            layout = type.getResourceId(R.styleable.BaseLinearKeyText_Layout, 0);
            topMargin = type.getDimension(R.styleable.BaseLinearKeyText_TopMargin, 0);
        } finally {
            type.recycle();
        }
    }

    private void init(Context context, AttributeSet attrs) {

    }

    private void onItemUpdates() {
        TextView textView;
        for (int i = 0; i < list.size(); i++) {
            LinearLayoutCompat mainView = (LinearLayoutCompat)
                    LayoutInflater.from(getContext()).inflate(layout, null);

            //Resize layout
            LinearLayoutCompat.LayoutParams params =
                    new LinearLayoutCompat.LayoutParams(LayoutParams.MATCH_PARENT,
                            LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, (int) topMargin, 0, 0);
            mainView.setLayoutParams(params);

            //Add data to TextView
            textView = mainView.findViewById(R.id.generalText);
            textView.setText(list.get(i).getData());

            this.addView(mainView);
        }
    }

}
