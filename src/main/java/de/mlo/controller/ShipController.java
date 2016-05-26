package de.mlo.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.mlo.DTO.ShipDTO;
import de.mlo.Repository.HeavyShipRepository;
import de.mlo.Repository.LightShipRepository;
import de.mlo.Repository.MediumShipRepository;
import de.mlo.Repository.ShipRepository;
import de.mlo.Repository.SuperHeavyShipRepository;
import de.mlo.enums.Faction;
import de.mlo.enums.ShipType;
import de.mlo.model.GameSize;
import de.mlo.model.HeavyShip;
import de.mlo.model.LightShip;
import de.mlo.model.MediumShip;
import de.mlo.model.Ship;
import de.mlo.model.SuperHeavyShip;

@Controller
@RequestMapping(value = "/ships")
public class ShipController {
	
	/** The logger. */
	static Logger logger = LoggerFactory.getLogger(ShipController.class);
	
	/** The business object. */
	static String businessObject = "Ship"; // used in RedirectAttributes
											// messages
	@Autowired
	private LightShipRepository lightShipRepo;
	
	@Autowired
	private ShipRepository shipRepo;
	
	@Autowired
	private MediumShipRepository mediumShipRepo;
	
	@Autowired
	private HeavyShipRepository heavyShipRepo;
	
	@Autowired
	private SuperHeavyShipRepository superHeavyShipRepo;

	/** The message source. */
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listShips(Model model) {
		logger.debug("IN: Ship/list-GET");
		
		List<LightShip> lightShips = lightShipRepo.findAll();
		model.addAttribute("lightShips", lightShips);
		List<MediumShip> mediumShips = mediumShipRepo.findAll();
		model.addAttribute("mediumShips", mediumShips);
		List<HeavyShip> heavyShips = heavyShipRepo.findAll();
		model.addAttribute("heavyShips", heavyShips);
		List<SuperHeavyShip> superHeavyShips = superHeavyShipRepo.findAll();
		model.addAttribute("superHeavyShips", superHeavyShips);

		// if there was an error in /add, we do not want to overwrite
		// the existing ship object containing the errors.
		if (!model.containsAttribute("shipDTO")) {
			logger.debug("Adding shipDTO object to model");
			ShipDTO shipDTO = new ShipDTO();
			model.addAttribute("shipDTO", shipDTO);
		}
		return "ship-list";
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String listShipsAll(Model model) {
		logger.debug("IN: Ship/list-GET");
		
		List<LightShip> lightShips = lightShipRepo.findAll();
		model.addAttribute("lightShips", lightShips);
		List<MediumShip> mediumShips = mediumShipRepo.findAll();
		model.addAttribute("mediumShips", mediumShips);
		List<HeavyShip> heavyShips = heavyShipRepo.findAll();
		model.addAttribute("heavyShips", heavyShips);
		List<SuperHeavyShip> superHeavyShips = superHeavyShipRepo.findAll();
		model.addAttribute("superHeavyShips", superHeavyShips);

		// if there was an error in /add, we do not want to overwrite
		// the existing ship object containing the errors.
		if (!model.containsAttribute("shipDTO")) {
			logger.debug("Adding shipDTO object to model");
			ShipDTO shipDTO = new ShipDTO();
			model.addAttribute("shipDTO", shipDTO);
		}
		return "ship-list :: resultsList";
	}
	
	@RequestMapping(value = { "/{faction}" }, method = RequestMethod.GET)
	public String listFactionShips(Model model, @PathVariable("faction") String faction) {
		logger.debug("IN: FactionShips/list-GET");
			Faction factionEnum = Faction.valueOf(faction);
			List<LightShip> lightShips = lightShipRepo.findAllByFaction(factionEnum);
			model.addAttribute("lightShips", lightShips);
			List<MediumShip> mediumShips = mediumShipRepo.findAllByFaction(factionEnum);
			model.addAttribute("mediumShips", mediumShips);
			List<HeavyShip> heavyShips = heavyShipRepo.findAllByFaction(factionEnum);
			model.addAttribute("heavyShips", heavyShips);
			List<SuperHeavyShip> superHeavyShips = superHeavyShipRepo.findAllByFaction(factionEnum);
			model.addAttribute("superHeavyShips", superHeavyShips);

		// if there was an error in /add, we do not want to overwrite
		// the existing ship object containing the errors.
		if (!model.containsAttribute("shipDTO")) {
			logger.debug("Adding shipDTO object to model");
			ShipDTO shipDTO = new ShipDTO();
			model.addAttribute("shipDTO", shipDTO);
		}
		return "ship-list :: resultsList";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String adduserpage(@Valid @ModelAttribute ShipDTO shipDTO,
			BindingResult result, RedirectAttributes redirectAttrs) {

		logger.debug("IN: Ship/add-GET");

		return "ship-add";

	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addShip(@Valid @ModelAttribute ShipDTO shipDTO,
			BindingResult result, RedirectAttributes redirectAttrs) throws Exception {

		logger.debug("IN: Ship/add-POST");

		if (result.hasErrors()) {
			logger.debug("ShipDTO add error: " + result.toString());
			redirectAttrs.addFlashAttribute(
					"org.springframework.validation.BindingResult.shipDTO",
					result);
			redirectAttrs.addFlashAttribute("shipDTO", shipDTO);
			return "redirect:/ships/list";
		} else {
			Ship ship = generateNewShip(shipDTO);
			shipRepo.saveAndFlush(ship);
			String message = messageSource
					.getMessage("ctrl.message.success.add", new Object[] {
							businessObject, ship.getName() }, Locale.US);
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/ships/list";
		}
	}

	private Ship generateNewShip(ShipDTO shipDTO) {
		Ship ship = new Ship();
		if (shipDTO.getShipType() == ShipType.LIGHTSHIP) {
			ship = new LightShip();
		} else if (shipDTO.getShipType() == ShipType.MEDIUMSHIP) {
			ship = new MediumShip();
		} else if (shipDTO.getShipType() == ShipType.HEAVYSHIP) {
			ship = new HeavyShip();
		} else if (shipDTO.getShipType() == ShipType.SUPERHEAVYSHIP) {
			ship = new SuperHeavyShip();
		}
		ship.setA(shipDTO.getA());
		ship.setFaction(shipDTO.getFaction());
		ship.setG(shipDTO.getG());
		ship.setHull(shipDTO.getHull());
		ship.setName(shipDTO.getName());
		ship.setPd(shipDTO.getPd());
		ship.setPts(shipDTO.getPts());
		ship.setFaction(shipDTO.getFaction());
		ship.setScan(shipDTO.getScan());
		ship.setSig(shipDTO.getSig());
		ship.setThrust(shipDTO.getThrust());
		ship.setT(shipDTO.getT());
		if (shipDTO.getFaction() == Faction.PHR) {
			ship.setFactionLogoURL("phr_25.jpg");
		} else if (shipDTO.getFaction() == Faction.UCM) {
			ship.setFactionLogoURL("ucm_25.jpg");
		} else if (shipDTO.getFaction() == Faction.SCOURGE) {
			ship.setFactionLogoURL("scourge_25.jpg");
		} else if (shipDTO.getFaction() == Faction.SHALTARI) {
			ship.setFactionLogoURL("shaltari_25.jpg");
		}
		
		return ship;
	}
}
