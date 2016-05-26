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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.mlo.DTO.GameSizeDTO;
import de.mlo.Repository.GameSizeRepository;
import de.mlo.model.GameSize;

@Controller
@RequestMapping(value = "/gameSize")
public class GameSizeController {
	
	/** The logger. */
	static Logger logger = LoggerFactory.getLogger(GameSizeController.class);
	
	/** The business object. */
	static String businessObject = "GameSize"; // used in RedirectAttributes
											// messages
	@Autowired
	private GameSizeRepository gameSizeRepo;

	/** The message source. */
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listGameSizes(Model model) {
		logger.debug("IN: GameSize/list-GET");

		List<GameSize> gameSizes = gameSizeRepo.findAll();
		model.addAttribute("gameSizes", gameSizes);

		// if there was an error in /add, we do not want to overwrite
		// the existing gameSize object containing the errors.
		if (!model.containsAttribute("gameSizeDTO")) {
			logger.debug("Adding gameSizeDTO object to model");
			GameSizeDTO gameSizeDTO = new GameSizeDTO();
			model.addAttribute("gameSizeDTO", gameSizeDTO);
		}
		return "gameSize-list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addgameSizepage(@Valid @ModelAttribute GameSizeDTO gameSizeDTO,
			BindingResult result, RedirectAttributes redirectAttrs) {

		logger.debug("IN: GameSize/add-GET");

		return "gameSize-add";

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addGameSize(@Valid @ModelAttribute GameSizeDTO gameSizeDTO,
			BindingResult result, RedirectAttributes redirectAttrs) throws Exception {

		logger.debug("IN: GameSize/add-POST");

		if (result.hasErrors()) {
			logger.debug("gameSizeDTO add error: " + result.toString());
			redirectAttrs.addFlashAttribute(
					"org.springframework.validation.BindingResult.gameSizeDTO",
					result);
			redirectAttrs.addFlashAttribute("gameSizeDTO", gameSizeDTO);
			return "redirect:/gameSize/list";
		} else {
			GameSize gameSize = new GameSize();
			gameSize = getGameSize(gameSizeDTO);
			gameSizeRepo.save(gameSize);
			String message = messageSource
					.getMessage("ctrl.message.success.add", new Object[] {
							businessObject, gameSize.getName() }, Locale.US);
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/gameSize/list";
		}
	}

	private GameSize getGameSize(GameSizeDTO gameSizeDTO) {
		GameSize gameSize = new GameSize();
		gameSize.setBattlegroupMax(gameSizeDTO.getBattlegroupMax());
		gameSize.setFlagSize(gameSizeDTO.getFlagSize());
		gameSize.setLineSize(gameSizeDTO.getLineSize());
		gameSize.setMaxPoints(gameSizeDTO.getMaxPoints());
		gameSize.setName(gameSizeDTO.getName());
		gameSize.setPathfinderSize(gameSizeDTO.getPathfinderSize());
		gameSize.setVanguardSize(gameSizeDTO.getVanguardSize());
		return gameSize;
	}

	/**
	 * Edits the gameSize page.
	 *
	 * @param id the id
	 * @param model the model
	 * @param redirectAttrs the redirect attrs
	 * @return the string
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editGameSizePage(
			@RequestParam(value = "id", required = true) Integer id,
			Model model, RedirectAttributes redirectAttrs) {

		logger.debug("IN: GameSize/edit-GET:  ID to query = " + id);

		if (!model.containsAttribute("gameSizeDTO")) {
			logger.debug("Adding gameSizeDTO object to model");
			GameSize gameSize = gameSizeRepo.getOne(id);
			GameSizeDTO gameSizeDTO = getGameSize(gameSize);
			logger.debug("GameSize/edit-GET:  " + gameSizeDTO.toString());
			model.addAttribute("gameSizeDTO", gameSizeDTO);
		}
		return "gameSize-edit";
	}

	private GameSizeDTO getGameSize(GameSize gameSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Edits the gameSize.
	 *
	 * @param gameSizeDTO the gameSize dto
	 * @param result the result
	 * @param redirectAttrs the redirect attrs
	 * @param action the action
	 * @return the string
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editGameSize(@Valid @ModelAttribute GameSizeDTO gameSizeDTO,
			BindingResult result, RedirectAttributes redirectAttrs,
			@RequestParam(value = "action", required = true) String action) {

		logger.debug("IN: GameSize/edit-POST: " + action);

		if (action.equals(messageSource.getMessage("button.action.cancel",
				null, Locale.US))) {
			String message = messageSource.getMessage(
					"ctrl.message.success.cancel", new Object[] { "Edit",
							businessObject, gameSizeDTO.getName() }, Locale.US);
			redirectAttrs.addFlashAttribute("message", message);
		} else if (result.hasErrors()) {
			logger.debug("GameSize-edit error: " + result.toString());
			redirectAttrs.addFlashAttribute(
					"org.springframework.validation.BindingResult.gameSizeDTO",
					result);
			redirectAttrs.addFlashAttribute("gameSizeDTO", gameSizeDTO);
			return "redirect:/gameSize/edit?id=" + gameSizeDTO.getId();
		} else if (action.equals(messageSource.getMessage("button.action.save",
				null, Locale.US))) {
			logger.debug("GameSize/edit-POST:  " + gameSizeDTO.toString());
			GameSize gameSize = getGameSize(gameSizeDTO);
			gameSizeRepo.saveAndFlush(gameSize);
			String message = messageSource.getMessage(
					"ctrl.message.success.update", new Object[] {
							businessObject, gameSizeDTO.getName() },
					Locale.US);
			redirectAttrs.addFlashAttribute("message", message);
		}
		return "redirect:/gameSize/list";
	}

	/**
	 * Delete gameSize.
	 *
	 * @param id the id
	 * @param phase the phase
	 * @param model the model
	 * @param redirectAttrs the redirect attrs
	 * @return the string
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteGameSize(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "phase", required = true) String phase,
			Model model, RedirectAttributes redirectAttrs) {

		GameSize gameSize;
		gameSize = gameSizeRepo.getOne(id);

		logger.debug("IN: GameSize/delete-GET | id = " + id + " | phase = " + phase
				+ " | " + gameSize.toString());

		if (phase.equals(messageSource.getMessage("button.action.cancel", null,
				Locale.US))) {
			String message = messageSource.getMessage(
					"ctrl.message.success.cancel", new Object[] { "Delete",
							businessObject, gameSize.getName() }, Locale.US);
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/gameSize/list";
		} else if (phase.equals(messageSource.getMessage("button.action.stage",
				null, Locale.US))) {
			logger.debug("     adding gameSize : " + gameSize.toString());
			model.addAttribute("gameSize", gameSize);
			return "gameSize-delete";
		} else if (phase.equals(messageSource.getMessage(
				"button.action.delete", null, Locale.US))) {
			gameSizeRepo.delete(gameSize.getId());
			String message = messageSource
					.getMessage(
							"ctrl.message.success.delete",
							new Object[] { businessObject,
									gameSize.getName() }, Locale.US);
			redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/gameSize/list";
		}

		return "redirect:/gameSize/list";
	}

}
