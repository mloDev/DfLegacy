package de.mlo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.mlo.enums.Faction;
import de.mlo.model.SuperHeavyShip;

public interface SuperHeavyShipRepository extends JpaRepository<SuperHeavyShip, Integer>{
	
	List<SuperHeavyShip> findAllByFaction(Faction faction);

}
