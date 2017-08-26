package com.zxventures.challenge;

import android.app.Application;

/**
 * Created by Leonardo on 26/08/2017.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GraphApi.init(this);
    }
}
