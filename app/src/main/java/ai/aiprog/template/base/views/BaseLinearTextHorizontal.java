package ai.aiprog.template.base.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.LinearLayoutCompat;

import java.util.ArrayList;
import java.util.List;

import ai.aiprog.template.R;
import ai.aiprog.template.base.utils.Dimensions;

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
public class BaseLinearTextHorizontal extends LinearLayoutCompat {
    @LayoutRes
    private int layout;
    @LayoutRes
    private int layoutText;
    private float rightMargin = 20f;
    private ArrayList<BaseModelView> list;
    private Listener listener;
    private float topMargin = 10f;
    private float sideMargin = 10f;
    private View lastView;
    private View lastViewTmp;

    public BaseLinearTextHorizontal(Context context) {
        super(context);
        init(context, null);
        list = new ArrayList<>();
    }

    public BaseLinearTextHorizontal(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttributes(attrs);
        init(context, attrs);
        list = new ArrayList<>();
    }

    public BaseLinearTextHorizontal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttributes(attrs);
        init(context, attrs);
        list = new ArrayList<>();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
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
        TypedArray type = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.BaseLinearTextHorizontal,
                0, 0);

        try {
            int textBackground = type.getColor(R.styleable.BaseLinearTextHorizontal_TextBackground,
                    Color.parseColor("#FFFFFF"));
            int textColor = type.getColor(R.styleable.BaseLinearTextHorizontal_TextColor,
                    Color.parseColor("#FFFFFF"));
            layout = type.getResourceId(R.styleable.BaseLinearTextHorizontal_Layout, 0);
            layoutText = type.getResourceId(R.styleable.BaseLinearTextHorizontal_LayoutText, 0);
            rightMargin = type.getDimension(R.styleable.BaseLinearTextHorizontal_RightMargin, 0);
            topMargin = type.getDimension(R.styleable.BaseLinearTextHorizontal_TopMargin, 0);
            sideMargin = type.getDimension(R.styleable.BaseLinearTextHorizontal_TopMargin, 0);
        } finally {
            type.recycle();
        }
    }

    private void init(Context context, AttributeSet attrs) {

    }

    private void onItemUpdates() {
        LinearLayoutCompat mainView;
        LinearLayoutCompat.LayoutParams params;
        TextView view;
        for (int i = 0; i < list.size(); i++) {
            mainView = (LinearLayoutCompat)
                    LayoutInflater.from(getContext()).inflate(layout, null);

            params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            params.setMargins(0, (int) topMargin, 0, 0);
            mainView.setLayoutParams(params);

            boolean ok = true;
            View lastView = null;
            float viewWidth = 0;
            while (ok && i < list.size()) {
                view = (TextView) LayoutInflater.from(getContext()).inflate(layoutText, null);
                view.setText(list.get(i).getData());
                int finalI = i;

                if(i == 0 && listener != null){
                    this.lastView = view;
                    lastViewTmp = view;
                    listener.onViewClick(view, this.lastView, list.get(finalI));
                }
                view.setOnClickListener(v -> {
                    if (listener != null) {
                        if(this.lastView == null){
                            this.lastView = v;
                        }
                        else{
                            this.lastView = lastViewTmp;
                        }
                        lastViewTmp = v;
                        listener.onViewClick(v, this.lastView, list.get(finalI));
                    }
                });

                params = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, (int) rightMargin, 0);
                view.setLayoutParams(params);

                if (lastView == null) {
                    lastView = view;
                    mainView.addView(view);
                    viewWidth = Dimensions.getTextViewWeight(view);
                    i++;
                } else {
                    float textSize = Dimensions.getTextViewWeight(view);
                    viewWidth = viewWidth + textSize + sideMargin + sideMargin;
                    int screenWidth = Dimensions.getScreenWidth(getContext());

                    if ((screenWidth - viewWidth) > 0) {
                        mainView.addView(view);
                        lastView = view;
                        i++;
                    }else if((screenWidth - (viewWidth - rightMargin)) > 0){
                        params = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                                LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, 0, 0, 0);
                        view.setLayoutParams(params);
                        mainView.addView(view);

                        ok = false;
//                        i++;
                    }
                    else {
                        ok = false;
                        i--;
                    }
                }
            }
            this.addView(mainView);
        }
    }

    private void onItemUpdates1() {
        LinearLayoutCompat mainView;
        LinearLayoutCompat.LayoutParams params;
        TextView view;
        for (int i = 0; i < list.size(); i++) {
            mainView = (LinearLayoutCompat)
                    LayoutInflater.from(getContext()).inflate(layout, null);

            params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            params.setMargins(0, (int) topMargin, 0, 0);
            mainView.setLayoutParams(params);

            boolean ok = true;
            View lastView = null;
            float viewWidth = 0;
            while (ok && i < list.size()) {
                view = (TextView) LayoutInflater.from(getContext()).inflate(layoutText, null);
                view.setText(list.get(i).getData());
                int finalI = i;
                view.setOnClickListener(v -> {
                    if (listener != null) {
                        if(this.lastView == null){
                            this.lastView = v;
                        }
                        else{
                            this.lastView = lastViewTmp;
                        }
                        lastViewTmp = v;
                        listener.onViewClick(v, this.lastView, list.get(finalI));
                    }
                });

                params = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, (int) rightMargin, 0);
                view.setLayoutParams(params);

                if (lastView == null) {
                    lastView = view;
                    mainView.addView(view);
                    viewWidth = Dimensions.getTextViewWeight(view) + rightMargin;
                    i++;
                } else {
                    float textSize = Dimensions.getTextViewWeight(view);
                    viewWidth = viewWidth + textSize + rightMargin;
                    int screenWidth = Dimensions.getScreenWidth(getContext());

                    if (viewWidth < screenWidth && ((screenWidth - viewWidth) > rightMargin)) {
                        mainView.addView(view);
                        lastView = view;
                        i++;
                    } else {
                        ok = false;
                        i--;
                    }
                }
            }
            this.addView(mainView);
        }
    }

    public interface Listener {
        void onViewClick(View currentView, View lastView, BaseModelView model);
    }
}
