package de.mlo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.mlo.model.Ship;

public interface ShipRepository extends JpaRepository<Ship, Integer> {

}
