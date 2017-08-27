package com.mendel.utg.repositories;

import com.mendel.utg.models.Classroom;
import com.mendel.utg.utils.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alberto Alegria
 */
@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findByType(Type type);
}
