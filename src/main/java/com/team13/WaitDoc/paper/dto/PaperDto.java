package com.team13.WaitDoc.paper.dto;

import com.team13.WaitDoc.paper.entity.Paper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaperDto {
    private Long id;

    private String title;
    private String content;

    private MultipartFile file;

    private String url;

    public Paper toEntity(){
        return Paper.builder()
                .title(title)
                .content(content)
                .build();
    }
}
