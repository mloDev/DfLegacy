package de.mlo.controller;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


// TODO: Auto-generated Javadoc
/**
 * The Class LinkController.
 */
@Controller
public class LinkController {
	
	/** The logger. */
	static Logger logger = LoggerFactory.getLogger(LinkController.class);

	/**
	 * Main page.
	 *
	 * @param session the session
	 * @return the string
	 */
	@RequestMapping(value = "/")
	public String mainPage(HttpSession session) {
				session.setAttribute("fragmenttop", "fragments/ADMIN");
				session.setAttribute("fragmentvert", "fragments/ADMIN");
				return "home-admin";
	}

	/**
	 * Index page.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/index")
	public String indexPage() {
		return "redirect:/";
	}
	


}
