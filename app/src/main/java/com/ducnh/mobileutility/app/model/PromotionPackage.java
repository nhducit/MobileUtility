package com.ducnh.mobileutility.app.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by nhuuduc on 1/30/2015.
 */
public class PromotionPackage extends RealmObject {
    private String vendor;
    private String packageName;
    private String description;
    private String phoneNumber;
    private int packageFee;
    private RealmList<Action> actions;


    //<editor-fold desc="getter and setter">

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPackageFee() {
        return packageFee;
    }

    public void setPackageFee(int packageFee) {
        this.packageFee = packageFee;
    }

    public RealmList<Action> getActions() {
        return actions;
    }

    public void setActions(RealmList<Action> actions) {
        this.actions = actions;
    }

//</editor-fold>
}