package com.kenumir.eventclip.proto;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventParam implements Serializable {

	private String eventName;
	private Map<String, Object> fields;
	
	public EventParam(@EventName @NonNull String e) {
		this.eventName = e;
	}

	@EventName @NonNull
	public String getName() {
		return eventName;
	}

    public EventParam changeName(@EventName @NonNull String name) {
        eventName = name;
        return this;
    }
	
	public synchronized boolean hasFields() {
		return fields != null && fields.size() > 0;
	}
	
	public synchronized EventParam field(@EventName @NonNull String key, @NonNull Object value) {
		getFields().put(key, value);
		return this;
	}

	public synchronized EventParam fields(@NonNull Map<String, Object> props) {
		getFields().putAll(props);
		return this;
	}
	
	public synchronized EventParam clear(@EventName @NonNull String fieldKey) {
		getFields().remove(fieldKey);
		return this;
	}

    @NonNull
	public synchronized Map<String, Object> getAllFields() {
		return getFields();
	}

    @Nullable
	public synchronized Object getField(@EventName @NonNull String key) {
		return getFields().get(key);
	}

	public synchronized long getFieldAsLong(@EventName @NonNull String key, long defaultValue) {
		Object field = getFields().get(key);
		return field != null && field instanceof Long ? (long) field : defaultValue;
	}

	public synchronized int getFieldAsInt(@EventName @NonNull String key, int defaultValue) {
		Object field = getFields().get(key);
		return field != null && field instanceof Integer ? (int) field : defaultValue;
	}

	public synchronized double getFieldAsDouble(@EventName @NonNull String key, double defaultValue) {
		Object field = getFields().get(key);
		return field != null && field instanceof Double ? (double) field : defaultValue;
	}

	public synchronized float getFieldAsFloat(@EventName @NonNull String key, float defaultValue) {
		Object field = getFields().get(key);
		return field != null && field instanceof Float ? (float) field : defaultValue;
	}

	public synchronized String getFieldAsString(@EventName @NonNull String key, String defaultValue) {
		Object field = getFields().get(key);
		return field != null && field instanceof String ? (String) field : defaultValue;
	}

    @NonNull
	private Map<String, Object> getFields() {
		if (fields == null) {
			fields = new LinkedHashMap<>();
		}
		return fields;
	}

	@Override
	public String toString() {
		return "{" + eventName + ": " + getFields() + "}";
	}
}
