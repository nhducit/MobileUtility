package com.ducnh.mobileutility.app.logic;

import android.app.Application;
import android.content.Context;

/**
 * Created by JackMap on 2/8/2015.
 */
public class MobileUtilityApplication extends Application {
    private static Context context;

    @Override
    public void onCreate(){
        super.onCreate();
        MobileUtilityApplication.context = getApplicationContext();
        
    }
    
    public static Context getContext() {
        return context;
    }

}
