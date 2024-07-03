package com.collector.web.job.writer;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class LoggingItemWriter implements ItemWriter<Object> {
    @Override
    public void write(Chunk<? extends Object> chunk) throws Exception {
        log.info(chunk);
    }
}
