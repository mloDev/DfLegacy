package de.mlo.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.mlo.enums.Faction;
import de.mlo.model.HeavyShip;

public interface HeavyShipRepository extends JpaRepository<HeavyShip, Integer>{
	
	List<HeavyShip> findAllByFaction(Faction faction);

}
