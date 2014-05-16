package com.android.indigo.service;

import android.content.Context;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by hoang_nam on 2014/05/07.
 */
public class Configurations {
    private static Configurations instance = null;
    private Properties properties = null;

    private Configurations (Context context) {
        properties = new Properties();

        try {
            properties.load(context.getResources().getAssets().open("indigo.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty (String key) {
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        return "no_prop";
    }

    public static Configurations getInstance () {
        return instance;
    }

    public static Configurations getInstance (Context context) {
        if (instance == null) {
            instance = new Configurations(context);
        }
        return instance;
    }
}
