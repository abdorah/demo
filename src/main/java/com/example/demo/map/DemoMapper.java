package com.example.demo.map;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import com.example.demo.domain.DemoDTO;
import com.example.demo.model.Demo;

@Mapper(componentModel = "spring")
public interface DemoMapper extends Converter<Demo, DemoDTO> {

    DemoDTO convert(Demo demo);

    @InheritInverseConfiguration
    Demo revert(DemoDTO demoDTO);
}