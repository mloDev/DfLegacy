package de.mlo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.mlo.enums.Faction;
import de.mlo.model.MediumShip;

public interface MediumShipRepository extends JpaRepository<MediumShip, Integer>{

	List<MediumShip> findAllByFaction(Faction faction);
}
