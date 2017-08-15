package com.mendel.utg.controllers;

import com.mendel.utg.models.Group;
import com.mendel.utg.models.Subject;
import com.mendel.utg.repositories.CareerRepository;
import com.mendel.utg.repositories.CurriculumRepository;
import com.mendel.utg.repositories.SubjectRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alberto Alegria
 */
@RestController
@CrossOrigin("http://localhost:4200")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CurriculumRepository curriculumRepository;

    private Log log = LogFactory.getLog(SubjectController.class);

    @GetMapping("subjects")
    public ResponseEntity<List<Subject>> getAll() {
        log.info("Retrieving all the subjects");
        return new ResponseEntity<>(subjectRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("subjects/curriculumandsemester")
    public ResponseEntity<List<Subject>> getByCurriculumAndSemester(@RequestParam Map<String, String> params) {
        long id = Long.valueOf(params.get("id"));
        int semester = Integer.parseInt(params.get("semester"));

        if (!curriculumRepository.exists(id)) {
            log.info("Failed to obtain subjects for curriculum with id " + id + ". Curriculum does not exists");
            return new ResponseEntity<List<Subject>>(HttpStatus.BAD_REQUEST);
        } else {
            log.info("Retrieving subjects for curriculum with id " + id);
            List<Subject> subjects = curriculumRepository.findOne(id).getSubjects().stream()
                    .filter(subject -> subject.getSemester().equals(semester)).collect(Collectors.toList());

            return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
        }
    }

    @PostMapping("subject")
    public ResponseEntity<Void> create(@Validated @RequestBody Subject subject) {
        log.info("Created subject with name " + subject.getName());
        subjectRepository.save(subject);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("subject")
    public ResponseEntity<Subject> get(@RequestParam long id) {
        if (exists(id)) {
            log.info("Retrieving subject with id " + id);
            return new ResponseEntity<>(subjectRepository.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("subject")
    public ResponseEntity<Void> update(@Validated @RequestBody Subject subject) {
        log.info("Saved subject with name " + subject.getName());
        subjectRepository.save(subject);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("subject")
    public ResponseEntity<Void> delete(@RequestParam long id) {
        if (exists(id)) {
            log.info("Deleted subject with id " + id);
            subjectRepository.delete(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            log.error("Cannot find subject with id " + id + ". Subject does not exist!");
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean exists(long id) {
        return subjectRepository.findAll().contains(subjectRepository.findOne(id));
    }
}
