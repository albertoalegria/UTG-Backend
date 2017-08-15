package com.mendel.utg.controllers;

import com.mendel.utg.models.Teacher;
import com.mendel.utg.repositories.TeacherRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alberto Alegria
 */

@RestController
@CrossOrigin("http://localhost:4200")
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;

    private Log log = LogFactory.getLog(TeacherController.class);

    @GetMapping("teachers")
    public ResponseEntity<List<Teacher>> getAll() {
        log.info("Retrieving all the teachers");
        return new ResponseEntity<>(teacherRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("teacher")
    public ResponseEntity<Void> create(@Validated @RequestBody Teacher teacher) {
        log.info("Created teacher with name " + teacher.getName());
        teacherRepository.save(teacher);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("teacher")
    public ResponseEntity<Teacher> get(@RequestParam long id) {
        if (exists(id)) {
            log.info("Retrieving teacher with id " + id);
            return new ResponseEntity<>(teacherRepository.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("teacher")
    public ResponseEntity<Void> update(@Validated @RequestBody Teacher teacher) {
        log.info("Saved teacher with name " + teacher.getName());
        teacherRepository.save(teacher);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("teacher")
    public ResponseEntity<Void> delete(@RequestParam long id) {
        if (exists(id)) {
            log.info("Deleted teacher with id " + id);
            teacherRepository.delete(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            log.error("Cannot find teacher with id " + id + ". Teacher does not exist!");
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean exists(long id) {
        return teacherRepository.findAll().contains(teacherRepository.findOne(id));
    }
}
