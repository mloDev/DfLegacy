package de.mlo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class FlagBattlegroupe extends Battlegroupe {

	@Setter
	@ManyToOne
	@JoinTable(name = "FLEET_FLAG_ID")
	@Getter
	private Fleet fleet;
	
}
