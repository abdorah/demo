package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.example.demo.domain.DemoDTO;
import com.example.demo.repository.DemoRepository;

@Service
public class DemoService {

    @Autowired
    private DemoRepository demoRepository;
    
    @Autowired
    private ConversionService conversionService;

    public DemoDTO getDemoById(UUID id) throws Exception {
        if(demoRepository.findById(id).isPresent())
        return conversionService.convert(demoRepository.findById(id).get(),DemoDTO.class);
        throw new Exception(String.format("Demo not found with the spicified Id: %s wasn't found.", id.toString()));
    }
}
