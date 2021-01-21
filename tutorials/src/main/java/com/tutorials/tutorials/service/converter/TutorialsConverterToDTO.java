package com.tutorials.tutorials.service.converter;

import com.tutorials.tutorials.model.TutorialsVO;
import com.tutorials.tutorials.model.dto.TutorialsDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TutorialsConverterToDTO implements Converter<TutorialsVO, TutorialsDTO> {

    @Override
    public TutorialsDTO convert(TutorialsVO tutorialsVO) {

        return TutorialsDTO.builder()
                .titulo( tutorialsVO.getTitulo() )
                .descripcion(tutorialsVO.getDescripcion() )
                .publicado(tutorialsVO.isPublicado() )
                .id( tutorialsVO.getId() )
                .build();

    }
}
