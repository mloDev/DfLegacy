package de.mlo.DTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

public class GameSizeDTO {

	@Getter
	@Setter
	private int id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private int pathfinderSize;
	
	@Getter
	@Setter
	private int lineSize;
	
	@Getter
	@Setter
	private int vanguardSize;
	
	@Getter
	@Setter
	private int flagSize;

	@Getter
	@Setter
	private int battlegroupMax;

	@Getter
	@Setter
	private int maxPoints;

}
