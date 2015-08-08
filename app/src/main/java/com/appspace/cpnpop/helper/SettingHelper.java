package com.appspace.cpnpop.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.appspace.cpnpop.R;

/**
 * Created by siwaweswongcharoen on 8/7/2015 AD.
 */
public class SettingHelper {
    static final String tag = "setting";

    // SharedPreferences
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public SettingHelper(Context context) {
        sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public boolean getFacebookLoginStatus() {
        return sharedPref.getBoolean(Constant.setting_facebook_login_status_boolean,false);
    }

    public void setFacebookLoginStatus(boolean val) {
        editor.putBoolean(Constant.setting_facebook_login_status_boolean,val);
        editor.commit();
        Log.d(tag, "setFacebookLoginStatus:" + val);
    }

    public String getFacebookToken() {
        return sharedPref.getString(Constant.facebook_token,"");
    }

    public void setFacebookToken(String val) {
        editor.putString(Constant.facebook_token,val);
        editor.commit();
        Log.d(tag, "setFacebookToken:"+val);
    }

    public String getUserID() {
        return sharedPref.getString(Constant.userID,"");
    }

    public void setUserID(String val) {
        editor.putString(Constant.userID,val);
        editor.commit();
        Log.d(tag, "setUserID:"+val);
    }
}
