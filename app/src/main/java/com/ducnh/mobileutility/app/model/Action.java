package com.ducnh.mobileutility.app.model;


import io.realm.RealmObject;

/**
 * Created by nhuuduc on 1/30/2015.
 */
public class Action extends RealmObject {
    private String vendor;
    private String packageName;
    private String actionType;
    private int smsFee;
    private String customNumber;
    private String messageContent;

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

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public int getSmsFee() {
        return smsFee;
    }

    public void setSmsFee(int smsFee) {
        this.smsFee = smsFee;
    }

    public String getCustomNumber() {
        return customNumber;
    }

    public void setCustomNumber(String customNumber) {
        this.customNumber = customNumber;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    //</editor-fold>
}