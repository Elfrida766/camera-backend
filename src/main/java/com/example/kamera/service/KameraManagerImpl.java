package com.example.kamera.service;

import com.example.kamera.model.KameraEntity;
import com.example.kamera.model.KameraGroup;
import com.example.kamera.repository.KameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/*
Ketu Bej implementimin e metodave te shkruajtura ne interface dhe per kete arsye implementoj KameraManager
Perdoret annotation @Autowired per te inicializuar repository dhe MongoTamplate
Perdoret @Override per te gjitha metodat qe implementohen nga interface
 */
@Component
public class KameraManagerImpl implements KameraManager {
    @Autowired
    private KameraRepository kameraRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public KameraEntity save(KameraEntity kameraEntity) {
        //Ne kete metode therrasim metoden save te repository per te ruajtur nje kamer
        //per te bere save ne db
        KameraEntity kamer = kameraRepository.save(kameraEntity);
        return kamer;
    }

    @Override
    public void delete(String id) {
        //Per te fshire te dhenat si fillim duhet ta gjejme kush e dhene eshte ne database prandaj perdorim findById dhe nqs nuk e gjen na kthen nje null pointer error
        Optional<KameraEntity> kameraEntity = Optional.ofNullable(kameraRepository.findById(id).orElseThrow(NullPointerException::new));
        if (kameraEntity.isPresent()) {
            kameraRepository.delete(kameraEntity.get());
        }
    }

    @Override
    public List<KameraEntity> getAll() {
        //per te marr te gjitha te dhenat perdorim metoden e gatshme te repository findAll()
        return kameraRepository.findAll();
    }

    @Override
    public List<KameraEntity> getAllByEmer(String emer) {
        //Gjithashtu per te marr nje list me kamera ne baze te specifikimit qe duam perdorim metoden findAllByEmer
        return kameraRepository.findAllByEmer(emer);
    }

    @Override
    public List<KameraEntity> getAllByModel(String model) {

        return kameraRepository.findAllByModel(model);
    }

    @Override
    public List<KameraEntity> getAllByRezolucion(String rezolucion) {

        return kameraRepository.findAllByRezolucion(rezolucion);
    }

    @Override
    public List<KameraEntity> getAllByIp(String ip) {

        return kameraRepository.findAllByIp(ip);
    }

    /*
    Kete metore e perdor per grafikun ku ne folderin model kemi ndertuar entitetit kameraGroup
    perdorim mongo aggregation dhe mongo Tamplate per te bere grupim ne mongo
     */
    @Override
    public List<KameraGroup> groupByRezolucion() {
        /*
        Si fillim grupojme kamerat ne baze te fushes rezolucion , i vendos ne nje list kamera dhe i numerojme me .count() ku me pas shumen se sa kamera jane i vendosim tek total
        pasi kemi bere grupimin krijojme nje aggregation te ri dhe me pas tek aggragation result vendosim menyren  si do na kthehet rezultati
         */
        GroupOperation group = Aggregation.group(Aggregation.fields("rezolucion"))
                .push("$$ROOT").as("kamera").count().as("total");
        Aggregation aggregation = Aggregation.newAggregation(group);
        AggregationResults<KameraGroup> groupResult=mongoTemplate.aggregate(aggregation,KameraEntity.class,KameraGroup.class);
        List<KameraGroup>result=groupResult.getMappedResults();
        return result;
    }
}
