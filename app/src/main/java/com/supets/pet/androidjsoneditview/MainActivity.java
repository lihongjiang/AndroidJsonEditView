package com.supets.pet.androidjsoneditview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.supets.pet.jsonview.JSONEditView;
import com.supets.pet.jsonview.JSONFormatUtil;

public class MainActivity extends AppCompatActivity implements JSONEditView.JSONEditViewListener {

    private EditText jsonresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final JSONEditView jsonEditView = (JSONEditView) findViewById(R.id.jsoneditView);
        jsonEditView.setJSONEditViewListener(this);

        jsonresult = (EditText) findViewById(R.id.jsonresult);

        findViewById(R.id.changejson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jsonEditView.formatJsonInput(jsonresult.getText().toString());

            }
        });

        findViewById(R.id.jsoncheck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonEditView.doFormatOutput();
            }
        });

        findViewById(R.id.rootObject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonEditView.insertRootObject();
            }
        });

        findViewById(R.id.rootArray).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonEditView.insertRootArray();
            }
        });

        findViewById(R.id.jsoncopy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonEditView.doJsonCopy();
            }
        });

    }

    @Override
    public void formatOutput(String json, boolean isJson) {
        if (isJson) {
            jsonresult.setText(JSONFormatUtil.log(json));
            jsonresult.setTextColor(Color.argb(255, 0, 0, 0));
        } else {
            jsonresult.setText(json);
            jsonresult.setTextColor(Color.argb(255, 255, 0, 0));
        }
    }
}
