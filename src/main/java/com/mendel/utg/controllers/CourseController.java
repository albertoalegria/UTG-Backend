package com.mendel.utg.controllers;

import com.mendel.utg.models.Course;
import com.mendel.utg.repositories.CourseRepository;
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
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    private Log log = LogFactory.getLog(CourseController.class);

    @GetMapping("courses")
    public ResponseEntity<List<Course>> getAll() {
        log.info("Retrieving all the courses");
        return new ResponseEntity<List<Course>>(courseRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("course")
    public ResponseEntity<Void> create(@Validated @RequestBody Course course) {
        log.info("Created course for subject " + course.getSubject().getName());
        courseRepository.save(course);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("course")
    public ResponseEntity<Course> get(@RequestParam long id) {
        if (exists(id)) {
            log.info("Retrieving course with id " + id);
            return new ResponseEntity<Course>(courseRepository.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<Course>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("course")
    public ResponseEntity<Void> update(@Validated @RequestBody Course course) {
        log.info("Saved course for subject " + course.getSubject().getName());
        courseRepository.save(course);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("course")
    public ResponseEntity<Void> delete(@RequestParam long id) {
        if (exists(id)) {
            log.info("Deleted course with id " + id);
            courseRepository.delete(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            log.error("Cannot delete course with id " + id + ". Course does not exist!");
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean exists(long id) {
        return courseRepository.findAll().contains(courseRepository.findOne(id));
    }
}
