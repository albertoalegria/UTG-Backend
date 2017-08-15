package com.mendel.utg.controllers;

import com.mendel.utg.models.Career;
import com.mendel.utg.repositories.CareerRepository;
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
import java.awt.*;
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
public class CareerController {
    @Autowired
    private CareerRepository careerRepository;

    private Log log = LogFactory.getLog(CareerController.class);
    private static final String PATH = "src/main/resources/images/careers/";

    @GetMapping("careers")
    public ResponseEntity<List<Career>> getAll() {
        log.info("Retrieving all the careers");
        return new ResponseEntity<>(careerRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("career")
    public ResponseEntity<Void> create(@Validated @RequestBody Career career) {
        log.info("Created career with name " + career.getName());
        careerRepository.save(career);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("career")
    public ResponseEntity<Career> get(@RequestParam long id) {
        if (exists(id)) {
            log.info("Retrieving career with id " + id);
            return new ResponseEntity<>(careerRepository.findOne(id), HttpStatus.OK);
        } else {
            log.error("Cannot retrieve career with id " + id + ". Career does not exist!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("career")
    public ResponseEntity<Void> update(@Validated @RequestBody Career career) {
        log.info("Saved career with name " + career.getName());
        careerRepository.save(career);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("career")
    public ResponseEntity<Void> deleteCareer(@RequestParam long id) {
        if (exists(id)) {
            log.info("Deleted career with id " + id);
            careerRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.error("Cannot delete career with id " + id + ". Career does not exist!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "careers/upload")
    public ResponseEntity<Void> uploadImage(@RequestParam MultipartFile file) {
        log.info("Uploading career image to server...");

        Path path = Paths.get(PATH + file.getOriginalFilename());

        try {
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            log.error("Can't upload image to server!");
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        log.info("Sved image with name " + file.getOriginalFilename());

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping(value = "careers/download", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> getImage(@RequestParam String name) {
        log.info("Retrieving building image " + name + " from server");

        BufferedImage image;

        try {
            image = ImageIO.read(new File(PATH + name));
            return new ResponseEntity<BufferedImage>(image, HttpStatus.OK);
        } catch (IOException e) {
            log.error("Can't retrieve image");
            e.printStackTrace();

            return new ResponseEntity<BufferedImage>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "careers/download/thumbs", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> getThumbsImage(@RequestParam String name) {
        log.info("Retrieving thumbs image " + name + " from server");

        BufferedImage image;

        try {
            image = ImageIO.read(new File(PATH + name));
            image = getResized(image, 80, 80);
            return new ResponseEntity<BufferedImage>(image, HttpStatus.OK);
        } catch (IOException e) {
            log.error("Can't retrieve image");
            e.printStackTrace();

            return new ResponseEntity<BufferedImage>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean exists(long id) {
        return careerRepository.findAll().contains(careerRepository.findOne(id));
    }

    private BufferedImage getResized(BufferedImage image, int width, int height) {
        Image img = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage newImg = new BufferedImage(width, height, image.getType());

        return newImg;
    }
}
