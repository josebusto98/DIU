package com.example.demo.model;

import lombok.*;
//argumentos
import org.springframework.data.annotation.Id; //identificador del
                                                //dato, coherente con el de mongodb
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document( collection = "City" )
public class CityVO {

    @Id
    private String id;
    private String code;
    private String name;

}
