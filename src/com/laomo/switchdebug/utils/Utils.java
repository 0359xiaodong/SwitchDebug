package com.laomo.switchdebug.utils;

import java.io.IOException;
import java.io.InputStreamReader;

import com.laomo.switchdebug.R;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.widget.Toast;

public class Utils {

    public static void switchDebug(Context context) {
	if (hasRoot()) {
	    // debug开关切换
	    ContentResolver resolver = context.getContentResolver();
	    Settings.Secure.putInt(resolver, Settings.Secure.ADB_ENABLED,
		(1 - Settings.Secure.getInt(resolver, Settings.Secure.ADB_ENABLED, 0)));
	} else {
	    showToast(context, R.string.not_root);
	}

    }
    
    public static void switchDebug(Context context,int adbEnabled) {
	if (hasRoot()) {
	    // debug开关切换
	    ContentResolver resolver = context.getContentResolver();
	    if (adbEnabled != Settings.Secure.getInt(resolver, Settings.Secure.ADB_ENABLED, 0)) {
		Settings.Secure.putInt(resolver, Settings.Secure.ADB_ENABLED, adbEnabled);
	    }
	} else {
	    showToast(context, R.string.not_root);
	}

    }

    public static boolean hasRoot() {
	char[] arrayOfChar = new char[1024];
	try {
	    int j = new InputStreamReader(Runtime.getRuntime().exec("su -c ls").getErrorStream()).read(arrayOfChar);
	    if (j == -1) {
		return true;
	    }
	} catch (IOException e) {
	}
	return false;
    }
    
    public static void showToast(Context context, int msgResId) {
	Toast.makeText(context, msgResId, Toast.LENGTH_LONG).show();
    }
    
    public static void showToast(Context context, String msg) {
	Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}