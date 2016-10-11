package com.hivedi.eventclip.proto;


import android.support.annotation.NonNull;

/**
 * Created by Kenumir on 2016-09-03.
 *
 */
public abstract class EventClipProvider {

    public abstract void deliver(EventParam param);
    public abstract void userProperty(@NonNull UserParam param);

	public void flush() {
        // To implement
    }

}
