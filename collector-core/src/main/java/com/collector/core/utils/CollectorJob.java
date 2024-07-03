package com.collector.core.utils;

import com.collector.core.exception.CollectorException;

public interface CollectorJob {
    String getName();
    void run(Object... params) throws CollectorException;
}
