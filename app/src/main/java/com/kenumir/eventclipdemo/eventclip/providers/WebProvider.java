package com.kenumir.eventclipdemo.eventclip.providers;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kenumir.eventclip.proto.EventClipProvider;
import com.kenumir.eventclip.proto.EventParam;
import com.kenumir.eventclip.proto.UserParam;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Kenumir on 2016-09-03.
 *
 */
public class WebProvider extends EventClipProvider {

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "WebProviderTask #" + mCount.getAndIncrement());
        }
    };

    private static class SerialExecutor implements Executor {
        final Queue<Runnable> tasks = new ArrayDeque<>();
        final Executor executor;
        Runnable active;

        SerialExecutor(Executor executor) {
            this.executor = executor;
        }

        public synchronized void execute(@NonNull final Runnable r) {
            tasks.add(new Runnable() {
                public void run() {
                    try {
                        r.run();
                    } finally {
                        scheduleNext();
                    }
                }
            });
            if (active == null) {
                scheduleNext();
            }
        }

        protected synchronized void scheduleNext() {
            if ((active = tasks.poll()) != null) {
                executor.execute(active);
            }
        }
    }

    static final OkHttpClient client = new OkHttpClient();
    static final Executor SERIAL_EXECUTOR = new SerialExecutor(Executors.newSingleThreadExecutor(sThreadFactory));

    static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private ArrayList<EventParam> deliveryErrorQueue = new ArrayList<>();
    private final Object deliveryErrorQueueSync = new Object();

    public WebProvider() {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                /**
                 * load saved
                 */
                return null;
            }
        }.executeOnExecutor(SERIAL_EXECUTOR);
    }

    private void onDeliveryError(EventParam param) {
        /**
         * save file on sd card/cache for later delivery
         */
        Log.e("tests", "Deliver error " + param.getName());
    }

    private void onDeliverySuccess(EventParam param) {
        /**
         * remove previously saved files (delivery error)
         */
        Log.d("tests", "Deliver success " + param.getName());
    }

    @Override
    public void deliver(EventParam param) {
        Log.v("tests", "deliver: " + param.getName());
        new AsyncTask<EventParam, Void, Void>(){
            @Override
            protected Void doInBackground(EventParam... eventParams) {
                EventParam currentEventParam = eventParams[0];
                JsonObject json = new JsonObject();
                json.addProperty("name", currentEventParam.getName());
                for(String key : currentEventParam.getAllFields().keySet()) {
                    json.addProperty(key, currentEventParam.getAllFields().get(key).toString());
                }

                RequestBody body = RequestBody.create(JSON, new Gson().toJson(json));

                Random r = new Random();

                Request request = new Request.Builder()
                        .url(r.nextBoolean() ? "http://kenumir.pl/" : "http://192.168.100.10/")
                        .post(body)
                        .build();

                EventParam errorDeliver = null;
                try {
                    Log.i("tests", "try deliver: " + currentEventParam.getName());
                    Response response = client.newCall(request).execute();
                    try {
                        Thread.sleep(1_200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!response.isSuccessful()) {
                        // not delivered
                        errorDeliver = currentEventParam;
                    }
                } catch (Exception e) {
                    // not delivered
                    errorDeliver = currentEventParam;
                }

                if (errorDeliver != null) {
                    synchronized (deliveryErrorQueueSync) {
                        if (!deliveryErrorQueue.contains(errorDeliver)) {
                            deliveryErrorQueue.add(errorDeliver);
                        }
                    }
                    onDeliveryError(errorDeliver);
                } else {
                    onDeliverySuccess(currentEventParam);
                }
                return null;
            }
        }.executeOnExecutor(SERIAL_EXECUTOR, param);
    }

	@Override
	public void userProperty(@NonNull UserParam param) {

	}

	@Override
    public void flush() {
        synchronized (deliveryErrorQueueSync) {
            if (deliveryErrorQueue.size() > 0) {
                Iterator<EventParam> it = deliveryErrorQueue.iterator();
                while (it.hasNext()) {
                    EventParam p = it.next();
                    Log.v("tests", "Flushing event " + p.getName());
                    deliver(p);
                    it.remove();
                }
            } else {
                Log.v("tests", "No events to flush");
            }
        }
    }
}
