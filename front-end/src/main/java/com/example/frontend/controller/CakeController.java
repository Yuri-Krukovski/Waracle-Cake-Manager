package com.example.frontend.controller;

import com.example.frontend.dto.CakeDto;
import com.example.frontend.service.CakeClientRestService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class CakeController {

    private final CakeClientRestService cakeRestService;

    public CakeController(CakeClientRestService cakeRestService) {
        this.cakeRestService = cakeRestService;
    }

    @GetMapping
    public String main(){

       List<CakeDto> dtos = cakeRestService.getAllCakes();

        return "index.jsp";
    }
}
