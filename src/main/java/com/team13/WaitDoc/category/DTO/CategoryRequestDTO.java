package com.team13.WaitDoc.category.DTO;

import lombok.*;

import java.util.List;

@Getter
@ToString
@Builder
//@NoArgsConstructor
public class CategoryRequestDTO {
    private String location;
    private String name;
    private String department;
    private String classify;
    @Setter
    private int page = 1;
    @Setter
    private int rows = 40;

    public String[] getAddrs(){
        if(location != null && !location.isBlank()){
            return location.trim().split(" ");
        }
        return null;
    }

    public CategoryRequestDTO(String location, String name, String department, String classify, int page, int rows) {
        this.location = location;
        this.name = name;
        this.department = department;
        this.classify = classify;
        if (page > 0) this.page = page;
        if (rows > 0) this.rows = rows;
    }

}
