package com.mendel.utg.controllers;

import com.mendel.utg.models.Course;
import com.mendel.utg.models.Curriculum;
import com.mendel.utg.models.Group;
import com.mendel.utg.repositories.CareerRepository;
import com.mendel.utg.repositories.CurriculumRepository;
import com.mendel.utg.repositories.GroupRepository;
import com.mendel.utg.utils.enums.Shift;
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
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CurriculumRepository curriculumRepository;

    private Log log = LogFactory.getLog(GroupController.class);

    @GetMapping("groups")
    public ResponseEntity<List<Group>> getAll() {
        log.info("Retrieving all the groups");
        return new ResponseEntity<>(groupRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("groups")
    public ResponseEntity<Void> create(@Validated @RequestBody List<Group> groups) {
        for (Group group : groups) {
            log.info("Created group for career " + group.getCurriculum().getCareer().getName());
            groupRepository.save(group);
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("groups/curriculum")
    public ResponseEntity<List<Group>> getByCurriculum(@RequestParam long id) {
        if (!curriculumRepository.exists(id)) {
            log.info("Failed to obtain groups for curriculum with id " + id + ". Career does not exists!");
            return new ResponseEntity<List<Group>>(HttpStatus.BAD_REQUEST);
        } else {
            log.info("Retrieving groups for curriculum with id " + id);
            return new ResponseEntity<List<Group>>(curriculumRepository.findOne(id).getGroups(), HttpStatus.OK);
        }
    }

    @PostMapping("group")
    public ResponseEntity<Void> create(@Validated @RequestBody Group group) {
        log.info("Created group for career " + group.getCurriculum().getCareer().getName());
        groupRepository.save(group);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("group")
    public ResponseEntity<Group> get(@RequestParam long id) {
        if (exists(id)) {
            log.info("Retrieving group with id " + id);
            return new ResponseEntity<>(groupRepository.findOne(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("group")
    public ResponseEntity<Void> update(@Validated @RequestBody Group group) {
        log.info("Saved group for career " + group.getCurriculum().getCareer().getName());
        groupRepository.save(group);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("group")
    public ResponseEntity<Void> delete(@RequestParam long id) {
        if (exists(id)) {
            log.info("Deleted group with id " + id);
            groupRepository.delete(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            log.error("Cannot find group with id " + id + ". Group does not exist!");
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean exists(long id) {
        return groupRepository.findAll().contains(groupRepository.findOne(id));
    }
}
