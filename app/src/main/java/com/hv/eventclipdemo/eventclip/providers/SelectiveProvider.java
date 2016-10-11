package com.hv.eventclipdemo.eventclip.providers;

import android.support.annotation.NonNull;

import com.hivedi.eventclip.proto.EventParam;
import com.hivedi.eventclip.providers.EventClipLogProvider;
import com.hv.eventclipdemo.eventclip.EventNames;

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
