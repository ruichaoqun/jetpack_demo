package com.example.myapplication.utils;

public class CommonUtils {
    public static int getScrollAngle(float f, float f2, float f3, float f4) {
        float abs = Math.abs(f - f3);
        float abs2 = Math.abs(f2 - f4);
        return Math.round((float) ((Math.asin(((double) abs2) / Math.sqrt(((abs * abs) + (abs2 * abs2)))) / Math.PI) * 180.0d));
    }
}
