package com.team13.WaitDoc.hospital.controller;

import com.team13.WaitDoc.base.config.AppConfig;
import com.team13.WaitDoc.base.util.ApiUt;
import com.team13.WaitDoc.base.util.HospitalXml;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HospitalController {

    @GetMapping("/map")
    public String mapHospital(Model model) throws IOException, InterruptedException {

        model.addAttribute("appKey", AppConfig.getMapKey());
        return "hospital/map";
    }
}


