package com.example.kamera.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
/*
Ndertoj nje model i cili do te na sherbej per te grupur kamerat dhe ne te mund ti shfaqim tek grafiku
i vendos nje id ku me vone id do te jete emri i grupit dhe ne rastin tone do te jete rezolucioni pasi ne do ti grupojme ne baze te rezolucionit kamerat
gjithashtu do te permbaj nje list me te gjitha kamerat qe kane kete rezolucion dhe nje fushe total ku do te tregoj sa kamera kemi gjithesej me kete rezolucion
 */
@Data
public class KameraGroup {
    @Id
    private String id;
    @Field
    private List <KameraEntity> kamera;
    @Field
    private int total;
}
