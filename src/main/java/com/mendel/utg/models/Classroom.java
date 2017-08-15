package com.mendel.utg.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mendel.utg.utils.enums.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Alberto Alegria
 */

@Entity(name = "MNDL_CLASSROOMS")
@Table(name = "MNDL_CLASSROOMS")
public class Classroom {
    @Id
    @Column(name = "CLASSROOM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Min(1)
    @Max(100)
    @Column(name = "CLASSROOM_SIZE")
    private Integer size;

    @NotBlank
    @Size(min = 1, max = 15)
    @Column(name = "CLASSROOM_IDENTIFIER")
    private String identifier;

    @NotNull
    @Column(name = "CLASSROOM_TYPE")
    private Type type;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "BUILDING_ID", nullable = false)
    private Building building;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Timetable timetable;

    public Classroom() {}

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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
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
                obj instanceof Classroom &&
                size.equals(((Classroom) obj).getSize()) &&
                identifier.equals(((Classroom) obj).getIdentifier()) &&
                type.equals(((Classroom) obj).getType()) &&
                building.equals(((Classroom) obj).getBuilding());
    }
}
