package de.mlo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;


// TODO: Auto-generated Javadoc
/**
 * The Class ThymeleafConfig.
 */
@Configuration
public class ThymeleafConfig {
		
	/** The logger. */
	static Logger logger = LoggerFactory
			.getLogger(ThymeleafConfig.class);

	/**
	 * Template resolver.
	 *
	 * @return the template resolver
	 */
	@Bean
	public TemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setOrder(1);
		return templateResolver;
	}

	/**
	 * Template engine.
	 *
	 * @return the spring template engine
	 */
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

	/**
	 * Thymeleaf view resolver.
	 *
	 * @return the thymeleaf view resolver
	 */
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		return resolver;
	}

	/**
	 * The Class FaviconController.
	 */
	@Controller
	static class FaviconController {
		
		/**
		 * Favicon.
		 *
		 * @return the string
		 */
		@RequestMapping("favicon.ico")
		String favicon() {
			return "forward:/resources/images/favicon.ico";
		}
	}

}
