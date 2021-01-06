package com.common.utils;


import java.util.List;
import java.util.Map;

public class CheckUtils {

    public static boolean listIsEmpty(List list) {
        if (list != null && !list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean listIsNotEmpty(List list) {
        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean mapIsEmpty(Map map) {
        if (map != null && !map.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean mapIsNotEmpty(Map map) {
        if (map != null && !map.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
