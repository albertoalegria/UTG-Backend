package com.mendel.utg.repositories;

import com.mendel.utg.models.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alberto Alegria
 */
@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {
}
