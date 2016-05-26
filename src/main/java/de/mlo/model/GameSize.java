package de.mlo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class GameSize {
	
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
	private int pathfinderSize;
	
	@Column
	@Getter
	@Setter
	private int lineSize;
	
	@Column
	@Getter
	@Setter
	private int vanguardSize;
	
	@Column
	@Getter
	@Setter
	private int flagSize;
	
	@Column
	@Getter
	@Setter
	private int battlegroupMax;
	
	@Column
	@Getter
	@Setter
	private int maxPoints;
	
	@OneToOne
    @JoinTable(name = "FLEET_ID")
    private Fleet fleet;
}
