package com.team13.WaitDoc.category.DTO;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CategoryRequestDTO {
    private String region;
    private String addr;
    private final String location;
    private final String name;
    private final String department;
    private final String classify;

    public CategoryRequestDTO(String location, String name, String department, String classify) {
        this.location = location;
        this.name = name;
        this.department = department;
        this.classify = classify;

        if(location.contains(" ")){
            String[] locationParts = location.split(" ");
            this.region = locationParts[0];
            this.addr = locationParts[1];
        }
        else{
            this.region = location;
        }

    }
}
