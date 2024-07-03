package com.collector.web.job;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.UUID;

@Component
public class HttpItemReader implements ItemReader, StepExecutionListener {
//    private HttpInfo httpInfo;
//
//    public HttpItemReader(HttpInfo httpInfo) {
//        this.httpInfo = httpInfo;
//    }
//    @Value("${pull-api.host}")
    private String uri = "http://localhost:8180/customers";
    private LinkedList<Object> queue;
    private final String jobId = UUID.randomUUID().toString();
//

//    private String uri = "https://reqres.in/api/users?page=2";

//    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Before reading item");

        var restTemplate = new RestTemplate();
        var result = restTemplate.getForObject(this.uri, Object[].class);
//        var customerBatchList = Arrays.stream(result)
//                .map(a-> new CustomerDto(this.jobId, a))
//                .toList();
        this.queue = new LinkedList<>(Arrays.stream(result).toList());
        stepExecution.getExecutionContext().put(this.jobId, result);
    }
//
    @Override
    public Object read() throws Exception {
//        Thread.sleep(1000);
        return this.queue.poll();
    }
}
