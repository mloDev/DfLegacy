package de.mlo.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.mlo.DTO.FleetDTO;
import de.mlo.Repository.FlagBattlegroupeRepository;
import de.mlo.Repository.FleetRepository;
import de.mlo.Repository.GameSizeRepository;
import de.mlo.model.FlagBattlegroupe;
import de.mlo.model.Fleet;
import de.mlo.model.GameSize;
import de.mlo.model.LineBattlegroupe;
import de.mlo.model.PathfinderBattlegroupe;
import de.mlo.model.VanguardBattlegroupe;

@Controller
@RequestMapping(value = "/fleet")
public class FleetController {

	/** The logger. */
	static Logger logger = LoggerFactory.getLogger(FleetController.class);
	
	/** The business object. */
	static String businessObject = "Ship"; // used in RedirectAttributes
											// messages
	
	@Autowired
	private FleetRepository fleetRepo;
	
	@Autowired
	private GameSizeRepository gameSizeRepo;
	
	@Autowired
	private FlagBattlegroupeRepository flagRepo;
	
	/** The message source. */
	@Autowired
	private MessageSource messageSource;
	
	@ModelAttribute("allGameSizes")
	public List<GameSize> getAllGameSizes() {
		return gameSizeRepo.findAll();
	}
	
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listFleets(Model model) {
		logger.debug("IN: Ship/list-GET");
		
		List<Fleet> fleets = fleetRepo.findAll();
		model.addAttribute("fleets", fleets);

		// if there was an error in /add, we do not want to overwrite
		// the existing ship object containing the errors.
		if (!model.containsAttribute("shipDTO")) {
			logger.debug("Adding shipDTO object to model");
			FleetDTO fleetDTO = new FleetDTO();
			model.addAttribute("fleetDTO", fleetDTO);
		}
		return "fleet-list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addfleetpage(@Valid @ModelAttribute FleetDTO fleetDTO,
			BindingResult result, RedirectAttributes redirectAttrs) {

		logger.debug("IN: Fleet/add-GET");

		return "fleet-add";

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addFleet(@Valid @ModelAttribute FleetDTO fleetDTO,
			BindingResult result, RedirectAttributes redirectAttrs) throws Exception {

		logger.debug("IN: Fleet/add-POST");

		if (result.hasErrors()) {
			logger.debug("fleetDTO add error: " + result.toString());
			redirectAttrs.addFlashAttribute(
					"org.springframework.validation.BindingResult.fleetDTO",
					result);
			redirectAttrs.addFlashAttribute("fleetDTO", fleetDTO);
			return "redirect:/fleet/list";
		} else {
			Fleet fleet = new Fleet();
			fleet = getFleet(fleetDTO);
			fleetRepo.save(fleet);
			String message = messageSource
					.getMessage("ctrl.message.success.add", new Object[] {
							businessObject, fleet.getName() }, Locale.US);
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/fleet/list";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editFleetPage(
			@RequestParam(value = "id", required = true) Integer id,
			Model model, RedirectAttributes redirectAttrs) {

		logger.debug("IN: User/edit-GET:  ID to query = " + id);

			if (!model.containsAttribute("fleetDTO")) {
				logger.debug("Adding fleetDTO object to model");
				Fleet fleet = fleetRepo.getOne(id);
				FleetDTO fleetDTO = getFleetDTO(fleet);
				model.addAttribute("fleetDTO", fleetDTO);
			}
			return "fleet-edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editStudent(@Valid @ModelAttribute FleetDTO fleetDTO,
			BindingResult result, RedirectAttributes redirectAttrs,
			@RequestParam(value = "action", required = true) String action) {
		if (action.equals(messageSource.getMessage("button.action.cancel",
				null, Locale.US))) {
			String message = messageSource.getMessage(
					"ctrl.message.success.cancel", new Object[] { "Edit",
							businessObject, fleetDTO.getName() }, Locale.US);
			redirectAttrs.addFlashAttribute("message", message);
		} else if (result.hasErrors()) {
			logger.debug("Fleet-edit error: " + result.toString());
			redirectAttrs.addFlashAttribute(
					"org.springframework.validation.BindingResult.fleetDTO",
					result);
			redirectAttrs.addFlashAttribute("fleetDTO", fleetDTO);
			return "redirect:/fleet/list";
		} else if (action.equals(messageSource.getMessage("button.action.addFlag", null, Locale.US))) {
			Fleet fleet = getFleetWithId(fleetDTO);
			if (fleetDTO.getGameSize().getFlagSize() < 5) {
				fleet.addFlagGroupe(new FlagBattlegroupe());;
				fleetRepo.saveAndFlush(fleet);
			}
		} else if (action.equals(messageSource.getMessage("button.action.addLine", null, Locale.US))) {
			Fleet fleet = getFleetWithId(fleetDTO);
			fleet.addLineGroupe(new LineBattlegroupe());;
			fleetRepo.saveAndFlush(fleet);
		} else if (action.equals(messageSource.getMessage("button.action.addVanguard", null, Locale.US))) {
			Fleet fleet = getFleetWithId(fleetDTO);
			fleet.addVanguardGroupe(new VanguardBattlegroupe());;
			fleetRepo.saveAndFlush(fleet);
		} else if (action.equals(messageSource.getMessage("button.action.addPathfinder", null, Locale.US))) {
			Fleet fleet = getFleetWithId(fleetDTO);
			fleet.addPathfinderGroupe(new PathfinderBattlegroupe());;
			fleetRepo.saveAndFlush(fleet);
		} else if (action.equals(messageSource.getMessage("button.action.save",
				null, Locale.US))) {
			logger.debug("Student/edit-POST:  " + fleetDTO.toString());
			Fleet fleet = getFleetWithId(fleetDTO);
			fleetRepo.saveAndFlush(fleet);

			String message = messageSource.getMessage(
					"ctrl.message.success.update", new Object[] {
							businessObject, fleetDTO.getName() },
					Locale.US);
			redirectAttrs.addFlashAttribute("message", message);
		}
		return "redirect:/fleet/edit?id=" + fleetDTO.getId();
	}
	
	
	
	private Fleet getFleetWithId(FleetDTO fleetDTO) {
		Fleet fleet = new Fleet();
		fleet.setGameSize(fleetDTO.getGameSize());
		logger.info(fleet.getGameSize().getName());
		logger.info("DTO: " + fleetDTO.getGameSize().getName());
		fleet.setId(fleetDTO.getId());
		fleet.setFaction(fleetDTO.getFaction());
		fleet.setName(fleetDTO.getName());
		fleet.setFlagBattlegroupes(fleetDTO.getFlagBattlegroupes());
		fleet.setLineBattlegroupes(fleetDTO.getLineBattlegroupes());
		fleet.setPathfinderBattlegroupes(fleetDTO.getPathfinderBattlegroupes());
		fleet.setVanguardBattlegroupes(fleetDTO.getVanguardBattlegroupes());
		return fleet;
	}


	private FleetDTO getFleetDTO(Fleet fleet) {
		FleetDTO fleetDTO = new FleetDTO();
		fleetDTO.setGameSize(fleet.getGameSize());
		logger.info(fleet.getGameSize().getName());
		logger.info("DTO: " + fleetDTO.getGameSize().getName());
		fleetDTO.setName(fleet.getName());
		fleetDTO.setFaction(fleet.getFaction());
		fleetDTO.setId(fleet.getId());
		fleetDTO.setFlagBattlegroupes(flagRepo.getBattlegroupesByFleetId(fleet.getId()));
		fleetDTO.setLineBattlegroupes(fleet.getLineBattlegroupes());
		fleetDTO.setVanguardBattlegroupes(fleet.getVanguardBattlegroupes());
		fleetDTO.setPathfinderBattlegroupes(fleet.getPathfinderBattlegroupes());
		return fleetDTO;
	}

	private Fleet getFleet(FleetDTO fleetDTO) {
		Fleet fleet = new Fleet();
		fleet.setFaction(fleetDTO.getFaction());
		fleet.setGameSize(fleetDTO.getGameSize());
		fleet.setName(fleetDTO.getName());
		fleet.setFlagBattlegroupes(fleetDTO.getFlagBattlegroupes());
		fleet.setLineBattlegroupes(fleetDTO.getLineBattlegroupes());
		fleet.setPathfinderBattlegroupes(fleetDTO.getPathfinderBattlegroupes());
		fleet.setVanguardBattlegroupes(fleetDTO.getVanguardBattlegroupes());
		return fleet;
	}
	
}
