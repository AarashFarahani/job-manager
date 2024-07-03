package com.collector.web.job.reader;

import com.collector.web.dto.SimpleJobConfigs;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.UUID;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class CsvItemReader implements ItemReader {
    private final SimpleJobConfigs simpleJobConfigs;
    private static final String CSV_FILE_PATH = "data/customers.csv";
    private LinkedList<Object> queue;
    private final String jobId = UUID.randomUUID().toString();

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Before reading item");
        this.queue = new LinkedList<>();
        File csvFile = null;
        try {
            csvFile = new ClassPathResource(CSV_FILE_PATH).getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (var streamLines = Files.lines(Paths.get(csvFile.getPath()), Charset.defaultCharset())) {
            var lines = streamLines.toList();

            for (int i = 1; i < lines.size(); i++) {
                var line = lines.get(i);
                this.queue.push(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object read() throws Exception {
        return this.queue.poll();
    }
}
