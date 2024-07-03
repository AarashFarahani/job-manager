package com.collector.web.service;

import com.collector.core.exception.CollectorException;
import com.collector.web.utils.CollectorJobFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectorJobService {
    private final CollectorJobFactory collectorJobFactory;

    public void run(String jobName, Object... params) throws CollectorException {
        var collectorJob = this.collectorJobFactory.findJob(jobName);
        collectorJob.run(params);
    }
}
