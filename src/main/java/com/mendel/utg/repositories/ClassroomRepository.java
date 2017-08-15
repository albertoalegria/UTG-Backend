package com.mendel.utg.repositories;

import com.mendel.utg.models.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alberto Alegria
 */
@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
