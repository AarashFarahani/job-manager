package com.collector.web.dto;

import lombok.Data;

@Data
public class JobParams {
    private String name;

    private JobProperties input;
    private JobProperties output;
}
