package com.ducnh.mobileutility.app.logic;

import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;
import com.ducnh.mobileutility.app.Constant;
import com.ducnh.mobileutility.app.model.Action;
import com.ducnh.mobileutility.app.model.PromotionPackage;
import io.realm.Realm;

/**
 * Created by JackMap on 1/31/2015.
 */
public class ActionInvocation {
    private PromotionPackage promotionPackage;
    private Realm realm;
    //

//    public boolean register() {
//        boolean result = false;
//        Action action = realm.where(Action.class).equalTo(Constant.VENDOR, promotionPackage.getVendor(), false)
//                .equalTo(Constant.PACKAGE_NAME, promotionPackage.getPackageName(), false)
//                .equalTo(Constant.ACTION_TYPE, "register", false)
//                .findFirst();
//        if(!action.getVendor().isEmpty()){
//            sendSms(promotionPackage, action);
//            result = true;
//        }
//
//        return result;
//    }


    public boolean sendSms(PromotionPackage promotionPackage, Action action) {
        String targetPhoneNumber = !action.getCustomNumber().isEmpty() ?
                action.getCustomNumber() :
                promotionPackage.getPhoneNumber();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            //smsManager.sendTextMessage(targetPhoneNumber, null, action.getMessageContent(), null, null);
            Log.i(Constant.LOG_TAG, targetPhoneNumber + promotionPackage.toString()+ action.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    ///

    public ActionInvocation() {
    }

    public ActionInvocation(PromotionPackage promotionPackage) {
        this.promotionPackage = promotionPackage;
    }

    public ActionInvocation(PromotionPackage promotionPackage, Realm realm) {
        this.promotionPackage = promotionPackage;
        this.realm = realm;
    }

    public PromotionPackage getPromotionPackage() {
        return promotionPackage;
    }

    public void setPromotionPackage(PromotionPackage promotionPackage) {
        this.promotionPackage = promotionPackage;
    }
}
