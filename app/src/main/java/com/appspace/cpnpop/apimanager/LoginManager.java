package com.appspace.cpnpop.apimanager;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by siwaweswongcharoen on 8/8/2015 AD.
 */
public class LoginManager {
    public static RequestParams getParamsByFacebookGraph(JSONObject object, String facebookToken) throws JSONException {
        RequestParams params = new RequestParams();

        String newBirthDay;
        String birthDay;
        String[] temp;
        try {
            birthDay = object.getString("birthday");
            temp = birthDay.split("/");
            newBirthDay = temp[2] + "-" + temp[0] + "-" + temp[1];
        } catch (JSONException | NullPointerException e) {
            newBirthDay = "0000-00-00";
        }

        params.add("firstname", object.getString("first_name"));
        params.add("lastname", object.getString("last_name"));
        params.add("email", object.getString("email"));
        params.add("facebook_id", object.getString("id"));
        params.add("birthday", newBirthDay);
        params.add("gender", object.getString("gender"));
        params.add("facebook_token", facebookToken);


        return params;
    }
}
