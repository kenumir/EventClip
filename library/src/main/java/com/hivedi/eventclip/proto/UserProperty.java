package com.hivedi.eventclip.proto;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Created by Hivedi2 on 2016-09-07.
 *
 */
@Target({ PARAMETER, FIELD, METHOD })
@Retention(RetentionPolicy.SOURCE)
public @interface UserProperty {
}
