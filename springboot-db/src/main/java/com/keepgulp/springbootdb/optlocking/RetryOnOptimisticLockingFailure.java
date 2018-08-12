package com.keepgulp.springbootdb.optlocking;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RetryOnOptimisticLockingFailure {

}
