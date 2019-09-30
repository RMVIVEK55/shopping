package trainingproject.tridentnets.com.shoppingtask.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

import java.util.Locale;

/**
 * Preference class for the whole project
 *
 * @author dharmalingam.r
 */
public class AppPreference extends Preference {

    private static final String TAG = "AppPreference";
    /**
     * Singleton getInstance for AppPreference
     */
    public static AppPreference instance;

    private SharedPreferences sharedPreferences;

    public AppPreference(Context context) {
        super(context);
        sharedPreferences = context.getSharedPreferences("Preferences",Context.MODE_PRIVATE);
    }

    public static AppPreference getInstance(Context context) {
        if (instance == null)
            return new AppPreference(context);
        return instance;
    }

    /**
     * @param key   key constant from AppPreference
     * @param value String value to be stored associated with the key
     *              <p>
     *              Stores the String value in preference with the key
     *              </p>
     */
    public void putString(StringKeys key, String value) {
        sharedPreferences.edit().putString(key.toString(), value).commit();
    }

    /**
     * @param key key constant from AppPreference
     * @return String value associated with key or null
     */
    public String getString(StringKeys key) {
        return sharedPreferences.getString(key.toString(), null);
    }

    /**
     * @param key   key constant from AppPreference
     * @param value integer value to be stored associated with the key
     *              <p>
     *              Stores the integer value in preference with the key
     *              </p>
     */
    public void putInt(IntKeys key, int value) {
        sharedPreferences.edit().putInt(key.toString(), value).commit();
    }

    /**
     * @param key key constant from AppPreference
     * @return integer value associated with key or Integer.MIN_VALUE
     */
    public int getInt(IntKeys key) {
        return sharedPreferences.getInt(key.toString(), Integer.MIN_VALUE);
    }

    /**
     * @param key   key constant from AppPreference
     * @param value boolean value to be stored associated with the key
     *              <p>
     *              Stores the boolean value in preference with the key
     *              </p>
     */
    public void putBoolean(BooleanKeys key, boolean value) {
        sharedPreferences.edit().putBoolean(key.toString(), value).commit();
    }

    /**
     * @param key key constant from AppPreference
     * @return boolean value associated with key or false
     */
    public boolean getBoolean(BooleanKeys key) {
        return sharedPreferences.getBoolean(key.toString(), false);
    }

    /**
     * @param key   key constant from AppPreference
     * @param value float value to be stored associated with the key
     *              <p>
     *              Stores the float value in preference with the key
     *              </p>
     */
    public void putDouble(DoubleKeys key, float value) {
        sharedPreferences.edit().putFloat(key.toString(), value).commit();
    }

    /**
     * @param key key constant from AppPreference
     * @return float value associated with key or Float.MIN_VALUE
     */
    public Float get(DoubleKeys key) {
        return sharedPreferences.getFloat(key.toString(), Float.MIN_VALUE);
    }

    /**
     * This enum values are used to manage String values in preference
     */
    public static enum StringKeys {


        /**
         * Meeting id
         */

      DOMAIN,  MAILID, CARTFRAGMENTNAME;


        public String toString() {
            return this.name().toLowerCase(Locale.ENGLISH);
        }

    }


    /**
     * This enum values are used to manage Boolean values in preference
     */
    public static enum BooleanKeys {

        /**
         * True for user already logged in
         */
        LOGGED_IN,

        /**
         * TO identify if the app is opened for the first time
         */
        IS_SECOND_TIME;

        public String toString() {
            return this.name().toLowerCase(Locale.ENGLISH);
        }

    }

    /**
     * This enum values are used to manage Boolean values in preference
     */
    public static enum LoginBooleanKeys {

        /**
         * True for user already logged in
         */
        ISLogin;



        public String toString() {
            return this.name().toLowerCase(Locale.ENGLISH);
        }

    }

    /**
     * This enum values are used to manage Integer values in preference
     */
    public static enum IntKeys {


        /**
         * Key for storing the app_version
         */
        APP_VERSION;

        public String toString() {
            return this.name().toLowerCase(Locale.ENGLISH);
        }


    }


    /**
     * This enum values are used to manage Float values in preference
     */
    public static enum DoubleKeys {

        /**
         * Latitude of the user's current location
         */
        INITIAL_LONG;

        public String toString() {
            return this.name().toLowerCase(Locale.ENGLISH);
        }

    }


}
