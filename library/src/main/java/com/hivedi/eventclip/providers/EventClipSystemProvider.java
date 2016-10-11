package com.hivedi.eventclip.providers;

import android.support.annotation.NonNull;

import com.hivedi.eventclip.proto.EventClipProvider;
import com.hivedi.eventclip.proto.EventParam;
import com.hivedi.eventclip.proto.UserParam;

import java.util.Map;

/**
 * Created by Kenumir on 2016-09-03.
 *
 */
public class EventClipSystemProvider extends EventClipProvider {

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
        System.out.println(sb.toString());
    }

	@Override
	public void userProperty(@NonNull UserParam param) {
		System.out.println("Set user properties\n" + param.getAllProperties());
	}

}
