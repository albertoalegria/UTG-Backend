package com.mendel.utg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mendel.utg.utils.enums.Shift;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Alberto Alegria
 */

@Entity(name = "MNDL_GROUPS")
@Table(name = "MNDL_GROUPS")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID")
    private Long id;

    @NotNull
    @Column(name = "GROUP_SHIFT")
    private Shift shift;

    @NotNull
    @Min(0)
    @Max(12)
    @Column(name = "GROUP_SEMESTER")
    private Integer semester;

    @ManyToOne
    @JoinColumn(name = "CURRICULUM_ID", nullable = false)
    private Curriculum curriculum;

    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Course> courses;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Timetable timetable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
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

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }



    @Override
    public boolean equals(Object obj) {
        return obj != null &&
                obj instanceof Group &&
                shift.equals(((Group) obj).getShift()) &&
                semester.equals(((Group) obj).getSemester());
    }
}
