package com.android.indigo.service;

import android.app.Activity;
import android.content.Context;

import com.android.indigo.R;
import com.android.indigo.activity.HomeActivity;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

/**
 * Created by hoang_nam on 2014/05/07.
 */
public class ParseService {
    public ParseService initialize (Context context) {
        Parse.initialize(context, Configurations.getInstance().getProperty("PARSE_USER_ID"), Configurations.getInstance().getProperty("PARSE_USER_SECRET"));
        return this;
    }

    public void start(Activity activity) {
        PushService.setDefaultPushCallback(activity.getApplicationContext(), HomeActivity.class, R.drawable.icon);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
