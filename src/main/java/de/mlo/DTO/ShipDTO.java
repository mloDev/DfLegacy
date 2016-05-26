package de.mlo.DTO;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import de.mlo.enums.Faction;
import de.mlo.enums.ShipType;
import de.mlo.model.Weapon;
import lombok.Getter;
import lombok.Setter;

public class ShipDTO {

	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private int scan;

	@Getter
	@Setter
	private int sig;

	@Getter
	@Setter
	private int thrust;

	@Getter
	@Setter
	private int hull;

	@Getter
	@Setter
	private int a;

	@Getter
	@Setter
	private int pd;

	@Getter
	@Setter
	private String g;

	@Getter
	@Setter
	private String t;

	@Getter
	@Setter
	private String special;

	@Getter
	@Setter
	private Faction faction;

	@Getter
	@Setter
	private int pts;

	@Getter
	@Setter
	private List<Weapon> weapons; 
	
	@Getter
	@Setter
	private String factionLogoURL;
	
	@Getter
	@Setter
	private ShipType shipType;

}
