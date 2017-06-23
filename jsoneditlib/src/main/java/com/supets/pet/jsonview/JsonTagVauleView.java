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

public class JsonTagVauleView extends LinearLayout implements View.OnClickListener {

    private TextView key;
    private TextView vaule;

    private View deletevaule;

    public JsonTagVauleView(Context context) {
        super(context);
        initView();
    }

    public JsonTagVauleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public JsonTagVauleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public JsonTagVauleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.json_vaulecontrl, this);

        setOrientation(HORIZONTAL);
        setPadding(20, 5, 0, 0);

        key = (TextView) findViewById(R.id.key);
        vaule = (TextView) findViewById(R.id.vaule);
        deletevaule = findViewById(R.id.deletevaule);

        final CheckBox editvaule = (CheckBox) findViewById(R.id.editvaule);
        editvaule.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                key.setEnabled(editvaule.isChecked());
                vaule.setEnabled(editvaule.isChecked());
            }
        });
        deletevaule.setOnClickListener(this);

        final View mm = findViewById(R.id.controllayout);
        findViewById(R.id.controllay).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mm.setVisibility(mm.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
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

        vaule.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyVauleInputDialog dialog = new KeyVauleInputDialog(getContext());
                dialog.setOnClickInputTokenListener(new KeyVauleInputDialog.OnClickInputTokenListener() {
                    @Override
                    public void onInputToken(String name, Dialog dialog) {
                        vaule.setText(name);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == deletevaule) {
            ViewGroup viewGroup = (ViewGroup) getParent();
            viewGroup.removeView(this);
        }
    }

    public String getKey() {
        return "\"" + key.getText().toString() + "\"";
    }

    public String getVaule() {
        String result = vaule.getText().toString();
        try {
            Double.valueOf(result);
            return result;
        } catch (Exception e) {
            return "\"" + result + "\"";
        }
    }

    public void addTagVaule(String key1, String vaule1) {
        key.setText(key1);
        vaule.setText(vaule1);
    }

    public JsonTagVauleView setArrayHide() {
        key.setVisibility(View.GONE);
        findViewById(R.id.dian).setVisibility(View.GONE);
        return this;
    }

}
