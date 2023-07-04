package com.team13.WaitDoc.category.DTO;

import lombok.*;

import java.util.List;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class CategoryRequestDTO {
    private String region;
    private String addr;
    private String[] addrs;
    private String location;
    private String name;
    private String department;
    private String classify;
    @Setter
    private int page = 1;
    @Setter
    private int rows = 40;

    public CategoryRequestDTO(String region, String addr, String location, String name, String department, String classify, int page, int rows) {
        this.region = region;
        this.addr = addr;
        this.location = location;
        this.name = name;
        this.department = department;
        this.classify = classify;
        if (page > 0) this.page = page;
        if (rows > 0) this.rows = rows;

        if(location != null && !location.isBlank() && location.contains(" ")){
            addrs = location.split(" ");
            this.region = addrs[0];
            this.addr = addrs[1];
        }
        else{
            this.region = location;
        }
    }
}
