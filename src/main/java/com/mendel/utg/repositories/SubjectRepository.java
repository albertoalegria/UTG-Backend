package com.mendel.utg.repositories;

import com.mendel.utg.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alberto Alegria
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
