package com.mike.givemewingzz.found.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.mike.givemewingzz.found.FoundApplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GiveMeWingzz on 12/13/2015.
 */
public class FoundUtils {

    static Typeface tfRobotRegular = null;
    static Typeface tfRobotMedium = null;
    static Typeface tfRobotBold = null;
    static Typeface tfRobotoLight = null;

    private static final int BUFFER_SIZE = 4 * 1024;
    private static final String TAG = FoundUtils.class.getSimpleName();

    public static String inputStreamToString(InputStream inputStream, String charsetName)
            throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(inputStream, charsetName);
        char[] buffer = new char[BUFFER_SIZE];
        int length;
        while ((length = reader.read(buffer)) != -1) {
            builder.append(buffer, 0, length);
        }
        return builder.toString();
    }

    public static boolean hasTelephony(Activity context) {
        Log.i("hasTelephony", "hasTelephony");
        boolean hasTelephony;
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (tm == null) {
                hasTelephony = false;
                Log.d("hasTelephony", "TelephonyManager is null");
                return hasTelephony;
            }

            PackageManager pm = context.getPackageManager();
            Method method;

            Class<?>[] parameters = new Class<?>[1];
            parameters[0] = String.class;
            method = pm.getClass().getMethod("hasSystemFeature", parameters);
            Object[] parm = new Object[1];
            parm[0] = PackageManager.FEATURE_TELEPHONY;
            Object retValue = method.invoke(pm, parm);
            if (retValue instanceof Boolean) {
                hasTelephony = (Boolean) retValue;
                Log.d("hasTelephony", "retValue instanceof Boolean " + hasTelephony);
            } else {
                Log.d("hasTelephony", "retValue NOT instanceof Boolean ");
                hasTelephony = false;
            }
        } catch (Exception e) {
            Log.e("hasTelephony", "Exception hasTelephony = false " + e);
            hasTelephony = false;
        }

        return hasTelephony;
    }

    public static Typeface getTfRobotoRegular() {
        if (tfRobotRegular == null) {
            AssetManager mgr = FoundApplication.getInstance().getResources().getAssets();
            tfRobotRegular = Typeface.createFromAsset(mgr, "fonts/roboto_regular.ttf");
        }

        return tfRobotRegular;
    }

    public static Typeface getTfRobotoLight() {
        if (tfRobotoLight == null) {
            AssetManager mgr = FoundApplication.getInstance().getResources().getAssets();
            tfRobotoLight = Typeface.createFromAsset(mgr, "fonts/roboto_light.ttf");
        }

        return tfRobotoLight;
    }

    public static Typeface getTfRobotMedium() {
        if (tfRobotMedium == null) {
            AssetManager mgr = FoundApplication.getInstance().getResources().getAssets();
            tfRobotMedium = Typeface.createFromAsset(mgr, "fonts/roboto_medium.ttf");
        }

        return tfRobotMedium;
    }

    public static Typeface getTfRobotBold() {
        if (tfRobotBold == null) {
            AssetManager mgr = FoundApplication.getInstance().getResources().getAssets();
            tfRobotBold = Typeface.createFromAsset(mgr, "fonts/roboto_bold.ttf");
        }

        return tfRobotBold;
    }

    public static List<String> removedChars(
            String inputValue,
            String splittingCharacter,
            final int desiredFirstOffset,
            final int desiredLastOffset) {

        int startOffset = 0;
        final int charLength = inputValue.length();

        String newValue;
        String splittedValue;

        List<String> tempList = new ArrayList<>();

        newValue = inputValue.substring(startOffset, charLength - desiredLastOffset);

        Log.d(TAG, "New Value after removing last : " + desiredLastOffset + " :: " + newValue);
        newValue = newValue.substring(newValue.lastIndexOf("[") + 1);

        Log.d(TAG, "New Value after removing first : " + desiredFirstOffset + " :: " + newValue);
        Log.d(TAG, "New Value : " + newValue);


        String[] tempString = newValue.split(splittingCharacter);

        final int tempStringChar = tempString.length;

        Log.d(TAG, "TempStringChar : " + tempStringChar);

        for (int j = 0; j < tempStringChar; j++) {
            splittedValue = tempString[j];
            if (!splittedValue.endsWith("\"")) {
                splittedValue = splittedValue + "\"";
            } else if (!splittedValue.startsWith("\"")) {
                splittedValue = "\"" + splittedValue;
            }
            tempList.add(splittedValue);
            Log.d(TAG, "New Value after removing first Splitted: " + splittedValue);
        }

        List<String> toFlush = new ArrayList<>();
        String quotes = null;
        for (String escapes : tempList) {
            quotes = escapes.substring(0, escapes.length() - 1);
            Log.d(TAG, "Splitted Quotes : " + quotes);
            toFlush.add(quotes);
        }

        List<String> toReturn = new ArrayList<>();
        for (String esc : toFlush) {

            toReturn.add(esc.substring(1));
            for (String q : toReturn) {
                Log.d(TAG, "Splitted ESC : " + q);
            }

        }

        tempList.clear();
        toFlush.clear();
        return toReturn;
    }

    // Use it when using custom views
//    @Override
//    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        final ViewTreeObserver observer = view.getViewTreeObserver();
//        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            public void onGlobalLayout() {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    if (observer.isAlive()) {
//                        observer.removeOnGlobalLayoutListener(this);
//                    }
//                } else {
//                    if (observer.isAlive()) {
//                        observer.removeGlobalOnLayoutListener(this);
//                    }
//                }
//
//                // Do somethign here
//
//            }
//        });
//
//    }

}
