package com.opencart.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSource {
    public enum Format {
        CSV,
        JSON,
        XML,
        EXCEL
    }

    String value();
    Format format();
}
