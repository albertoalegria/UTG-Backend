package com.mendel.utg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Alberto Alegria
 */

@Entity(name = "MNDL_CAREERS")
@Table(name = "MNDL_CAREERS")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CAREER_ID")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "CAREER_NAME")
    private String name;

    @NotBlank
    @Size(min = 1, max = 10)
    @Column(name = "CAREER_ACRONYM")
    private String acronym;

    @Column(name = "CAREER_IMAGE_PATH")
    private String imgPath;

    @JsonIgnore
    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL)
    private List<Curriculum> curricula;

    public Career() {}

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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public List<Curriculum> getCurricula() {
        return curricula;
    }

    public void setCurricula(List<Curriculum> curricula) {
        this.curricula = curricula;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null &&
                obj instanceof Career &&
                name.equals(((Career) obj).getName()) &&
                acronym.equals(((Career) obj).getAcronym());

    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                "name=" + name +
                "acronym" + acronym +
                "}";
    }
}
