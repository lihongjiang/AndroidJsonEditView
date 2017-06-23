package com.supets.pet.jsonview;


import android.content.Context;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONException;

import java.util.Iterator;
import java.util.Map;

public class JSONViewHelper2 {

    public static JsonView parse(String testjson, ViewGroup viewGroup) throws JSONException {
        JsonElement map = new Gson().fromJson(testjson, JsonElement.class);
        JsonElement rootJsonElement = new Gson().toJsonTree(map);
        return test(viewGroup.getContext(), rootJsonElement, null);
    }

    public static JsonView test(Context context, JsonElement obj, String keyno) {

        JsonView jsonView;

        if (obj.isJsonObject()) {

            if (keyno == null) {
                jsonView = new JsonView(context)
                        .setRootTagType(JsonTagControlView.JsonTagType.object);
            } else {
                jsonView = new JsonView(context)
                        .setTagType(JsonTagControlView.JsonTagType.object);
            }

            jsonView.setKey(keyno);

            Iterator<Map.Entry<String, JsonElement>> keys =
                    obj.getAsJsonObject().entrySet().iterator();
            while (keys.hasNext()) {
                Map.Entry<String, JsonElement> type = keys.next();
                String key = type.getKey();
                JsonElement vaule = type.getValue();
                //shuzu
                if (vaule.isJsonArray()) {
                    //go
                    JsonView temp = test(context, vaule, key);
                    jsonView.addView(temp);
                } else if (vaule.isJsonObject()) {
                    //go
                    JsonView temp = test(context, vaule, key);
                    jsonView.addView(temp);
                } else {
                    //add vaule
                    jsonView.addTagVaule(key, vaule.getAsString());
                }
            }

            return jsonView;

        } else if (obj.isJsonArray()) {


            if (keyno == null) {
                jsonView = new JsonView(context)
                        .setRootTagType(JsonTagControlView.JsonTagType.array);
            } else {
                jsonView = new JsonView(context)
                        .setTagType(JsonTagControlView.JsonTagType.array);
            }

            jsonView.setKey(keyno);

            if (obj.getAsJsonArray().size() > 0) {

                for (int i = 0; i < obj.getAsJsonArray().size(); i++) {
                    JsonElement tag = obj.getAsJsonArray().get(i);
                    if (tag.isJsonObject()) {
                        //go
                        JsonView temp = test(context, tag, null);
                        jsonView.addView(temp);
                    } else {
                        jsonView.addTagVaule(null, tag.getAsString());
                    }
                }
            }

            return jsonView;

        }

        return null;
    }

}
