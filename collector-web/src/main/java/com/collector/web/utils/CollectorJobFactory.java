package com.collector.web.utils;

import com.collector.core.exception.CollectorException;
import com.collector.core.utils.CollectorJob;
import com.collector.web.dto.SimpleJobConfigs;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CollectorJobFactory {
    private final SimpleJobConfigs simpleJobConfigs;
    private final JobCollectorBuilder jobCollectorBuilder;
    private final JobLauncher jobLauncher;
    private List<CollectorJob> collectorJobs = new ArrayList<>();

    @PostConstruct
    public void registerJobs() {
        for (var jobConfig : simpleJobConfigs.getJobConfigs()) {
            var job = this.jobCollectorBuilder.createJob(jobConfig);
            var collectorJob = new SpringBatchCollectorJob(jobConfig.getName(), job, this.jobLauncher);
            this.collectorJobs.add(collectorJob);
        }
    }

    public CollectorJob findJob(String jobName) throws CollectorException {
        return this.collectorJobs.stream()
                .filter(a-> a.getName().equalsIgnoreCase(jobName))
                .findFirst()
                .orElseThrow(()-> new CollectorException(String.format("Invalid Job Name, %s", jobName)));
    }
}
