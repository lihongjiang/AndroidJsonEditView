package com.supets.pet.jsonview;

import java.util.HashMap;

public class JSONViewHelper {

    public static String parse(JsonView rootView) {

        String vaule = null;

        if (rootView.isArray()) {
            vaule = rootView.getTagKey() + "[";
            if (rootView.getTagArrayVaule().length > 0) {
                String[] vaules = rootView.getTagArrayVaule();
                for (int i = 0; i < vaules.length; i++) {
                    vaule += vaules[i] + (i != (vaules.length - 1) ? "," : "");
                }

            } else {
                if (rootView.getChildCount() > 2) {
                    for (int i = 2; i < rootView.getChildCount(); i++) {
                        JsonView temp = (JsonView) rootView.getChildAt(i);
                        vaule += parse(temp) + (i != (rootView.getChildCount() - 1) ? "," : "");
                    }
                }
            }

            vaule += "]";
        } else {
            vaule = rootView.getTagKey() + "{";
            HashMap<String, String> vaules = rootView.getTagVaule();

            int a = 0;
            int b = vaules.keySet().size();
            for (String key : vaules.keySet()) {
                vaule += key+ ":" + vaules.get(key)+ (a == b - 1 ? "" : ",");
                a++;
            }

            if (rootView.getChildCount() > 2) {
                for (int i = 2; i < rootView.getChildCount(); i++) {
                    JsonView temp = (JsonView) rootView.getChildAt(i);
                    vaule +=( b==0&&i==2?"":",") + parse(temp);
                }
            }

            vaule += "}";
        }

        return vaule;
    }


}
