package com.kenumir.eventclip.proto;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserParam implements Serializable {

	private Map<String, Object> properties;

	public UserParam() {}

	public UserParam(@UserProperty @NonNull String key, @NonNull Object value) {
		property(key, value);
	}

	public synchronized boolean hasProperties() {
		return properties != null && properties.size() > 0;
	}
	
	public synchronized UserParam property(@UserProperty @NonNull String key, @NonNull Object value) {
		getProperties().put(key, value);
		return this;
	}

	public synchronized UserParam properties(@NonNull Map<String, Object> props) {
		getProperties().putAll(props);
		return this;
	}
	
	public synchronized UserParam clear(@UserProperty @NonNull String fieldKey) {
		getProperties().remove(fieldKey);
		return this;
	}

    @NonNull
	public synchronized Map<String, Object> getAllProperties() {
		return getProperties();
	}

	@Nullable
	public synchronized Object getProperty(@UserProperty @NonNull String key) {
		return getProperties().get(key);
	}

	public synchronized long getPropertyAsLong(@EventName @NonNull String key, long defaultValue) {
		Object field = getProperties().get(key);
		return field != null && field instanceof Long ? (long) field : defaultValue;
	}

	public synchronized int getPropertyAsInt(@EventName @NonNull String key, int defaultValue) {
		Object field = getProperties().get(key);
		return field != null && field instanceof Integer ? (int) field : defaultValue;
	}

	public synchronized double getPropertyDouble(@EventName @NonNull String key, double defaultValue) {
		Object field = getProperties().get(key);
		return field != null && field instanceof Double ? (double) field : defaultValue;
	}

	public synchronized float getPropertyFloat(@EventName @NonNull String key, float defaultValue) {
		Object field = getProperties().get(key);
		return field != null && field instanceof Float ? (float) field : defaultValue;
	}

	public synchronized String getPropertyString(@EventName @NonNull String key, String defaultValue) {
		Object field = getProperties().get(key);
		return field != null && field instanceof String ? (String) field : defaultValue;
	}

    @NonNull
	private Map<String, Object> getProperties() {
		if (properties == null) {
			properties = new LinkedHashMap<>();
		}
		return properties;
	}

	@Override
	public String toString() {
		return "" + getProperties();
	}

}
