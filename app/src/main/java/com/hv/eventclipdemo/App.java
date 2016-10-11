package com.hv.eventclipdemo;

import android.app.Application;

import com.hivedi.eventclip.EventClip;
import com.hivedi.eventclip.providers.EventClipLogProvider;
import com.hivedi.eventclip.providers.EventClipSystemProvider;
import com.hv.eventclipdemo.eventclip.providers.SelectiveProvider;

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
