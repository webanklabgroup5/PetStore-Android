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
}
