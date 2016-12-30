package com.kenumir.eventclip;

import android.support.annotation.NonNull;

import com.kenumir.eventclip.proto.EventClipProvider;
import com.kenumir.eventclip.proto.EventParam;
import com.kenumir.eventclip.proto.UserParam;

import java.util.ArrayList;

/**
 * Created by Kenumir on 2016-09-03.
 *
 */
public class EventClip {

    private volatile static ArrayList<EventClipProvider> providers = new ArrayList<>();

    public static synchronized void registerProvider(@NonNull EventClipProvider provider) {
        providers.add(provider);
    }

    public static synchronized void unregisterProvider(@NonNull EventClipProvider provider) {
        providers.remove(provider);
    }

    public static synchronized void clearProviders() {
        providers.clear();
    }

    public static synchronized void deliver(@NonNull EventParam param) {
        for(EventClipProvider provider : providers) {
            provider.deliver(param);
        }
    }

	public static void deliver(@NonNull String eventName) {
		deliver(new EventParam(eventName));
	}

	public static synchronized void userProperty(@NonNull UserParam param) {
		for(EventClipProvider provider : providers) {
			provider.userProperty(param);
		}
	}

	public static void userProperty(@NonNull String key, @NonNull Object value) {
		userProperty(new UserParam(key, value));
	}

    public static synchronized void flushProviders() {
        for(EventClipProvider provider : providers) {
            provider.flush();
        }
    }
}
