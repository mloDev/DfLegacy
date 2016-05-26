package de.mlo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.mlo.DTO.BattlegroupeDTO;
import de.mlo.Repository.BattlegroupeRepository;
import de.mlo.model.Battlegroupe;

@Controller
@RequestMapping(value = "/battlegroupe")
public class BattlegroupeController {
	
	/** The logger. */
	static Logger logger = LoggerFactory.getLogger(BattlegroupeController.class);
	
	/** The business object. */
	static String businessObject = "Battlegroupe"; // used in RedirectAttributes
											// messages
	@Autowired
	private BattlegroupeRepository battlegroupeRepo;

	/** The message source. */
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listBattlegroupes(Model model) {
		logger.debug("IN: Battlegroupe/list-GET");

		List<Battlegroupe> battlegroupes = battlegroupeRepo.findAll();
		model.addAttribute("battlegroupes", battlegroupes);

		// if there was an error in /add, we do not want to overwrite
		// the existing battlegroupe object containing the errors.
		if (!model.containsAttribute("battlegroupeDTO")) {
			logger.debug("Adding battlegroupeDTO object to model");
			BattlegroupeDTO battlegroupeDTO = new BattlegroupeDTO();
			model.addAttribute("battlegroupeDTO", battlegroupeDTO);
		}
		return "battlegroupe-list";
	}

}
