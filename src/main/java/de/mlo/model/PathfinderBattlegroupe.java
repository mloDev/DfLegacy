package de.mlo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class PathfinderBattlegroupe extends Battlegroupe {
	
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "FLEET_PATHFINDER_ID")
	@Getter
	private Fleet fleet;

}
