package com.kenumir.eventclip.providers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kenumir.eventclip.proto.EventClipProvider;
import com.kenumir.eventclip.proto.EventParam;
import com.kenumir.eventclip.proto.UserParam;

import java.util.Map;

/**
 * Created by Kenumir on 2016-09-03.
 *
 */
public class EventClipLogProvider extends EventClipProvider {

    private String tag;

    public EventClipLogProvider(@NonNull String tag) {
        this.tag = tag;
    }

    @Override
    public void deliver(EventParam param) {
        StringBuilder sb = new StringBuilder();
        sb.append(param.getName());
        if (param.hasFields()) {
            sb.append(":\n");

            Map<String, Object> fields = param.getAllFields();
            for(String key : fields.keySet()) {
                sb.append("\t")
                        .append(key)
                        .append("=")
                        .append(fields.get(key))
                        .append("\n");
            }
        }
        Log.v(tag, sb.toString());
    }

	@Override
	public void userProperty(@NonNull UserParam param) {
		Log.v(tag, "Set user properties\n" + param.getAllProperties());
	}

}
