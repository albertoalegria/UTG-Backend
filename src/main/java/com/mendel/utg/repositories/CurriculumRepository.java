package com.mendel.utg.repositories;

import com.mendel.utg.models.Career;
import com.mendel.utg.models.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alberto Alegria
 */
@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
}
