package com.collector.web.dto;

import lombok.Data;

@Data
public class JobProperties {
    private String type;
    private HttpInfo http;
    private FileInfo file;
}
