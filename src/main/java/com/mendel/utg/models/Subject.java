package com.mendel.utg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mendel.utg.utils.enums.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Alberto Alegria
 */

@Entity(name = "MNDL_SUBJECTS")
@Table(name = "MNDL_SUBJECTS")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SUBJECT_ID")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "SUBJECT_NAME")
    private String name;

    @NotBlank
    @Size(min = 1, max = 10)
    @Column(name = "SUBJECT_CODE")
    private String code;

    @NotNull
    @Min(0)
    @Max(12)
    @Column(name = "SUBJECT_SEMESTER")
    private Integer semester;

    @NotNull
    @Column(name = "SUBJECT_TYPE")
    private Type type;

    @NotNull
    @Min(1)
    @Max(20)
    @Column(name = "SUBJECT_HOURS")
    private Integer hours;

    @Min(0)
    @Max(10)
    @Column(name = "SUBJECT_LABORATORY_HOURS")
    private Integer laboratoryHours;

    @ManyToOne
    @JoinColumn(name = "CURRICULUM_ID", nullable = false)
    private Curriculum curriculum;

    @JsonIgnore
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Course> courses;

    public Subject() {
        laboratoryHours = 0;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getLaboratoryHours() {
        return laboratoryHours;
    }

    public void setLaboratoryHours(Integer laboratoryHours) {
        this.laboratoryHours = laboratoryHours;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null &&
                obj instanceof Subject &&
                name.equals(((Subject) obj).getName()) &&
                code.equals(((Subject) obj).code) &&
                semester.equals(((Subject) obj).getSemester()) &&
                type.equals(((Subject) obj).getType()) &&
                hours.equals(((Subject) obj).getHours()) &&
                laboratoryHours.equals(((Subject) obj).getLaboratoryHours());

    }
}
