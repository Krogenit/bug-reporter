package ru.utair.kubsu.bugreporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public interface BugLogger {
    default Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }
}