package com.mendel.utg.repositories;

import com.mendel.utg.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alberto Alegria
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
