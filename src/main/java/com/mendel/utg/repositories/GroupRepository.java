package com.mendel.utg.repositories;

import com.mendel.utg.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alberto Alegria
 */
@Repository

public interface GroupRepository extends JpaRepository<Group, Long> {

}
