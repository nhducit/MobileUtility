package com.ducnh.mobileutility.app.logic;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;
import com.ducnh.mobileutility.app.Constant;
import com.ducnh.mobileutility.app.model.Action;
import com.ducnh.mobileutility.app.model.PromotionPackage;
import io.realm.Realm;

/**
 * Created by JackMap on 2/8/2015.
 */
public class ActionUtility {

    public static boolean doAction(PromotionPackage promotionPackage, String actionName){
        boolean result = false;
        //query appropriate action
        Realm realm = Realm.getInstance(MobileUtilityApplication.getContext());
        Action action = realm.where(Action.class).equalTo(Constant.VENDOR, promotionPackage.getVendor(), false)
                .equalTo(Constant.PACKAGE_NAME, promotionPackage.getPackageName(), false)
                .equalTo(Constant.ACTION_TYPE, actionName, false)
                .findFirst();
        //check action is exists
        if(!action.getVendor().isEmpty()){
            //send sms
            sendSms(promotionPackage, action);
            result = true;
        }else{
            Log.e(Constant.LOG_TAG, "Action " + actionName + " doesn't exist in " + promotionPackage.getPackageName());
        }
        return result;
    }


    public static boolean sendSms(PromotionPackage promotionPackage, Action action) {
        String targetPhoneNumber = !action.getCustomNumber().isEmpty() ?
                action.getCustomNumber() :
                promotionPackage.getPhoneNumber();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(targetPhoneNumber, null, action.getMessageContent(), null, null);
            Toast.makeText(MobileUtilityApplication.getContext(), action.getActionType() + " " + promotionPackage
                    .getPackageName() + " " + "successful", Toast.LENGTH_LONG)
                    .show();
            //Log.i(Constant.LOG_TAG, targetPhoneNumber + action.getMessageContent() + promotionPackage.toString()+
            //        action.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
