package com.mendel.utg.repositories;

import com.mendel.utg.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alberto Alegria
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
