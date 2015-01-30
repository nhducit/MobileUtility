package com.ducnh.mobileutility.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.ducnh.mobileutility.app.model.Action;
import com.ducnh.mobileutility.app.model.PromotionPackage;
import io.realm.Realm;
import io.realm.RealmResults;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {

    String VENDOR = "vendor";
    String PACKAGE_NAME = "packageName";
    String ACTION_TYPE = "actionType";
    String SMS_FEE = "smsFee";
    String PACKAGE_FEE = "packageFee";
    String CUSTOM_NUMBER = "customNumber";
    String MESSAGE_CONTENT = "messageContent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialDatabase();
    }


    public void initialDatabase() {
        initialActionData();
        initialPackageData();
        packageActionLinker();
        printAllPackage();
    }

    public void initialPackageData() {
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();
        try {
            InputStream inputStream = getAssets().open("PromotionPackageData.json");
            realm.createAllFromJson(PromotionPackage.class, inputStream);
            realm.commitTransaction();
        } catch (IOException e) {
            e.printStackTrace();
            realm.cancelTransaction();
        }
    }

    public void initialActionData() {
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();
        try {
            InputStream inputStream = getAssets().open("ActionData.json");
            realm.createAllFromJson(Action.class, inputStream);
            realm.commitTransaction();
        } catch (IOException e) {
            e.printStackTrace();
            realm.cancelTransaction();
        }
    }

    public void packageActionLinker() {
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();
        RealmResults<PromotionPackage> promotionPackages = realm.where(PromotionPackage.class).findAll();
        for (PromotionPackage promotionPackage : promotionPackages) {
            RealmResults<Action> actions = realm.where(Action.class)
                    .equalTo(VENDOR, promotionPackage.getVendor())
                    .equalTo(PACKAGE_NAME, promotionPackage.getVendor())
                    .findAll();

            promotionPackage.getActions().addAll(actions);
        }
        realm.commitTransaction();
    }

    public void printAllPackage() {
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();
        RealmResults<PromotionPackage> promotionPackages = realm.where(PromotionPackage.class).findAll();

        for (PromotionPackage promotionPackage : promotionPackages) {
            System.out.println(promotionPackage.toString());
        }
        realm.commitTransaction();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
