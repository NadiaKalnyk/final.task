package ua.hillel.rest.entities;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Comment {

    private Integer id;
    private String message;
    private String username;
    private String commentDate;
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();


}