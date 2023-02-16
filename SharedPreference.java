package akaalwebsoft.com.gurudwara.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPreference {

    public static final String AccessToken = "AccessToken";
    public static final String AUTH = "Auth";
    public static final String DEVICEID = "Deviceid";
    public static final String PHONENUMBER = "Phonenumber";
    public static final String GURUDWARANAME = "gurudwaraname";
    public static final String GURUDWARAMASTERID = "gurudwaramasterid";
    public static final String USERMASTERID = "usermasterid";
    public static final String USERTYPE = "usertype";
    public static final String GURUDWARA_ADDRESS = "gurudwara_address";
    public static final String MOTIVE_CATAGORY = "motive_catagory";
    public static final String RUPPESS = "Ruppess";
    public static final String DATE = "date";
    public static SharedPreference pref = null;
    private final Context context;
    SharedPreferences.Editor prefsEditor;
    SharedPreferences sharedpreferences;


    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return sharedpreferences.getString(key, "");
    }


    public void clearPreferences() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();
    }

    public <T> T getModel(String key, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(sharedpreferences.getString(key, ""), type);
    }


    public SharedPreference(Context context) {
        this.context = context;
        sharedpreferences = context.getSharedPreferences("Gurudwara", Context.MODE_PRIVATE);
        prefsEditor = sharedpreferences.edit();
    }

    public void saveModel(String key, Object modelClass) {
        Gson gson = new Gson();
        prefsEditor.putString(key, gson.toJson(modelClass));
        prefsEditor.apply();
    }

    public void login(Context context, boolean connected) {
        sharedpreferences = context.getSharedPreferences("Gurudwara", 0);
        prefsEditor = sharedpreferences.edit();
        prefsEditor.putBoolean("connected", connected);
        prefsEditor.commit();
    }

    public boolean isLogin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Gurudwara", 0);
        return sharedPreferences.getBoolean("connected", false);
    }

    public static SharedPreference getInstance(Context context) {
        if (pref == null) {
            pref = new SharedPreference(context);
        }
        return pref;
    }

    public void setAccessToken(String accessToken) {
        sharedpreferences.edit().putString(AccessToken, accessToken).commit();
    }

    public String getAccessToken() {
        return sharedpreferences.getString(AccessToken, "");
    }

    public void setAuth(String auth) {
        sharedpreferences.edit().putString(AUTH, auth).commit();
    }

    public String getAuth() {
        return sharedpreferences.getString(AUTH, "");
    }

    public void setDeviceid(String deviceid) {
        sharedpreferences.edit().putString(DEVICEID, deviceid).commit();
    }

    public String getDeviceid() {
        return sharedpreferences.getString(DEVICEID, "");
    }

    public void setGurudwaraname(String gurudwaraname) {
        sharedpreferences.edit().putString(GURUDWARANAME, gurudwaraname).commit();
    }

    public String getGurudwaraname() {
        return sharedpreferences.getString(GURUDWARANAME, "");
    }

    public void setGurudwaraAddress(String gurudwaraAddress) {
        sharedpreferences.edit().putString(GURUDWARA_ADDRESS, gurudwaraAddress).commit();
    }

    public String getGurudwaraAddress() {
        return sharedpreferences.getString(GURUDWARA_ADDRESS, "");
    }

    public void setMotiveCatagory(String motiveCatagory) {
        sharedpreferences.edit().putString(MOTIVE_CATAGORY, motiveCatagory).commit();
    }

    public String getMotiveCatagory() {
        return sharedpreferences.getString(MOTIVE_CATAGORY, "");

    } public void setRuppess(String ruppess) {
        sharedpreferences.edit().putString(RUPPESS, ruppess).commit();
    }

    public String getRuppess() {
        return sharedpreferences.getString(RUPPESS, "");
    }

    public void setDate(String date) {
        sharedpreferences.edit().putString(DATE, date).commit();
    }

    public String getDate() {
        return sharedpreferences.getString(DATE, "");
    }

    public void setGurudwaramasterid(String gurudwaramasterid) {
        sharedpreferences.edit().putString(GURUDWARAMASTERID, gurudwaramasterid).commit();
    }

    public String getGurudwaramasterid() {
        return sharedpreferences.getString(GURUDWARAMASTERID, "");
    }

    public void setUsermasterid(String usermasterid) {
        sharedpreferences.edit().putString(USERMASTERID, usermasterid).commit();
    }

    public String getUsermasterid() {
        return sharedpreferences.getString(USERMASTERID, "");
    }

    public void setUsertype(String usertype) {
        sharedpreferences.edit().putString(USERTYPE, usertype).commit();
    }

    public String getUsertype() {
        return sharedpreferences.getString(USERTYPE, "");
    }

//    public Boolean setPhoneNumber(Boolean phonenumber) {
//        sharedpreferences.edit().putBoolean(PHONENUMBER, phonenumber).commit();
//    }
//
//    public Boolean getPhonenumber() {
//        return sharedpreferences.getBoolean(PHONENUMBER, true);
//    }
}
