package com.example.kamera.controller;

import com.example.kamera.model.KameraEntity;
import com.example.kamera.model.KameraGroup;
import com.example.kamera.service.KameraManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*

Vendos @Autowired Per Managerin tone i cili eshte KameraManager pasi do ta therrasim per metodat dhe do te na duhet ta inicializojme
 */
@RestController
@RequestMapping(value = "/kamera")
@CrossOrigin("http://localhost:4200")
public class KameraController {
    @Autowired
    private KameraManager kameraManager;

    /*

    ne rastin e metodes post ne marr tipin e te dhenave ResponseEntity qe eshte e rest api vete dhe gjithashtu ne bady kesaj thirrje ne i kalojme ne objekt json i cili eshte
    KameraEntity. Bejme ne fillim kontrollin nqs id qe po i shtohet te mos jete null dhe me pas therasim metoden e managerit per te bere save
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<KameraEntity>saveKamera(@RequestBody KameraEntity kameraEntity){
        if(kameraEntity.getId()!=null){
            return ResponseEntity.badRequest().build();
        }else {
            KameraEntity response = kameraManager.save(kameraEntity);
            return ResponseEntity.ok(response);
        }
    }
    /*
    E njejta gje dhe per metoden getALl por ketu nuk kaloj asnje parameter ose body pasi do ti marr te gjitha
    tipi i metodes eshte get dhe url localhost:8080/kamera/getAll
    therras metoden e managerit per te marr nje list me kamera
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<KameraEntity>> getAllKamera() {
        List<KameraEntity> kameraEntities = kameraManager.getAll();
        return ResponseEntity.ok(kameraEntities);
    }
    /*
     kontrollin per id e ekemi bere tek managerImp;
    ketu thirrjes i kalojme nje parameter e cila eshte id dhe prandaj perdoret @Path Variabel
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id){
        kameraManager.delete(id);
    }
/*
Dhe gjithashtu per te gjitha metodat e tjera qe jane get me parameter vendosim nje parameter ne url te cilin e marr me @PathVariabel e me pas ja kalojme metodes se manager
 */
    @RequestMapping(value = "/getByEmer/{emer}", method = RequestMethod.GET)
    public ResponseEntity<List<KameraEntity>> getByEmer(@PathVariable String emer){
        List<KameraEntity> kameraEntities= kameraManager.getAllByEmer(emer);
        return ResponseEntity.ok(kameraEntities);
    }

    @RequestMapping(value = "/getByModel/{model}", method = RequestMethod.GET)
    public ResponseEntity<List<KameraEntity>> getByModel(@PathVariable String model){
        List<KameraEntity> kameraEntities= kameraManager.getAllByModel(model);
        return ResponseEntity.ok(kameraEntities);

    }
    @RequestMapping(value = "/getByRezolucion/{rezolucion}", method = RequestMethod.GET)
    public ResponseEntity<List<KameraEntity>> getByRezolucion(@PathVariable String rezolucion){
        List<KameraEntity> kameraEntities= kameraManager.getAllByRezolucion(rezolucion);
        return ResponseEntity.ok(kameraEntities);

    }
    @RequestMapping(value = "/getByIp/{ip}", method = RequestMethod.GET)
    public ResponseEntity<List<KameraEntity>> getByIp(@PathVariable String ip){
        List<KameraEntity> kameraEntities= kameraManager.getAllByIp(ip);
        return ResponseEntity.ok(kameraEntities);
    }
    @RequestMapping(value = "/groupByRezolucion", method = RequestMethod.GET)
    public ResponseEntity<List<KameraGroup>>groupByRezolucion(){
        List<KameraGroup>kamera=kameraManager.groupByRezolucion();
        return ResponseEntity.ok(kamera);
    }
}
