package com.supets.pet.jsonview;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.supets.pet.jsoneditlib.R;

public class KeyVauleInputDialog extends Dialog implements
        View.OnClickListener {

    private EditText mToken;

    private Button mCancel;
    private Button mConfirm;

    public KeyVauleInputDialog(Context context) {
        super(context, R.style.xg_dialog);
        init();
        findViews();
        adjustWidth();
    }

    public KeyVauleInputDialog(Context context, int res) {
        super(context, R.style.xg_dialog);
        init();
        findViews();
        adjustWidth();
    }

    private void init() {
        getWindow().setGravity(Gravity.CENTER);
        setContentView(R.layout.json_keyvaule_dialog);
        setCanceledOnTouchOutside(true);
    }

    private void adjustWidth() {
        int screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = screenWidth - dip2px(40f);
        getWindow().setAttributes(params);
    }

    private int dip2px(float dpValue) {
        return (int) (dpValue * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    private void findViews() {
        mToken = (EditText) findViewById(R.id.inputToken);
        mCancel = (Button) findViewById(R.id.button_dialog_negative);
        mConfirm = (Button) findViewById(R.id.button_dialog_positive);

        mCancel.setOnClickListener(this);
        mConfirm.setOnClickListener(this);

    }

    public void setOnClickInputTokenListener(OnClickInputTokenListener listener) {
        this.listener = listener;
    }

    private OnClickInputTokenListener listener;

    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }
        if (v.getId() == R.id.button_dialog_positive) {
            String token = mToken.getText().toString().trim();
            listener.onInputToken(token, this);
        }

        if (v.getId() == R.id.button_dialog_negative) {
            dismiss();
        }

    }

    public interface OnClickInputTokenListener {
        void onInputToken(String name, Dialog dialog);
    }
}
