package de.mlo.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.mlo.enums.Faction;
import de.mlo.model.LightShip;

public interface LightShipRepository extends JpaRepository<LightShip, Integer>{
	
	List<LightShip> findAllByFaction(Faction faction);

}
