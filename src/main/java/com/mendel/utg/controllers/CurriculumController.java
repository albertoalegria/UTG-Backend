package com.mendel.utg.controllers;

import com.mendel.utg.models.Curriculum;
import com.mendel.utg.repositories.CareerRepository;
import com.mendel.utg.repositories.CurriculumRepository;
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
public class CurriculumController {
    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private CareerRepository careerRepository;

    private Log log = LogFactory.getLog(CurriculumController.class);

    @GetMapping("curricula")
    public ResponseEntity<List<Curriculum>> getAll() {
        log.info("Retrieving all the curricula");
        return new ResponseEntity<>(curriculumRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("curricula/career")
    public ResponseEntity<List<Curriculum>> getByCareer(@RequestParam long id) {
        if (!careerRepository.exists(id)) {
            log.info("Failed to obtain curricula for career with id " + id + ". Career does not exists!");
            return new ResponseEntity<List<Curriculum>>(HttpStatus.BAD_REQUEST);
        } else {
            log.info("Retrieving curricula for career with id " + id);
            return new ResponseEntity<List<Curriculum>>(careerRepository.findOne(id).getCurricula(), HttpStatus.OK);
        }
    }

    @PostMapping("curriculum")
    public ResponseEntity<Void> create(@Validated @RequestBody Curriculum curriculum) {
        log.info("Created curriculum for career " + curriculum.getCareer().getName() + " with name " + curriculum.getName());
        curriculumRepository.save(curriculum);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("curriculum")
    public ResponseEntity<Curriculum> get(@RequestParam long id) {
        if (exists(id)) {
            log.info("Retrieving curriculum with id " + id);
            return new ResponseEntity<>(curriculumRepository.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("curriculum")
    public ResponseEntity<Void> update(@Validated @RequestBody Curriculum curriculum) {
        log.info("Saved curriculum with name " + curriculum.getName());
        curriculumRepository.save(curriculum);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("curriculum")
    public ResponseEntity<Void> delete(@RequestParam long id) {
        if (exists(id)) {
            log.info("Deleted curriculum with id " + id);
            curriculumRepository.delete(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            log.error("Cannot find curriculum with id " + id + ". Curriculum does not exist!");
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean exists(long id) {
        return curriculumRepository.findAll().contains(curriculumRepository.findOne(id));
    }
}
