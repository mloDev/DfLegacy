package de.mlo.DTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import de.mlo.enums.Faction;
import de.mlo.model.FlagBattlegroupe;
import de.mlo.model.Fleet;
import de.mlo.model.GameSize;
import de.mlo.model.LineBattlegroupe;
import de.mlo.model.PathfinderBattlegroupe;
import de.mlo.model.VanguardBattlegroupe;
import lombok.Getter;
import lombok.Setter;

public class FleetDTO {

	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private Faction faction;

	@Getter
	@Setter
	private GameSize gameSize;

	@Getter
	@Setter
	private List<FlagBattlegroupe> flagBattlegroupes;

	@Getter
	@Setter
	private List<VanguardBattlegroupe> vanguardBattlegroupes;

	@Getter
	@Setter
	private List<LineBattlegroupe> lineBattlegroupes;

	@Getter
	@Setter
	private List<PathfinderBattlegroupe> pathfinderBattlegroupes;


}
