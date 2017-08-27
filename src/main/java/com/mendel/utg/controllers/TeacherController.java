package com.mendel.utg.controllers;

import com.mendel.utg.models.Teacher;
import com.mendel.utg.repositories.TeacherRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static final String PATH = "src/main/resources/images/teachers/";

    @GetMapping("teachers")
    public ResponseEntity<List<Teacher>> getAll() {
        log.info("Retrieving all the teachers");
        return new ResponseEntity<>(teacherRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("teacher")
    public ResponseEntity<Void> create(@Validated @RequestBody Teacher teacher) {
        log.info("Created teacher with name " + teacher.getFirstName());
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
        log.info("Saved teacher with name " + teacher.getFirstName());
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

    @PostMapping(value = "teachers/upload")
    public ResponseEntity<Void> uploadImage(@RequestParam MultipartFile file) {
        log.info("Uploading teacher image to server...");

        Path path = Paths.get(PATH + file.getOriginalFilename());
        try {
            Files.write(path, file.getBytes());
            log.info("Saved image with name " + file.getOriginalFilename());

            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (IOException e) {
            log.error("Can't upload image to server!");
            e.printStackTrace();

            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping(value = "teachers/download", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> getImage(@RequestParam String name) {
        log.info("Retrieving teacher image " + name + " from server");

        try {
            BufferedImage image = ImageIO.read(new File(PATH + name));

            return new ResponseEntity<BufferedImage>(image, HttpStatus.OK);
        } catch (IOException e) {
            log.error("Can't retrieve image");
            e.printStackTrace();

            return new ResponseEntity<BufferedImage>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean exists(long id) {
        return teacherRepository.findAll().contains(teacherRepository.findOne(id));
    }
}
