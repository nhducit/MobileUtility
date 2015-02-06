package com.ducnh.mobileutility.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.ducnh.mobileutility.app.logic.ActionInvocation;
import com.ducnh.mobileutility.app.model.Action;
import com.ducnh.mobileutility.app.model.PromotionPackage;
import io.realm.Realm;
import io.realm.RealmResults;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {
    
    private String LOG_TAG = "MobileUtility";
    
    
    private String VENDOR = "vendor";
    private String PACKAGE_NAME = "packageName";
    private String ACTION_TYPE = "actionType";
    private String SMS_FEE = "smsFee";
    private String PACKAGE_FEE = "packageFee";
    private String CUSTOM_NUMBER = "customNumber";
    private String MESSAGE_CONTENT = "messageContent";

    //

    private Realm realm;

    //
    Button testButton;
    TextView textView;
    
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
            Log.i(LOG_TAG, "Can not get instance of Realm, need migration");
            e.printStackTrace();
        }
        initialDatabase();
        testButton = (Button) findViewById(R.id.testButton);
        textView = (TextView) findViewById(R.id.packageTextVIew);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                //printAllPackage(textView);
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
            ActionInvocation actionInvocation = new ActionInvocation(promotionPackage, realm);
            boolean actionResult = true; //actionInvocation.register();
            if(actionResult){
                Toast.makeText(getApplicationContext(), "Register successfully", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Register Failed", Toast.LENGTH_LONG).show();
            }
            Log.i("MU---" , promotionPackage.toString());
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
