package org.bankapp.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.UUID;

public final class AccountNumberGenerator {
    public static String generate() {
        return "AC" + UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }
}
