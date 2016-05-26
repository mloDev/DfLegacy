package de.mlo.enums;

import lombok.Getter;

public enum Faction {
	PHR("faction.phr"),
	UCM("faction.ucm"),
	SCOURGE("faction.scourge"),
	SHALTARI("faction.shaltari");

 
	@Getter
 	private String value;
	
	private Faction(String value){
		this.value = value;
	}

}
