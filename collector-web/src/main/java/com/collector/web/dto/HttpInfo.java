package com.collector.web.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class HttpInfo {
    private String uri;
    private String method;
    private HashMap<String, String> params;
    private HashMap<String, String> headers;
    private HashMap<String, String> pathVariables;
    private String body;
}
