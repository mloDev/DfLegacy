package de.mlo.DTO;

import java.util.List;

import de.mlo.model.HeavyShip;
import de.mlo.model.LightShip;
import de.mlo.model.MediumShip;
import de.mlo.model.SuperHeavyShip;
import lombok.Getter;
import lombok.Setter;

public class BattlegroupeDTO {
	
	@Getter
	@Setter
	private int id;
	
	@Getter
	@Setter
	private int maxShips;

	@Getter
	@Setter
	private List<LightShip> lightShip;
	
	@Getter
	@Setter
	private List<MediumShip> mediumShip;
	
	@Getter
	@Setter
	private List<HeavyShip> heavyShip;
	
	@Getter
	@Setter
	private List<SuperHeavyShip> superHeavyShip;
	
	@Getter
	@Setter
	int lightShipSize;
	
	@Getter
	@Setter
	int mediumShipSize;
	
	@Getter
	@Setter
	int heavyShipSize;
	
	@Getter
	@Setter
	int superHeavyShipSize;

}
