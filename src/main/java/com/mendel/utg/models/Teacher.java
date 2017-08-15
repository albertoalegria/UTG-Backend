package com.mendel.utg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Entity(name = "MNDL_TEACHERS")
@Table(name = "MNDL_TEACHERS")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TEACHER_ID")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 25)
    @Column(name = "TEACHER_FIRST_NAME")
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 25)
    @Column(name = "TEACHER_LAST_NAME")
    private String lastName;

    @Size(min = 1, max = 5)
    @Column(name = "TEACHER_TITLE")
    private String title;

    @Size(min = 3, max = 20)
    @Column(name = "TEACHER_STUDY_LEVEL")
    private String studyLevel;

    @Size(min = 1, max = 200)
    @Column(name = "TEACHER_PHOTO_PATH")
    private String imgPath;

    @NotNull
    @Min(1)
    @Max(50)
    @Column(name = "TEACHER_HOURS")
    private Integer hours;

    @NotNull
    @Column(name = "TEACHER_CHECK_IN")
    private Integer checkIn;

    @NotNull
    @Column(name = "TEACHER_CHECK_OUT")
    private Integer checkOut;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Course> courses;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Timetable timetable;

    public Teacher() {}

    @JsonIgnore
    public String getName() {
        return firstName + " " + lastName;
    }

    public Course getCourse(int index) {
        return courses.get(index);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(String studyLevel) {
        this.studyLevel = studyLevel;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Integer checkIn) {
        this.checkIn = checkIn;
    }

    public Integer getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Integer checkOut) {
        this.checkOut = checkOut;
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
                obj instanceof Teacher &&
                firstName.equals(((Teacher) obj).getFirstName()) &&
                lastName.equals(((Teacher) obj).lastName) &&
                title.equals(((Teacher) obj).getTitle()) &&
                studyLevel.equals(((Teacher) obj).getStudyLevel()) &&
                hours.equals(((Teacher) obj).getHours()) &&
                checkIn.equals(((Teacher) obj).getCheckIn()) &&
                checkOut.equals(((Teacher) obj).getCheckOut());
    }
}
