package com.collector.web.utils;

import com.collector.web.dto.JobParams;
import com.collector.web.job.reader.CsvItemReader;
import com.collector.web.job.writer.LoggingItemWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
@RequiredArgsConstructor
public class JobCollectorBuilder {
    private final ApplicationContext applicationContext;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final LoggingItemWriter loggingItemWriter;

    private Step step(JobParams jobParams) {
        var csvItemReader = this.applicationContext.getBean(CsvItemReader.class);
        return new StepBuilder("http-step", jobRepository)
                .chunk(2, this.platformTransactionManager)
                .reader(csvItemReader)
                .writer(this.loggingItemWriter)
//                .listener()//Completed
//                .listener(csvItemReader)
//                .allowStartIfComplete(true)
//                .taskExecutor(this.taskExecutor())//Multi thread
                .build();
    }

    public Job createJob(JobParams jobParams) {
        return new JobBuilder("Customer", this.jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(this.step(jobParams))
                .build();
    }
}
