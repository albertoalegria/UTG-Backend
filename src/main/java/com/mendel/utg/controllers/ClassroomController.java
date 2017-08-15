package com.mendel.utg.controllers;

import com.mendel.utg.models.Classroom;
import com.mendel.utg.repositories.BuildingRepository;
import com.mendel.utg.repositories.ClassroomRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Alberto Alegria
 */
@RestController
@CrossOrigin("http://localhost:4200")
public class ClassroomController {
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private BuildingRepository buildingRepository;

    private Log log = LogFactory.getLog(ClassroomController.class);

    @GetMapping("classrooms")
    public ResponseEntity<List<Classroom>> getAll() {
        log.info("Retrieving all the classrooms");
        return new ResponseEntity<>(classroomRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("classrooms/building")
    public ResponseEntity<List<Classroom>> getByBuilding(@RequestParam long id) {
        if (!buildingRepository.exists(id)) {
            log.info("Can not retrieve classrooms for building with id " + id + ". Building does not exists!");
            return new ResponseEntity<List<Classroom>>(HttpStatus.BAD_REQUEST);
        } else {
            log.info("Retrieving classrooms for building with id " + id);
            return new ResponseEntity<List<Classroom>>(buildingRepository.findOne(id).getClassrooms(), HttpStatus.OK);
        }
    }

    @PostMapping("classroom")
    public ResponseEntity<Void> create(@Validated @RequestBody Classroom classroom) {
        log.info("Created classroom for the building " + classroom.getBuilding().getName() + " with identifier " + classroom.getIdentifier());
        classroomRepository.save(classroom);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("classroom")
    public ResponseEntity<Classroom> get(@RequestParam long id) {
        if (exists(id)) {
            log.info("Retrieving classroom with id " + id);
            return new ResponseEntity<>(classroomRepository.findOne(id), HttpStatus.OK);
        } else {
            log.error("Cannot retrieve classroom with id " + id + ". Classroom does not exist!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("classroom")
    public ResponseEntity<Void> update(@Valid @RequestBody Classroom classroom) {
        log.info("Saved classroom with identifier " + classroom.getIdentifier());
        classroomRepository.save(classroom);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("classroom")
    public ResponseEntity<Void> delete(@RequestParam long id) {
        if (exists(id)) {
            log.info("Deleted classroom with id " + id);
            classroomRepository.delete(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            log.error("Cannot delete classroom with id " + id + ". Classroom does not exist!");
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean exists(long id) {
        return classroomRepository.findAll().contains(classroomRepository.findOne(id));
    }
}
