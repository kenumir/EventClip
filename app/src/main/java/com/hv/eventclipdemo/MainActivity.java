package com.hv.eventclipdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hivedi.eventclip.EventClip;
import com.hivedi.eventclip.proto.EventParam;
import com.hivedi.eventclip.proto.UserParam;
import com.hv.eventclipdemo.eventclip.EventNames;
import com.hv.eventclipdemo.eventclip.UserProperties;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long t1 = SystemClock.elapsedRealtime();
        setContentView(R.layout.activity_main);

        EventClip.deliver(new EventParam(EventNames.MAIN_CREATE).field("time", SystemClock.elapsedRealtime() - t1));
	    EventClip.userProperty(new UserParam("aaa", "123").property(UserProperties.MY_NAME, "Mike"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventClip.deliver(new EventParam(EventNames.MAIN_RESUME).field("test", "test value"));
    }

    public void handleClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                EventClip.deliver(new EventParam(EventNames.BUTTON_CLICK).field("Action", 1));
                break;
            case R.id.button2:
                EventClip.deliver(new EventParam(EventNames.BUTTON_CLICK).field("Action", 2));
                break;
            case R.id.button3:
                startActivity(new Intent(this, WebProviderActivity.class));
                break;
        }
    }
}
