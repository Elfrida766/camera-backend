package com.example.kamera.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
/*
Krijojme modelin per tabelen kamera te cilen e kemi quajtur KameraEntity
Perdorim @Data annotiation nga Lombok per te bere te mundur gjenerimin e get() dhe set();
*/
@Document(collection = "kamera")
@Data
public class KameraEntity {
    @Id
    private String id;
    @Field("emer")
    private String emer;
    @Field("model")
    private String model;
    @Field("rezolucion")
    private String rezolucion;
    @Field("ip")
    private String ip;
}
