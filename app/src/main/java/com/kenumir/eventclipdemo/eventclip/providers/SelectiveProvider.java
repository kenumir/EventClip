package com.kenumir.eventclipdemo.eventclip.providers;

import android.support.annotation.NonNull;

import com.kenumir.eventclip.proto.EventParam;
import com.kenumir.eventclip.providers.EventClipLogProvider;
import com.kenumir.eventclipdemo.eventclip.EventNames;

/**
 * Created by Kenumir on 2016-09-03.
 *
 */
public class SelectiveProvider extends EventClipLogProvider {

    public SelectiveProvider(@NonNull String tag) {
        super(tag);
    }

    @Override
    public void deliver(EventParam param) {
        switch(param.getName()) {
            case EventNames.BUTTON_CLICK:
                super.deliver(param.changeName(EventNames.BUTTON_PRESS));
                break;
        }
    }

}
