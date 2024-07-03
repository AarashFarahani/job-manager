package com.collector.web.job;

import com.collector.core.exception.CollectorException;
import com.collector.core.utils.CollectorJob;
import org.springframework.stereotype.Service;

@Service
public class SimpleCollectorBatchJob implements CollectorJob {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void run(Object... params) throws CollectorException {

    }
}
