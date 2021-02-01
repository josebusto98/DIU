package com.tutorials.tutorials.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document( collection = "Tutorials" )
public class TutorialsVO {

    @Id
    private String id;
    private String titulo;
    private String descripcion;
    private boolean publicado;

}
