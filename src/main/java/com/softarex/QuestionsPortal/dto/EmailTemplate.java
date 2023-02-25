package com.softarex.QuestionsPortal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailTemplate {

    private String from;
    private String to;
    private String subject;
    private String attachment;
    private String templateLocation;
    private Map<String, Object> context;
}