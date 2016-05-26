package de.mlo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.mlo.model.FlagBattlegroupe;

public interface FlagBattlegroupeRepository extends JpaRepository<FlagBattlegroupe, Integer>{
	
	List<FlagBattlegroupe> getBattlegroupesByFleetId (int fleetId);
	
}
