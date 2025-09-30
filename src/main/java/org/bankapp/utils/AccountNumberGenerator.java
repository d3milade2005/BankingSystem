package org.bankapp.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class AccountNumberGenerator {
    private static final AtomicLong accountNumberGenerator = new AtomicLong(1000000000L);
    public static String generate() {
        return "AC" + accountNumberGenerator.getAndIncrement();
    }
}
