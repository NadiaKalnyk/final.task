package ua.hillel.rest.entities;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;


@Data
public class User {

    private Integer id;
    private String username;
    private String name;
    private String lastname;
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
}