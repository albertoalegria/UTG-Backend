package com.mendel.utg.controllers;

import com.mendel.utg.models.Group;
import com.mendel.utg.repositories.CareerRepository;
import com.mendel.utg.repositories.CurriculumRepository;
import com.mendel.utg.repositories.GroupRepository;
import com.mendel.utg.utils.enums.Day;
import com.mendel.utg.utils.enums.Shift;
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
}
