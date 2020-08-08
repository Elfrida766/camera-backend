package com.example.kamera.service;

import com.example.kamera.model.KameraEntity;
import com.example.kamera.model.KameraGroup;
import org.springframework.stereotype.Service;

import java.util.List;
/*
Ketu shkruaj service tone ku eshte i tipit interface,metodat...
        */
@Service
public interface KameraManager {
    KameraEntity save (KameraEntity kameraEntity);
    void delete (String id);
    List<KameraEntity> getAll();
    List<KameraEntity>getAllByEmer(String emer);
    List<KameraEntity>getAllByModel(String model);
    List<KameraEntity>getAllByRezolucion(String rezolucion);
    List<KameraEntity>getAllByIp(String ip);
    List<KameraGroup>groupByRezolucion();
}
