package de.mlo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import de.mlo.enums.Faction;
import lombok.Getter;
import lombok.Setter;
@Entity
public class Fleet {
	
	@Id
	@GeneratedValue
	@Getter
	@Setter
	private int id;
	
	@Column
	@Getter
	@Setter
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column
	@Getter
	@Setter
	private Faction faction;
	
	@OneToOne(mappedBy = "fleet")
	@Getter
	@Setter
	private GameSize gameSize;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fleet")
	@Getter
	@Setter
	private List<FlagBattlegroupe> flagBattlegroupes;
	
	public void addFlagGroupe(FlagBattlegroupe flagGroupe) {
		if (flagGroupe != null) {
			if (flagBattlegroupes == null) {
				flagBattlegroupes = new ArrayList<FlagBattlegroupe>();          
		    }
		    flagBattlegroupes.add(flagGroupe);
		    flagGroupe.setFleet(this);
		 }
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fleet")
	@Getter
	@Setter
	private List<VanguardBattlegroupe> vanguardBattlegroupes;
	
	public void addVanguardGroupe(VanguardBattlegroupe vanguardGroupe) {
		if (vanguardGroupe != null) {
			if (vanguardBattlegroupes == null) {
				vanguardBattlegroupes = new ArrayList<VanguardBattlegroupe>();          
		    }
			vanguardBattlegroupes.add(vanguardGroupe);
			vanguardGroupe.setFleet(this);
		 }
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fleet")
	@Getter
	@Setter
	private List<LineBattlegroupe> lineBattlegroupes;
	
	public void addLineGroupe(LineBattlegroupe lineGroupe) {
		if (lineGroupe != null) {
			if (lineBattlegroupes == null) {
				lineBattlegroupes = new ArrayList<LineBattlegroupe>();          
		    }
			lineBattlegroupes.add(lineGroupe);
			lineGroupe.setFleet(this);
		 }
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fleet")
	@Getter
	@Setter
	private List<PathfinderBattlegroupe> pathfinderBattlegroupes;
	
	public void addPathfinderGroupe(PathfinderBattlegroupe pathfinderGroupe) {
		if (pathfinderGroupe != null) {
			if (pathfinderBattlegroupes == null) {
				pathfinderBattlegroupes = new ArrayList<PathfinderBattlegroupe>();          
		    }
			pathfinderBattlegroupes.add(pathfinderGroupe);
			pathfinderGroupe.setFleet(this);
		 }
	}

	
}
