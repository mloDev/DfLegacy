package de.mlo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.mlo.model.FlagBattlegroupe;
import de.mlo.model.Fleet;

public interface FleetRepository extends JpaRepository<Fleet, Integer>{
	
}
