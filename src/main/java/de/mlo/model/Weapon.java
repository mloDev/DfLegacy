package de.mlo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Weapon {

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
	private int lock;
	
	@Column
	@Getter
	@Setter
	private int attack;
	
	@Column
	@Getter
	@Setter
	private int damage;
	
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "weapon_id")
	@Getter
	@Setter
	private List<Arc> arcs;
	
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "weapon_id")
    @Getter
    @Setter
	private List<Special> specials;
}
