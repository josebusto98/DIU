package com.tutorials.tutorials.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TutorialsDTO {

    private String id;
    private String titulo;
    private String descripcion;
    private boolean publicado;

}
