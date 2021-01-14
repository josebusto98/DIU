package com.example.demo.service.impl;

import com.example.demo.model.CityVO;
import com.example.demo.model.dto.CityDTO;
import com.example.demo.repository.CityRepository;
import com.example.demo.service.CityService;
import com.example.demo.service.converter.CityConverterToDTO;
import com.example.demo.service.converter.CityConverterToVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityConverterToDTO cityConverterToDTO;

    @Autowired
    private CityConverterToVO cityConverterToVO;


    @Override
    public List < CityDTO > getAll() {
        return cityRepository.findAll()
                .stream()
                .map( cityConverterToDTO::convert )
                .collect( Collectors.toList());
    }

    @Override
    public CityDTO create( CityDTO city ) {
        CityVO cityVO = cityConverterToVO.convert( city );
        return cityConverterToDTO.convert( cityRepository.insert( cityVO ) );
    }

    @Override
    public CityDTO update( CityDTO city ) {
        CityVO cityVO = cityConverterToVO.convert( city );
        return cityConverterToDTO.convert( cityRepository.save( cityVO ) );
    }

    @Override
    public boolean delete( String id ) {
        try{
            cityRepository.deleteById( id );
            return Boolean.TRUE;
        } catch (Exception e){
            return Boolean.FALSE;
        }
    }
}
