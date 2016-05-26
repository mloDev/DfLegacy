package de.mlo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Battlegroupe {
	
	@Id
	@GeneratedValue
	@Getter
	@Setter
	private int id;
	
	@Column
	@Getter
	@Setter
	private String name;
	
	@Column
	@Getter
	@Setter
	private int maxShips;
	
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "battlegroup_id")
	@Getter
	@Setter
	private List<LightShip> lightShip;
	
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "battlegroup_id")
	@Getter
	@Setter
	private List<MediumShip> mediumShip;
	
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "battlegroup_id")
	@Getter
	@Setter
	private List<HeavyShip> heavyShip;
	
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "battlegroup_id")
	@Getter
	@Setter
	private List<SuperHeavyShip> superHeavyShip;
	
	@Column
	@Getter
	@Setter
	int lightShipSize;
	
	@Column
	@Getter
	@Setter
	int mediumShipSize;
	
	@Column
	@Getter
	@Setter
	int heavyShipSize;
	
	@Column
	@Getter
	@Setter
	int superHeavyShipSize;


}
