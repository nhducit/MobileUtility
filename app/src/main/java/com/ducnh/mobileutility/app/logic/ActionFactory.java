package com.ducnh.mobileutility.app.logic;

import com.ducnh.mobileutility.app.Constant;
import com.ducnh.mobileutility.app.model.PromotionPackage;

/**
 * Created by JackMap on 2/8/2015.
 */
public class ActionFactory {
    
    public ActionPrototype getActionPrototype(PromotionPackage promotionPackage,String actionType){
        if(actionType == null){
            return null;
        }
        if(actionType.equalsIgnoreCase(Constant.REGISTER)){
            return new RegisterAction(promotionPackage);
        }else if(actionType.equalsIgnoreCase(Constant.CANCEL)){
            return new CancelAction(promotionPackage);
        }else if(actionType.equalsIgnoreCase(Constant.CHECK)){
            return new CheckAction(promotionPackage);
        }else if(actionType.equalsIgnoreCase(Constant.TUTORIAL)){
            return new TutorialAction(promotionPackage);
        }
        return null;
    }
}
