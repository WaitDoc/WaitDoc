package com.team13.WaitDoc.paper.service;

import com.team13.WaitDoc.paper.dto.PaperDto;
import com.team13.WaitDoc.paper.entity.Paper;
import com.team13.WaitDoc.paper.repository.PaperRepository;
import com.team13.WaitDoc.s3.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class PaperService {

    private final AmazonS3Service amazonS3Service;
    private final PaperRepository paperRepository;


    public String upload(MultipartFile file, String name) {

        String url = amazonS3Service.imageUpload(file, name);

        return url;
    }

    public void register(PaperDto paperDto, String url) {
        Paper paper= paperDto.toEntity();
        paper.setImageUrl(url);
        paperRepository.save(paper);
    }

    public Paper findById(Long id) {
        return paperRepository.findById(id).orElseThrow(() ->  new NoSuchElementException("No paper found with ID "));
    }

    public List<Paper> findAll() {
        return paperRepository.findAll();
    }

    public void delete(Long id) {
        Paper paper = findById(id);

        paperRepository.delete(paper);
    }



}