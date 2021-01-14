package com.example.demo.service.converter;

import com.example.demo.model.CityVO;
import com.example.demo.model.dto.CityDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CityConverterToVO implements Converter<CityDTO, CityVO> {

    @Override
    public CityVO convert( CityDTO cityDTO ) {
        return CityVO.builder()
                .code( cityDTO.getCode() )
                .name( cityDTO.getName() )
                .id( cityDTO.getId() )
                .build();
    }
}
