package com.supets.pet.androidjsoneditview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.supets.pet.jsonview.JSONEditView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONEditView  jsonEditView= (JSONEditView) findViewById(R.id.jsoneditView);

        String  testjson="{\"key\":4,\"name\":[1,2,3]}";

        jsonEditView.formatJson(testjson);


    }
}
