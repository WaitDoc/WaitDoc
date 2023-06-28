package com.team13.WaitDoc.hospital.controller;


import com.team13.WaitDoc.base.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;


@Controller
@RequiredArgsConstructor
public class HospitalController {

    @GetMapping("/map")
    public String mapHospital(Model model) throws IOException, InterruptedException {

        model.addAttribute("appKey", AppConfig.getMapKey());
        return "hospital/map";
    }
}
