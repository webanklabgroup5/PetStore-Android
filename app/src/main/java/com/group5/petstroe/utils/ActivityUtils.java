package com.group5.petstroe.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

public class ActivityUtils {

    private static List<Activity> activityList = new LinkedList<Activity>();

    public static boolean addActivity(Activity activity) {
        return activityList.add(activity);
    }

    public static boolean removeActivity(Activity activity) {
        return activityList.remove(activity);
    }

    public static void finishAllActivity() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activityList.clear();
    }

    public static final int CODE_MAIN_ACTIVITY = 0;
    public static final int CODE_SIGN_IN_ACTIVITY = 1;
    public static final int CODE_SIGN_UP_ACTIVITY = 2;
    public static final int CODE_PET_ACTIVITY = 3;
    public static final int CODE_BALANCE_ACTIVITY = 4;
    public static final int CODE_APPLY_BALANCE_ACTIVITY = 5;
    public static final int CODE_MY_PET_ACTIVITY = 6;
    public static final int CODE_CREATE_PET_ACTIVITY = 7;
    public static final int CODE_PET_INFO_ACTIVITY = 8;
    public static final int CODE_CHANGE_PET_STATUS_ACTIVITY = 9;
    public static final int CODE_ORDER_ACTIVITY = 10;
    public static final int CODE_ORDER_INFO_ACTIVITY = 11;
    public static final int CODE_ARBITRATION_ACTIVITY = 12;
}
