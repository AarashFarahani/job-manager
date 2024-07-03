package com.collector.web.utils;

import com.collector.core.exception.CollectorException;
import com.collector.core.utils.CollectorJob;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;

@Log4j2
@Getter
@RequiredArgsConstructor
public class SpringBatchCollectorJob implements CollectorJob {
    private final String name;
    private final Job job;
    private final JobLauncher jobLauncher;

    @Override
    public void run(Object... params) throws CollectorException {
        try {
            this.jobLauncher.run(job, new JobParameters());
        } catch (Exception e) {
            log.error("Job failed {}: {}", this.getName(), e.getMessage());
            throw new CollectorException(e.getMessage());
        }
    }
}
