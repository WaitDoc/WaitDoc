package com.team13.WaitDoc.Category.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@Builder
@ToString
public class CategoryRequestDTO {
    private final String region;
    private final String addr;
    private final String name;
    private final String department;
    private final String classify;
}
