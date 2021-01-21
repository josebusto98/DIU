package com.tutorials.tutorials.service.converter;

import com.tutorials.tutorials.model.TutorialsVO;
import com.tutorials.tutorials.model.dto.TutorialsDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TutorialsConverterToVO implements Converter<TutorialsDTO, TutorialsVO> {

    @Override
    public TutorialsVO convert(TutorialsDTO tutorialsDTO) {

        return TutorialsVO.builder()
                .titulo( tutorialsDTO.getTitulo() )
                .descripcion(tutorialsDTO.getDescripcion() )
                .publicado(tutorialsDTO.isPublicado() )
                .id( tutorialsDTO.getId() )
                .build();

    }
}
