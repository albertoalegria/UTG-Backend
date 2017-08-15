package com.mendel.utg.controllers;

import com.mendel.utg.models.Building;
import com.mendel.utg.repositories.BuildingRepository;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

/**
 * @author Alberto Alegria
 */

@RestController
@CrossOrigin("http://localhost:4200")
public class BuildingController {
    @Autowired
    private BuildingRepository buildingRepository;

    private Log log = LogFactory.getLog(BuildingController.class);
    private static final String PATH = "src/main/resources/images/buildings/";

    @GetMapping("buildings")
    public ResponseEntity<List<Building>> getAll() {
        log.info("Retrieving all the buildings");
        return new ResponseEntity<>(buildingRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("building")
    public ResponseEntity<Void> create(@Validated @RequestBody Building building) {
        log.info("Created building with name " + building.getName());
        buildingRepository.save(building);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("building")
    public ResponseEntity<Building> get(@RequestParam long id) {
        if (exists(id)) {
            log.info("Retrieving building with id " + id);
            return new ResponseEntity<>(buildingRepository.findOne(id), HttpStatus.OK);
        } else {
            log.error("Cannot retrieve building with id " + id + ". Building does not exist!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("building")
    public ResponseEntity<Void> update(@Validated @RequestBody Building building) {
        log.info("Saved building with name " + building.getName());
        //building.setImgPath(currentImageName);
        buildingRepository.save(building);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("building")
    public ResponseEntity<Void> delete(@RequestParam long id) {
        if (exists(id)) {
            log.info("Deleted building with id " + id);
            buildingRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.error("Cannot delete building with id " + id + ". Building does not exist!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "buildings/upload")
    public ResponseEntity<Void> uploadImage(@RequestParam MultipartFile file) {
        log.info("Uploading building image to server...");

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

    @GetMapping(value = "buildings/download", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> getImage(@RequestParam String name) {
        log.info("Retrieving building image " + name + " from server");

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
        return buildingRepository.findAll().contains(buildingRepository.findOne(id));
    }

}
