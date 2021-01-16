package com.example.frontend.controller;

import com.example.frontend.dto.CakeDto;
import com.example.frontend.service.CakeClientRestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class CakeController {

    private final CakeClientRestService cakeRestService;

    public CakeController(CakeClientRestService cakeRestService) {
        this.cakeRestService = cakeRestService;
    }

    @GetMapping
    public String main(Model model){
        List<CakeDto> cakeDtos = cakeRestService.getAllCakes();
        CakeDto cakeDto = new CakeDto();
        model.addAttribute("cakes", cakeDtos);
        model.addAttribute("cakeDto", cakeDto);
        return "index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("cakeDto") CakeDto cakeDto){
        cakeRestService.addNewCake(cakeDto);
        return "redirect:/";
    }
}
