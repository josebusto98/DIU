package com.example.demo.controller.impl;

import com.example.demo.controller.CityAPI;
import com.example.demo.model.CityVO;
import com.example.demo.model.dto.CityDTO;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CityController implements CityAPI {

    @Autowired
    private CityService cityService;

    @Override
    public ResponseEntity < List <CityDTO> > getAll() {
        return ResponseEntity.ok( cityService.getAll() );
    }

    @Override
    public ResponseEntity < CityDTO > create( CityDTO cityDTO ) {
        return new ResponseEntity <>( cityService.create( cityDTO ), HttpStatus.CREATED );
    }

    @Override
    public ResponseEntity < CityDTO > update( CityDTO cityDTO ) {
        return new ResponseEntity<>( cityService.update( cityDTO ), HttpStatus.CREATED );
    }

    @Override
    public ResponseEntity < Boolean > delete( String id ) {
        return cityService.delete( id )
                ? ResponseEntity.ok( true )
                : new ResponseEntity <>( false, HttpStatus.NOT_FOUND );
    }
}
