package com.supets.pet.jsonview;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.supets.pet.jsoneditlib.R;

public class JSONEditView extends LinearLayout {


    private ViewGroup mJsonRootView;
    private JsonView rootView;
    private JSONEditViewListener mJsonEditViewListener;

    public JSONEditView(Context context) {
        super(context);
        initView();
    }

    public JSONEditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public JSONEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public JSONEditView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.json_editview, this);
        setOrientation(VERTICAL);
        mJsonRootView = (ViewGroup) findViewById(R.id.jsoncontainer);
    }

    public void doJsonCopy() {
        if (rootView != null) {
            String json = JSONViewHelper.parse(rootView);
            if (JSONFormatUtil.isJson(json)) {
                ClipMananger.copy(json, getContext());
                Toast.makeText(getContext(), "Copy Success", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void insertRootArray() {
        mJsonRootView.removeAllViews();
        rootView = new JsonView(getContext())
                .setRootTagType(JsonTagControlView.JsonTagType.array);
        mJsonRootView.addView(rootView);
    }

    public void insertRootObject() {
        mJsonRootView.removeAllViews();
        rootView = new JsonView(getContext()).setRootTagType(JsonTagControlView.JsonTagType.object);
        mJsonRootView.addView(rootView);
    }

    public void doFormatOutput() {
        if (rootView != null) {
            if (mJsonEditViewListener != null) {
                String json = JSONViewHelper.parse(rootView);
                mJsonEditViewListener.formatOutput(json, JSONFormatUtil.isJson(json));
            }
        }
    }

    public void formatJsonInput(String json) {
        if (json != null) {
            try {
                JsonView jsonView = JSONViewHelper2.parse(json, mJsonRootView);
                mJsonRootView.removeAllViews();
                rootView = jsonView;
                mJsonRootView.addView(jsonView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setJSONEditViewListener(JSONEditViewListener listener) {
        this.mJsonEditViewListener = listener;
    }

    public interface JSONEditViewListener {
        void formatOutput(String json, boolean isJson);
    }
}
