package com.mendel.utg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mendel.utg.utils.enums.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Alberto Alegria
 */

@Entity(name = "MNDL_COURSES")
@Table(name = "MNDL_COURSES")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COURSE_ID")
    private Long id;

    @NotNull
    @Min(1)
    @Max(100)
    @Column(name = "COURSE_SIZE")
    private Integer size;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "TEACHER_ID")
    private Teacher teacher;

    //@ElementCollection
    //@CollectionTable(name = "COURSE_POSSIBLE_CLASSROOMS", joinColumns = @JoinColumn(name = "COURSE_ID"))
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "course_classrooms", joinColumns = @JoinColumn(name = "COURSE_ID"),
            inverseJoinColumns = @JoinColumn(name = "CLASSROOM_ID"))
    private List<Classroom> classrooms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> possibleClassrooms) {
        this.classrooms = possibleClassrooms;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null &&
                obj instanceof Course &&
                size.equals(((Course) obj).getSize()) &&
                group.equals(((Course) obj).getGroup()) &&
                subject.equals(((Course) obj).getSubject());
    }
}
