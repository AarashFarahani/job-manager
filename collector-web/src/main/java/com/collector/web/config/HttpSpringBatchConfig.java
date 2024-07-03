//package com.collector.web.config;
//
//import com.collector.web.dto.SimpleJobConfigs;
//import com.collector.web.job.reader.CsvItemReader;
//import com.collector.web.job.writer.LoggingItemWriter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.core.task.TaskExecutor;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class HttpSpringBatchConfig {
//    private final LoggingItemWriter loggingItemWriter;
//    private final ApplicationContext applicationContext;
//
//    @Bean
//    public TaskExecutor taskExecutor() {
//        return new SimpleAsyncTaskExecutor("spring_batch");
//    }
//
//    @Bean
//    public Step step1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        var csvItemReader = this.applicationContext.getBean(CsvItemReader.class);
//        return new StepBuilder("http-step", jobRepository)
//                .chunk(2, platformTransactionManager)
//                .reader(csvItemReader)
//                .writer(this.loggingItemWriter)
////                .listener()//Completed
////                .listener(csvItemReader)
////                .allowStartIfComplete(true)
////                .taskExecutor(this.taskExecutor())//Multi thread
//                .build();
//    }
//
//    @Bean
//    public Job customerJob(JobRepository jobRepository, Step step1) {
//        return new JobBuilder("Customer", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(step1)
//                .build();
//    }
//}
