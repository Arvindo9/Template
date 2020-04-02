package ai.aiprog.template.base.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.LinearLayoutCompat;

import java.util.HashMap;
import java.util.Map;

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
public class BaseLinearTableText extends LinearLayoutCompat {
    @LayoutRes
    private int layout;
    private float topMargin = 20f;

    private HashMap<String, String> map;

    public BaseLinearTableText(Context context) {
        super(context);
        init(context, null);
        map = new HashMap<>();
    }

    public BaseLinearTableText(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttributes(attrs);
        init(context, attrs);
        map = new HashMap<>();
    }

    public BaseLinearTableText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttributes(attrs);
        init(context, attrs);
        map = new HashMap<>();
    }

    public void addItems(Map<String, String> model) {
        map.clear();
        map.putAll(model);
        onItemUpdates();
    }

    public void clearItems() {
        map.clear();
    }

    private void getAttributes(AttributeSet attrs) {
        TypedArray type = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.BaseLinearTableText,
                0, 0);

        try {
            int textBackground = type.getColor(R.styleable.BaseLinearTableText_TextBackground,
                    Color.parseColor("#FFFFFF"));
            int textColor = type.getColor(R.styleable.BaseLinearTableText_TextColor,
                    Color.parseColor("#FFFFFF"));
            layout = type.getResourceId(R.styleable.BaseLinearTableText_Layout, 0);
            topMargin = type.getDimension(R.styleable.BaseLinearTableText_TopMargin, 0);
        } finally {
            type.recycle();
        }
    }

    private void init(Context context, AttributeSet attrs) {

    }

    private void onItemUpdates() {
        TextView textView;
        String key;
        for (int i = 0; i < map.size(); i++) {
            LinearLayoutCompat mainView = (LinearLayoutCompat)
                    LayoutInflater.from(getContext()).inflate(layout, null);

            //Resize layout
            LinearLayoutCompat.LayoutParams params =
                    new LinearLayoutCompat.LayoutParams(LayoutParams.MATCH_PARENT,
                            LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, (int) topMargin, 0, 0);
            mainView.setLayoutParams(params);

            //Add data to TextView
            textView = mainView.findViewById(R.id.generalTextLeft);
            key = (String) map.keySet().toArray()[i];
            textView.setText(key);
            textView = mainView.findViewById(R.id.generalTextRight);
            textView.setText(map.get(key));

            this.addView(mainView);
        }
    }

}
