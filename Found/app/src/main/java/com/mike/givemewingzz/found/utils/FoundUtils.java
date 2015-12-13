package com.mike.givemewingzz.found.utils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GiveMeWingzz on 12/13/2015.
 */
public class FoundUtils {

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

}
