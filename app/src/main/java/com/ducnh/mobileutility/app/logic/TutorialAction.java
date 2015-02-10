package com.ducnh.mobileutility.app.logic;

import com.ducnh.mobileutility.app.Constant;
import com.ducnh.mobileutility.app.model.Action;
import com.ducnh.mobileutility.app.model.PromotionPackage;
import io.realm.Realm;

/**
 * Created by JackMap on 2/8/2015.
 */
public class TutorialAction implements ActionPrototype {
    private PromotionPackage promotionPackage;



    @Override
    public boolean doAction() {
        return ActionUtility.doAction(promotionPackage, Constant.TUTORIAL);
    }

    //<editor-fold desc="Constructor">

    public TutorialAction(PromotionPackage promotionPackage) {
        this.promotionPackage = promotionPackage;

    }

    public TutorialAction() {
    }
    //</editor-fold>


    //<editor-fold desc="getter and setter">
    public PromotionPackage getPromotionPackage() {
        return promotionPackage;
    }

    public void setPromotionPackage(PromotionPackage promotionPackage) {
        this.promotionPackage = promotionPackage;
    }


}
