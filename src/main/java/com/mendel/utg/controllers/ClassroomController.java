package com.mendel.utg.controllers;

import com.mendel.utg.models.Classroom;
import com.mendel.utg.repositories.BuildingRepository;
import com.mendel.utg.repositories.ClassroomRepository;
import com.mendel.utg.utils.enums.Type;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping("classrooms/type")
    public ResponseEntity<List<Classroom>> getByType(@RequestParam String type) {
        log.info("Retrieving all classrooms for type " + type);
        List<Classroom> classrooms;
        if (type.equals(Type.CLASSROOM.getName())) {
            classrooms = classroomRepository.findByType(Type.CLASSROOM);
        } else {
            classrooms = classroomRepository.findByType(Type.LABORATORY);
        }
        return new ResponseEntity<List<Classroom>>(classrooms, HttpStatus.OK);
    }

    /*GetByBuildingAndTypeExcept*/

    @GetMapping("classrooms/buildingandtype")
    public ResponseEntity<List<Classroom>> getByBuildingAndType(@RequestParam Map<String, String> params) {
        long id = Long.valueOf(params.get("id"));
        String t = params.get("type");
        Type type;// = params.get("type").equals(Type.CLASSROOM.getName()) ? Type.CLASSROOM : Type.LABORATORY ? Type.LABORATORY ;

        if (t.equals(Type.CLASSROOM.getName())) {
            type = Type.CLASSROOM;
        } else if (t.equals(Type.LABORATORY.getName())) {
            type = Type.LABORATORY;
        } else {
            log.error("Cannot retrieve classrooms. Type " + t + " is not a valid type!");
            return new ResponseEntity<List<Classroom>>(HttpStatus.BAD_REQUEST); //TODO create custom handler to manage this
        }

        log.info("Retrieving classrooms for building with id " + id + " and type " + type);

        //List<Classroom> classrooms = buildingRepository.findOne(id).getClassrooms().stream().filter(classroom -> classroom.getType().equals(type)).collect(Collectors.toList());

        return new ResponseEntity<List<Classroom>>(buildingRepository.findOne(id).getClassrooms().stream()
                .filter(classroom -> classroom.getType().equals(type)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("classrooms/buildingandtype2")
    public ResponseEntity<List<Classroom>> getByBuildingAndType2(@RequestParam Map<String, String> params) {
        long id = Long.valueOf(params.get("id"));
        String t = params.get("type");
        Type type;// = params.get("type").equals(Type.CLASSROOM.getName()) ? Type.CLASSROOM : Type.LABORATORY ? Type.LABORATORY ;

        if (t.equals(Type.CLASSROOM.getName())) {
            type = Type.LABORATORY;
        } else if (t.equals(Type.LABORATORY.getName())) {
            type = Type.CLASSROOM;
        } else {
            log.error("Cannot retrieve classrooms. Type " + t + " is not a valid type!");
            return new ResponseEntity<List<Classroom>>(HttpStatus.BAD_REQUEST); //TODO create custom handler to manage this
        }

        log.info("Retrieving classrooms for building with id " + id + " and type " + type);

        //List<Classroom> classrooms = buildingRepository.findOne(id).getClassrooms().stream().filter(classroom -> classroom.getType().equals(type)).collect(Collectors.toList());

        return new ResponseEntity<List<Classroom>>(buildingRepository.findOne(id).getClassrooms().stream()
                .filter(classroom -> classroom.getType().equals(type)).collect(Collectors.toList()), HttpStatus.OK);
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
