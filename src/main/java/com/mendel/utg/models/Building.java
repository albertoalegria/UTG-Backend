package com.mendel.utg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Alberto Alegria
 */

@Entity(name = "MNDL_BUILDINGS")
@Table(name = "MNDL_BUILDINGS")
public class Building {
    @Id
    @Column(name = "BUILDING_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 15)
    @Column(name = "BUILDING_NAME")
    private String name;


    @Column(name = "BUILDING_IMAGE_PATH")
    private String imgPath;

    @JsonIgnore
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<Classroom> classrooms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null &&
                obj instanceof Building &&
                name.equals(((Building) obj).getName());
    }
}
