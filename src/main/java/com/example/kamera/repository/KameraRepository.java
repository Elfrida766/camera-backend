package com.example.kamera.repository;

import com.example.kamera.model.KameraEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
/*

Perdorim MongoRepository per te lidhur model me databasen ,Kamera Entity eshte emri i Modelit
perdorim funksionet e mongorepository per te marr datat qe na duhen
 */
@Repository
@CrossOrigin("http://localhost:4200")
public interface KameraRepository extends MongoRepository<KameraEntity,String> {
    //me kete funksion marrim nje list me kamera sipas emrit qe ne veme ne kusht
    List<KameraEntity> findAllByEmer(String emer);
    List<KameraEntity> findAllByModel(String model);
    List<KameraEntity> findAllByIp(String ip);
    List<KameraEntity> findAllByRezolucion(String rezolucion);
    //Eshte e tipit optional sepse mund te kete nje ose asnje te dhene prandaj nuk eshte list
    Optional<KameraEntity> findById(String s);
}
