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
import android.widget.TextView;
import android.widget.Toast;

import com.supets.pet.jsoneditlib.R;

/**
 * Demo
 *
 * @user lihongjiang
 * @description
 * @date 2017/6/21
 * @updatetime 2017/6/21
 */

public class JSONEditView extends LinearLayout {


    private ViewGroup mJsonRootView;
    private JsonView rootView;

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

        final TextView jsonresult = (TextView) findViewById(R.id.jsonresult);
        findViewById(R.id.jsoncheck).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rootView != null) {
                    String json = JSONViewHelper.parse(rootView);
                    if (JSONFormatUtil.isJson(json)) {
                        jsonresult.setText(JSONFormatUtil.log(json));
                        jsonresult.setTextColor(Color.argb(255, 0, 0, 0));
                    } else {
                        jsonresult.setText(json);
                        jsonresult.setTextColor(Color.argb(255, 255, 0, 0));
                    }
                }
            }
        });

        findViewById(R.id.rootObject).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mJsonRootView.removeAllViews();
                rootView = new JsonView(getContext())
                        .setRootTagType(JsonTagControlView.JsonTagType.object);
                mJsonRootView.addView(rootView);
            }
        });

        findViewById(R.id.rootArray).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mJsonRootView.removeAllViews();
                rootView = new JsonView(getContext())
                        .setRootTagType(JsonTagControlView.JsonTagType.array);
                mJsonRootView.addView(rootView);
            }
        });

        findViewById(R.id.jsoncopy).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rootView != null) {
                    String json = JSONViewHelper.parse(rootView);
                    if (JSONFormatUtil.isJson(json)) {
                        ClipMananger.copy(json, getContext());
                        Toast.makeText(getContext(), "Copy Success", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
