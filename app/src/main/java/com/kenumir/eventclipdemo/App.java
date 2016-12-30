package com.kenumir.eventclipdemo;

import android.app.Application;

import com.kenumir.eventclip.EventClip;
import com.kenumir.eventclip.providers.EventClipLogProvider;
import com.kenumir.eventclip.providers.EventClipSystemProvider;
import com.kenumir.eventclipdemo.eventclip.providers.SelectiveProvider;

/**
 * Created by Kenumir on 2016-09-03.
 *
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        EventClip.registerProvider(new EventClipLogProvider("tests"));
        EventClip.registerProvider(new EventClipSystemProvider());
        EventClip.registerProvider(new SelectiveProvider("selective"));

    }
}
