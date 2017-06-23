package com.supets.pet.jsonview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class JSONFormatUtil {

    public static boolean isJson(String message) {
        try {
            new JSONObject(message);
        } catch (Exception e) {
            try {
                new JSONArray(message);
            } catch (JSONException e1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isJsonObject(String message) {
        try {
            new JSONObject(message);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isJsonArray(String message) {
        try {
            new JSONArray(message);
        } catch (JSONException e1) {
            return false;
        }
        return true;
    }

    public static String log(String message) {
        try {
            if (isJson(message)) {

                StringBuilder sb = new StringBuilder();

                String jsonStr = JSONFormatUtil.format(message);
                long totalpage = jsonStr.length() % 2048 == 0 ? 0 : 1 + jsonStr.length() / 2048;
                int start = 0;
                for (int i = 0; i < totalpage; i++) {
                    int dds = Math.min((i + 1) * 2048, jsonStr.length());
                    int last = jsonStr.indexOf(",", dds);
                    sb.append("\n" + jsonStr.substring(start, last == -1 ? dds : (last + 1)));
                    start = last == -1 ? dds : (last + 1);
                }
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public static String format(String jsonStr) {
        int level = 0;
        StringBuffer jsonForMatStr = new StringBuffer();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c + "\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }

        return jsonForMatStr.toString();

    }

    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

}