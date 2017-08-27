package com.mendel.utg.controllers;

import com.mendel.utg.models.Group;
import com.mendel.utg.models.Time;
import com.mendel.utg.repositories.CareerRepository;
import com.mendel.utg.repositories.CurriculumRepository;
import com.mendel.utg.repositories.GroupRepository;
import com.mendel.utg.utils.Utils;
import com.mendel.utg.utils.enums.Day;
import com.mendel.utg.utils.enums.Shift;
import com.mendel.utg.utils.enums.StudyLevel;
import com.mendel.utg.utils.enums.Type;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mendel.utg.utils.Global.MAX_SEMESTER;

/**
 * @author Alberto Alegria
 */
@RestController
@CrossOrigin("http://localhost:4200")
public class UtilsController {
    private Log log = LogFactory.getLog(UtilsController.class);

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private CurriculumRepository curriculumRepository;

    @GetMapping("/utils/types")
    public ResponseEntity<Type[]> getTypes() {
        log.info("Retrieving types");
        return new ResponseEntity<Type[]>(Type.values(), HttpStatus.OK);
    }

    @GetMapping("/utils/days")
    public ResponseEntity<Day[]> getDays() {
        log.info("Retrieving days");
        return new ResponseEntity<Day[]>(Day.getWeek(), HttpStatus.OK);
    }

    @GetMapping("/utils/alldays")
    public ResponseEntity<Day[]> getAllDays() {
        log.info("Retrieving days");
        return new ResponseEntity<Day[]>(Day.values(), HttpStatus.OK);
    }

    @GetMapping("/utils/shifts")
    public ResponseEntity<Shift[]> getShifts() {
        log.info("Retrieving shifts");
        return new ResponseEntity<Shift[]>(Shift.values(), HttpStatus.OK);
    }

    @GetMapping("/utils/shifts/available")
    public ResponseEntity<List<Shift>> getAvailableShiftsForGroup(@RequestParam Map<String, String> params) {
        long id = Long.valueOf(params.get("id"));
        int semester = Integer.parseInt(params.get("semester"));

        log.info("Retrieving available shifts for curricula with id " + id + " and semester " + semester);

        List<Group> groups = curriculumRepository.findOne(id).getGroups()
                .stream().filter(group -> group.getSemester().equals(semester)).collect(Collectors.toList());

        List<Shift> shifts = new ArrayList<>(Arrays.asList(Shift.values()));

        groups.forEach(group -> shifts.remove(group.getShift()));

        return new ResponseEntity<List<Shift>>(shifts, HttpStatus.OK);
    }

    @GetMapping("utils/semesters")
    public ResponseEntity<List<Integer>> getSemesters() {
        log.info("Retrieving semesters");
        List<Integer> semesters = new ArrayList<>();
        for (int i = 0; i <= MAX_SEMESTER; i++) {
            semesters.add(i);
        }
        return new ResponseEntity<List<Integer>>(semesters, HttpStatus.OK);
    }

    @GetMapping("utils/levels")
    public ResponseEntity<StudyLevel[]> getStudyLevels() {
        log.info("Retrieving study levels");
        return new ResponseEntity<StudyLevel[]>(StudyLevel.values(), HttpStatus.OK);
    }

    @GetMapping("utils/levels/abbr")
    public ResponseEntity<String> getStudyAbbr(@RequestParam String title) {
        log.info("Retrieving abbreviation for " + title);

        StudyLevel level;

        if (title.equals(StudyLevel.BACHELOR.getName())) {
            level = StudyLevel.BACHELOR;
        } else if (title.equals(StudyLevel.MASTER.getName())) {
            level = StudyLevel.MASTER;
        } else if (title.equals(StudyLevel.PHD.getName())) {
            level = StudyLevel.PHD;
        } else {
            log.error("Cannot obtain abbreviation for " + title + ". Study level does not exists!");
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>(level.getAbbr(), HttpStatus.OK);
    }

    /*
    * TODO fix the check in and check out hours 25/08
    * Times are rendered incorrectly
    *
    * nvm i already fix it 26/08
    * */
    @GetMapping("utils/times/checkIn")
    public ResponseEntity<List<String>> getCheckInTimes() {
        List<Time> times = Utils.Times.getTimesRange(1, 14);//FIXME
        List<String> checkIn = new ArrayList<>();

        times.forEach(time -> checkIn.add(time.getAmPmTime()));

        return new ResponseEntity<List<String>>(checkIn, HttpStatus.OK);
    }

    @GetMapping("utils/times/checkOut")
    public ResponseEntity<List<String>> getCheckOutTimes() {
        List<Time> times = Utils.Times.getTimesRange(1, 14);//FIXME
        List<String> checkOut = new ArrayList<>();

        times.forEach(time -> checkOut.add(time.getAmPmTime2()));

        return new ResponseEntity<List<String>>(checkOut, HttpStatus.OK);
    }

}
