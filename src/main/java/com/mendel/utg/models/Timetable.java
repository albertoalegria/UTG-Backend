package com.mendel.utg.models;

import com.mendel.utg.models.Course;
import com.mendel.utg.models.Time;
import com.mendel.utg.utils.Utils;
import com.mendel.utg.utils.enums.Shift;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alberto Alegria
 */

@Entity(name = "MNDL_TIMETABLE")
@Table(name = "MNDL_TIMETABLE")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "TIMETABLE_ID")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "TIMETABLE_SCHEDULE", joinColumns = @JoinColumn(name = "TIMETABLE_ID"))
    @Column(name = "TIMETABLE_SCHEDULE")
    private Map<Course, TimesHolder> schedule;

    @ElementCollection
    @CollectionTable(name = "TIMETABLE_TIMES", joinColumns = @JoinColumn(name = "TIMETABLE_ID"))
    @Column(name = "TIMETABLE_TIMES")
    private List<Time> times;

    public Timetable() {
        schedule = new HashMap<>();
        times = new ArrayList<>();
    }

    public void reset() {
        schedule.clear();
        times.clear();
    }

    public void init() {
        times = Utils.Times.getAllTimes();
    }

    public void init(int first, int last) {
        times = Utils.Times.getTimesArray(first, last);
    }

    public void init(Shift shift) {
        times = Utils.Times.getTimesForShift(shift);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Course, TimesHolder> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<Course, TimesHolder> schedule) {
        this.schedule = schedule;
    }

    public List<Time> getTimes() {
        return times;
    }

    public void setTimes(List<Time> times) {
        this.times = times;
    }
}
