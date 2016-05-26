package de.mlo.enums;

import lombok.Getter;

public enum ShipType {
	LIGHTSHIP("shiptype.light"),
	MEDIUMSHIP("shiptype.medium"),
	HEAVYSHIP("shiptype.heavy"),
	SUPERHEAVYSHIP("shiptype.superheavy");

 
	@Getter
 	private String value;
	
	private ShipType(String value){
		this.value = value;
	}

}
