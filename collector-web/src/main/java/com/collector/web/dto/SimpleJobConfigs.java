package com.collector.web.dto;

import com.collector.web.utils.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "jobs")
@PropertySource(value = "classpath:config.yml", factory = YamlPropertySourceFactory.class)
public class SimpleJobConfigs {
    private List<JobParams> jobConfigs;
}
