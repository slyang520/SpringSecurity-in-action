package com.example.slyangsecurity.es;

import io.searchbox.annotations.JestId;
import lombok.Data;

@Data
public class TestPojo {

    public TestPojo(String documentId, String first_name, String last_name, Long age) {
        this.documentId = documentId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
    }

    @JestId
    private String documentId;

    private String first_name;
    private String last_name;
    private Long age;

}
