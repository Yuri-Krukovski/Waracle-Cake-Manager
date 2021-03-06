package com.example.backend.service;

import com.example.backend.domain.dto.CakeDto;
import com.example.backend.domain.model.Cake;
import com.example.backend.repository.CakeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CakeServiceImpl {

    private final CakeRepository cakeRepository;

    public CakeServiceImpl(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    public List<Cake> getAll() {
        return cakeRepository.findAll();
    }

    public Cake save(CakeDto cakeDto) {
         return cakeRepository.save(new Cake(
                cakeDto.getTitle(),
                cakeDto.getDesc(),
                cakeDto.getImage()));
    }
}
