package com.collector.web.controller;

import com.collector.core.exception.CollectorException;
import com.collector.core.utils.CollectorJob;
import com.collector.web.utils.CollectorJobFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobController {
//    private final JobLauncher jobLauncher;
//    private final Job job;
    private final CollectorJobFactory collectorJobFactory;

    @GetMapping("/run")
    public ResponseEntity run() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, CollectorException {
        var job = this.collectorJobFactory.findJob("J1");
        job.run();
//        this.jobLauncher.run(this.job, new JobParameters());
        return ResponseEntity.ok().build();
    }
}
