package com.mendel.utg;

import com.mendel.utg.models.*;
import com.mendel.utg.repositories.*;
import com.mendel.utg.utils.enums.Shift;
import com.mendel.utg.utils.enums.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alberto Alegria
 */
@Component
public class MendelSeeder implements CommandLineRunner {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CareerRepository careerRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private CurriculumRepository curriculumRepository;

    @Override
    public void run(String... args) throws Exception {
        initCareers();
        initBuildings();
        initClassrooms();
        initTeachers();
        initCurricula();
        initSubjects();
        initGroups();
        initCourses();
    }


    private List<Career> careers = new ArrayList<>();
    private void initCareers() {
        for (int i = 1; i <= 10; i++) {
            Career career = new Career();
            career.setName("Ingeniería en Sistemas Computacionales" + i);
            career.setAcronym("ISC" + i);
            career.setImgPath("undefined");
            careers.add(career);
        }
        careerRepository.save(careers);
    }

    private List<Building> buildings = new ArrayList<>();
    private void initBuildings() {
        for (int i = 1; i <= 10; i++) {
            Building building = new Building();
            building.setName("Building" + i);
            building.setImgPath("undefined");
            buildings.add(building);
        }
        buildingRepository.save(buildings);
    }

    private List<Classroom> classrooms = new ArrayList<>();
    private void initClassrooms() {
        for (int i = 1; i <= 10; i++) {
            Classroom classroom = new Classroom();
            classroom.setBuilding(buildings.get(i - 1));
            classroom.setIdentifier("Classroom" + i);
            classroom.setSize(50);
            classroom.setType(i%2 == 0 ? Type.CLASSROOM : Type.LABORATORY);
            classrooms.add(classroom);
        }
        classroomRepository.save(classrooms);
    }

    private List<Group> groups = new ArrayList<>();
    private void initGroups() {
        for (int i = 1; i <= 10; i++) {
            Group group = new Group();
            group.setShift(i%2==0?Shift.MORNING:Shift.AFTERNOON);
            group.setCurriculum(curricula.get(i - 1));
            group.setSemester(i);
            groups.add(group);
        }
        groupRepository.save(groups);
    }

    private List<Course> courses = new ArrayList<>();
    private void initCourses() {
        for (int i = 1; i <= 10; i++) {
            Course course = new Course();
            course.setSize(50);
            course.setGroup(groups.get(i - 1));
            course.setSubject(subjects.get(i - 1));
            course.setTeacher(teachers.get(i - 1));
            courses.add(course);
        }
        courseRepository.save(courses);
    }

    private List<Curriculum> curricula = new ArrayList<>();
    private void initCurricula() {
        for (int i = 1; i <= 10; i++) {
            Curriculum curriculum = new Curriculum();
            curriculum.setCareer(careers.get(i - 1));
            curriculum.setName("Curriculum" + i);
            curricula.add(curriculum);
        }
        curriculumRepository.save(curricula);
    }

    private List<Subject> subjects = new ArrayList<>();
    private void initSubjects() {
        for (int i = 1; i <= 10; i++) {
            Subject subject = new Subject();
            subject.setName("Subject" + i);
            subject.setCode("Code" + i);
            subject.setSemester(i);
            subject.setType(i%2==0?Type.CLASSROOM:Type.LABORATORY);
            subject.setHours(5);
            subject.setLaboratoryHours(i%2==0?0:2);
            subject.setCurriculum(curricula.get(i - 1));
            subjects.add(subject);
        }
        subjectRepository.save(subjects);
    }

    private List<Teacher> teachers = new ArrayList<>();
    private void initTeachers() {
        for (int i = 1; i <= 10; i++) {
            Teacher teacher = new Teacher();
            teacher.setFirstName("Daniel Alberto" + i);
            teacher.setLastName("Alegría Sánchez" + i);
            teacher.setTitle("Dr.");
            teacher.setStudyLevel("PhD");
            teacher.setHours(40);
            teacher.setCheckIn(1);
            teacher.setCheckOut(10);
            teacher.setCourses(courses);
            teachers.add(teacher);
        }
        courseRepository.save(courses);
        teacherRepository.save(teachers);
    }
}
