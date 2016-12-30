package com.kenumir.eventclipdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hv.eventclipdemo.R;
import com.kenumir.eventclip.EventClip;
import com.kenumir.eventclip.proto.EventParam;
import com.kenumir.eventclipdemo.eventclip.EventNames;
import com.kenumir.eventclipdemo.eventclip.providers.WebProvider;

public class WebProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_provider);
        EventClip.clearProviders();
        EventClip.registerProvider(new WebProvider());
    }

    public void handleClick(View view) {
        switch(view.getId()) {
            case R.id.b1:
                EventClip.deliver(new EventParam(EventNames.EVENT1));
                break;
            case R.id.b2:
                EventClip.deliver(new EventParam(EventNames.EVENT2));
                break;
            case R.id.b3:
                EventClip.flushProviders();
                break;
        }
    }
}
