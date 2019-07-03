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
}
