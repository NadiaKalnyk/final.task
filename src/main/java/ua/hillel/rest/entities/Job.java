package ua.hillel.rest.entities;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Job {

    private Integer id;
    private String title;
    private String description;
    private Integer price;
    private String user;
    private Integer noOfComments;
    private String message;
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
}