package org.bankapp.utils;

import java.util.concurrent.atomic.AtomicLong;

public final class AccountNumberGenerator {
    private static final AtomicLong accountNumberGenerator = new AtomicInteger(1000000000L);
    public static String generate() {
        return "AC" + accountNumberGenerator.getAndIncrement();
    }
}
