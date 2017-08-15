package com.mendel.utg.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alberto Alegria
 */
@Entity(name = "MNDL_TIMES_HOLDER")
@Table(name = "MNDL_TIMES_HOLDER")
public class TimesHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TIMES_HOLDER_ID")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "TIMES_HOLDER_TIMES", joinColumns = @JoinColumn(name = "TIMES_HOLDER_ID"))
    @AttributeOverrides({
            @AttributeOverride(name = "hour", column = @Column(name = "TIMES_HOLDER_TIME"))
    })
    private List<Time> times;

    public TimesHolder() {
        times = new ArrayList<>();

    }

    public void add(Time time) {
        times.add(time);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Time> getTimes() {
        return times;
    }

    public void setTimes(List<Time> times) {
        this.times = times;
    }
}
