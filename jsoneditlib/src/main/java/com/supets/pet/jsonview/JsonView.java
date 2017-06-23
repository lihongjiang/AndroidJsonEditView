package com.supets.pet.jsonview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.supets.pet.jsoneditlib.R;

import java.util.HashMap;

public class JsonView extends LinearLayout {

    public JsonView(Context context) {
        super(context);
        initView();
    }

    public JsonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public JsonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public JsonView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    JsonTagVauleControlView jsonTagVauleControlView;
    JsonTagControlView jsonTagControl;

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.json_view, this);
        setOrientation(VERTICAL);
        setPadding(20, 0, 0, 0);

        jsonTagVauleControlView = (JsonTagVauleControlView) findViewById(R.id.jsonVauleControl);
        jsonTagControl = (JsonTagControlView) findViewById(R.id.jsonTagControl);
    }


    public JsonView setTagType(JsonTagControlView.JsonTagType type) {
        jsonTagControl.setTagType(type,true);
        return this;
    }

    public JsonView setRootTagType(JsonTagControlView.JsonTagType type) {
        jsonTagControl.setTagType(type,false);
        return this;
    }

    public HashMap<String, String> getTagVaule() {
        return jsonTagVauleControlView.getTagVaule();
    }


    public boolean isTagKey() {
        return jsonTagControl.isExistKey();
    }

    public String getTagKey() {
        return jsonTagControl.getTagKey();
    }

    public String[] getTagArrayVaule() {
        return jsonTagVauleControlView.getTagArrayVaule();
    }


    public boolean isArray() {
        return JsonTagControlView.JsonTagType.array == jsonTagControl.getTagType();
    }

    public void addTagVaule(String key, String vaule) {
        jsonTagControl.addTagVaule(key,vaule);
    }

    public void setKey(String key) {
        this.jsonTagControl.setTagKey(key);
    }
}
