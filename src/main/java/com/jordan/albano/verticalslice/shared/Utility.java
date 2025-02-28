package com.jordan.albano.verticalslice.shared;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

public @interface Utility {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
