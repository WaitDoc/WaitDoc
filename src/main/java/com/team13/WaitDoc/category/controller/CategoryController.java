package com.team13.WaitDoc.category.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.category.DTO.HospitalResponseDTO;
import com.team13.WaitDoc.base.util.ApiUt;
import com.team13.WaitDoc.base.util.HospitalXml;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/hospital/category")
public class CategoryController {
    @GetMapping("")
    public String showCategory(){
        return "Category/category";
    }
    @GetMapping("/all")
    @ResponseBody
    public List<HospitalResponseDTO> showHospital() throws IOException, InterruptedException {
        String url = ApiUt.Url.builder()
                .page(100)
                .rows(1000)
                .build();
        List<HospitalXml.Item> items = ApiUt.Response.getItems(ApiUt.Response.getBody(url));

        ObjectMapper mapper = new ObjectMapper();
        List<HospitalResponseDTO>list = mapper.convertValue(items, new TypeReference<List<HospitalResponseDTO>>() {});
        return list;
    }

    /*** TODO
     * 1.find시 요청받은 정보에 대한 DB에 병원 정보 있는지 확인
     * 2. DB에 병원이 존재한다면 createDate가 3개월이 지났는지 확인
     * 3. 데이터가 없거나 생성한지 3개월이 지났다면 API에서 데이터 가져옴
     * 4. 둘다 해당하지 않는다면 데이터를 db에서 보내줌
     */
    @ResponseBody
    @GetMapping("/find")
    public List<HospitalResponseDTO> find(CategoryRequestDTO requestDTO) throws IOException, InterruptedException {
        String url = ApiUt.Url.getByRequestDTO(requestDTO);
        return ApiUt.Response.getResponseDTOs(url);
    }

}

