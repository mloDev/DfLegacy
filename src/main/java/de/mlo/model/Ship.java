package de.mlo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import de.mlo.enums.Faction;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Ship {
	
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
	private int scan;
	
	@Column
	@Getter
	@Setter
	private int sig;
	
	@Column
	@Getter
	@Setter
	private int thrust;
	
	@Column
	@Getter
	@Setter
	private int hull;
	
	@Column
	@Getter
	@Setter
	private int a;
	
	@Column
	@Getter
	@Setter
	private int pd;
	
	@Column
	@Getter
	@Setter
	private String g;
	
	@Column
	@Getter
	@Setter
	private String t;
	
	@Column
	@Getter
	@Setter
	private String special;
	
	@Enumerated(EnumType.STRING)
	@Column
	@Getter
	@Setter
	private Faction faction;
	
	@Column
	@Getter
	@Setter
	private int pts;
	
	@OneToMany
	@JoinTable(name = "ship_id")
	@Getter
	@Setter
	private List<Weapon> weapons; 
	
	@Column
	@Getter
	@Setter
	private String factionLogoURL;
		
}
