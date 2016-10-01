package util;

import android.content.Context;
import android.content.SharedPreferences;

import constants.Constants;

/**
 * Created by whoami on 9/29/16.
 */

public class AppPreferences {

    private static AppPreferences instance;
    private Context context;
    private SharedPreferences preferences;
    private AppPreferences() {

    }

    public static AppPreferences getInstance(Context context) {
        if (instance == null)
            instance = new AppPreferences();

        instance.setContext(context);
        return instance;
    }

    public void putString(String key, String value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void putBoolean(String key, boolean value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String getString(String key){
        return preferences.getString(key, "");
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public void setContext(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(Constants.PREFERENCE_KEY, Context.MODE_PRIVATE);
    }
}
