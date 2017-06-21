package com.supets.pet.jsonview;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.supets.pet.jsoneditlib.R;

/**
 * Demo
 *
 * @user lihongjiang
 * @description
 * @date 2017/6/20
 * @updatetime 2017/6/20
 */

public class JsonTagControlView extends LinearLayout implements View.OnClickListener {

    private View addTag;
    private View addTag2;
    private View deleteNode;
    private View addVaule;

    private CheckBox mKeyStatus;

    private TextView key;
    private TextView type;

    private JsonTagType tagType;
    private boolean keyExist = true;

    public JsonTagControlView(Context context) {
        super(context);
        initView();
    }

    public JsonTagControlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public JsonTagControlView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public JsonTagControlView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.json_tagcontrol, this);

        setOrientation(HORIZONTAL);
        setPadding(20, 5, 0, 0);

        addTag = findViewById(R.id.addTag);
        addTag2 = findViewById(R.id.addTag2);
        deleteNode = findViewById(R.id.deleteNode);
        addVaule = findViewById(R.id.addVaule);

        key = (TextView) findViewById(R.id.key);
        type = (TextView) findViewById(R.id.type);

        addTag.setOnClickListener(this);
        addTag2.setOnClickListener(this);
        deleteNode.setOnClickListener(this);
        addVaule.setOnClickListener(this);

        mKeyStatus = (CheckBox) findViewById(R.id.keystatus);
        mKeyStatus.setChecked(keyExist);

        mKeyStatus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                keyExist = mKeyStatus.isChecked();
                setKeyExist();
            }
        });
        findViewById(R.id.EditKey).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox edit = (CheckBox) view;
                key.setEnabled(edit.isChecked());
            }
        });

        final View mm = findViewById(R.id.controllayout);
        findViewById(R.id.controllay).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mm.setVisibility(mm.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });
        findViewById(R.id.collees).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox box = (CheckBox) view;
                ViewGroup viewGroup = (ViewGroup) getParent();
                int v = box.isChecked() ? View.GONE : View.VISIBLE;
                for (int i = 1; i < viewGroup.getChildCount(); i++) {
                    viewGroup.getChildAt(i).setVisibility(v);
                }
            }
        });

        key.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyVauleInputDialog dialog = new KeyVauleInputDialog(getContext());
                dialog.setOnClickInputTokenListener(new KeyVauleInputDialog.OnClickInputTokenListener() {
                    @Override
                    public void onInputToken(String name, Dialog dialog) {
                        key.setText(name);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onClick(View view) {

        if (view == addTag) {
            addObject();
        }
        if (view == addTag2) {
            addArray();
        }

        if (view == addVaule) {
            addVaule();
        }

        if (view == deleteNode) {
            deleteNode();
        }

    }

    private void addObject() {
        ViewGroup parent = (ViewGroup) getParent();
        JsonView jsonView = new JsonView(getContext());
        jsonView.setTagType(JsonTagType.object);
        parent.addView(jsonView);
    }

    private void addArray() {
        ViewGroup parent = (ViewGroup) getParent();
        JsonView jsonView = new JsonView(getContext());
        jsonView.setTagType(JsonTagType.array);
        parent.addView(jsonView);
    }


    private void deleteNode() {
        ViewGroup parent = (ViewGroup) getParent();
        ViewGroup parent2 = (ViewGroup) parent.getParent();
        parent2.removeView(parent);
    }

    private void addVaule() {

        JsonTagVauleControlView controlView =
                (JsonTagVauleControlView) ((ViewGroup) getParent()).findViewById(R.id.jsonVauleControl);

        if (controlView != null) {
            controlView.addView(new JsonTagVauleView(getContext()));
        }
    }

    public JsonTagType getTagType() {
        return tagType;
    }


    public void setTagType(JsonTagType tagType,boolean keyExist) {
        this.tagType = tagType;
        this.keyExist=keyExist;

        switch (tagType) {
            case array:
                type.setText("[]");
                break;
            case object:
                type.setText("{}");
                break;
        }

        mKeyStatus.setChecked(keyExist);

        setKeyExist();
    }

    private void setKeyExist() {
        if (keyExist) {
            key.setVisibility(View.VISIBLE);
            findViewById(R.id.fuhao).setVisibility(View.VISIBLE);
        } else {
            key.setVisibility(View.GONE);
            findViewById(R.id.fuhao).setVisibility(View.GONE);
        }
    }

    public String getTagKey() {
        if (keyExist) {
            return "\"" + key.getText().toString() + "\"" + ":";
        }
        return "";
    }

    public boolean isExistKey() {
        return keyExist;
    }

    public enum JsonTagType {
        array,
        object
    }

}
