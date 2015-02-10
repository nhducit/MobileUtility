package com.ducnh.mobileutility.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ducnh.mobileutility.app.logic.ActionFactory;
import com.ducnh.mobileutility.app.logic.ActionPrototype;
import com.ducnh.mobileutility.app.model.Action;
import com.ducnh.mobileutility.app.model.PromotionPackage;
import io.realm.Realm;
import io.realm.RealmResults;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {


    private String VENDOR = "vendor";
    private String PACKAGE_NAME = "packageName";
    private String ACTION_TYPE = "actionType";
    private String SMS_FEE = "smsFee";
    private String PACKAGE_FEE = "packageFee";
    private String CUSTOM_NUMBER = "customNumber";
    private String MESSAGE_CONTENT = "messageContent";
    private ActionFactory actionFactory = new ActionFactory();
    //

    private Realm realm;

    //
    Button registerButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //String dbPath = copyBundledRealmFile(this.getResources().openRawResource(R.))
        try {
            //TODO: remove deleteRealmFile when publish this application
            Realm.deleteRealmFile(this);
            realm = Realm.getInstance(this);
        } catch (Exception e) {
            Log.i(Constant.LOG_TAG, "Can not get instance of Realm, need migration");
            e.printStackTrace();
        }
        initialDatabase();
        registerButton = (Button) findViewById(R.id.registerButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PromotionPackage promotionPackage = realm.where(PromotionPackage.class).equalTo(Constant
                        .PACKAGE_NAME, "vt100", false).findFirst();
                ActionPrototype registerVt100 = actionFactory.getActionPrototype(promotionPackage, Constant.REGISTER);
                registerVt100.doAction();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PromotionPackage promotionPackage = realm.where(PromotionPackage.class).equalTo(Constant
                        .PACKAGE_NAME, "vt100", false).findFirst();
                ActionPrototype cancelVt100 = actionFactory.getActionPrototype(promotionPackage, Constant.CANCEL);
                cancelVt100.doAction();
            }
        });
    }


    public void initialDatabase() {
        initialActionData();
        initialPackageData();
        //packageActionLinker();
    }

    public void initialPackageData() {
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

//    public void packageActionLinker() {
//        realm.beginTransaction();
//        RealmResults<PromotionPackage> promotionPackages = realm.where(PromotionPackage.class).findAll();
//
//
//        for (int i = 0; i < promotionPackages.size(); i++) {
//            PromotionPackage promotionPackage = promotionPackages.get(i);
//            RealmResults<Action> actions = realm.where(Action.class)
//                    .equalTo(VENDOR, promotionPackage.getVendor(), false)
//                    .equalTo(PACKAGE_NAME, promotionPackage.getPackageName(), false)
//                    .findAll();
//
//            for (int j = 0; j < actions.size(); j++) {
//                Action action = actions.get(j);
//                promotionPackage.getActions().add(action);
//            }
//            //promotionPackage.getActions().addAll(actions);
//
//        }
//
//        realm.commitTransaction();
//    }

    public void printAllPackage(TextView textView) {
        RealmResults<PromotionPackage> promotionPackages = realm.where(PromotionPackage.class).findAll();
        for (PromotionPackage promotionPackage : promotionPackages) {
            textView.setText(textView.getText() + promotionPackage.toString());
            //ActionInvocation actionInvocation = new ActionInvocation(promotionPackage, realm);
            //boolean actionResult = actionInvocation.register();
//            if(actionResult){
//                Toast.makeText(getApplicationContext(), "Register successfully!", Toast.LENGTH_LONG).show();
//            }else{
//                Toast.makeText(getApplicationContext(), "Register Failed!", Toast.LENGTH_LONG).show();
//            }
            Log.i("MU---", promotionPackage.toString());
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
