package com.supets.pet.jsonview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.HashMap;

public class JsonTagVauleControlView extends LinearLayout {


    public JsonTagVauleControlView(Context context) {
        super(context);
        initView();
    }

    public JsonTagVauleControlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public JsonTagVauleControlView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public JsonTagVauleControlView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }


    private void initView() {
        setOrientation(VERTICAL);
        setPadding(20, 0, 0, 0);
    }

    public HashMap<String, String> getTagVaule() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < getChildCount(); i++) {
            JsonTagVauleView vauleView = (JsonTagVauleView) getChildAt(i);
            String key = vauleView.getKey();
            String vaule = vauleView.getVaule();
            map.put(key, vaule);
        }
        return map;
    }

    public String[] getTagArrayVaule() {
        String[] map = new String[getChildCount()];
        for (int i = 0; i < getChildCount(); i++) {
            JsonTagVauleView vauleView = (JsonTagVauleView) getChildAt(i);
            String vaule = vauleView.getVaule();
            map[i] = vaule;
        }
        return map;
    }

}
