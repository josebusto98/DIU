package com.example.demo.service;

import com.example.demo.model.CityVO;
import com.example.demo.model.dto.CityDTO;

import java.util.List;

public interface CityService {

    List <CityDTO> getAll();

    CityDTO create( final CityDTO city );

    CityDTO update( final CityDTO city );

    boolean delete( final String id );


}