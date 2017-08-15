package com.mendel.utg.repositories;

import com.mendel.utg.models.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alberto Alegria
 */
@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

}
