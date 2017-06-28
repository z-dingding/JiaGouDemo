package com.bc.demo;

import android.app.Activity;
import java.util.Stack;


public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {

    }

    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        return instance;
    }

    public Activity getActivity(Class<?> clazz) {
        if (activityStack != null) {
            for (Activity activity : activityStack
                    ) {
                if (activity.getClass().equals(clazz)) {
                    return activity;
                }
            }
        }
        return null;
    }

    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    public Activity currentActicity() {
        return activityStack.lastElement();
    }

    public void finishActivity() {
        finishActivity(currentActicity());
    }

    public void finishActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                finishActivity(activityStack.get(i));
            }
        }
        activityStack.clear();
    }
}
