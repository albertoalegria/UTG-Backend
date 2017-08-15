package com.mendel.utg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Alberto Alegria
 */

@Entity(name = "MNDL_CURRICULUM")
@Table(name = "MNDL_CURRICULUM")
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CURRICULUM_ID")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "CURRICULUM_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "CAREER_ID", nullable = false)
    private Career career;

    @JsonIgnore
    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL)
    private List<Subject> subjects;

    @JsonIgnore
    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL)
    private List<Group> groups; //FIXME

    public Curriculum() {}

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

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Curriculum && name.equals(((Curriculum) obj).getName()) && career.equals(((Curriculum) obj).getCareer());

    }
}
