package de.mlo;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "de.mlo.Repository")
@EntityScan(basePackages = "de.mlo.model")
public class DfCBuilderApplication extends WebMvcConfigurerAdapter {
	
	/** The init. */
	@Value("${init.json}")
	private String init;

	public static void main(String[] args) {
		SpringApplication.run(DfCBuilderApplication.class, args);
	}
	
    /**
	 * H2servlet registration.
	 *
	 * @return the servlet registration bean
	 */
	@Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	// Maps resources path to webapp/resources
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	/**
	 * Message source.
	 *
	 * @return the resource bundle message source
	 */
	// Provides internationalization of messages
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages");
		return source;
	}
	
    /**
	 * Repository populator.
	 *
	 * @return the jackson2 repository populator factory bean
	 */
	@Bean
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
    	Resource sourceData;
    	Jackson2RepositoryPopulatorFactoryBean factory;
		try {
			sourceData = new PathResource(init);
			if(!sourceData.exists())
				sourceData = new ClassPathResource(init);
			factory = new Jackson2RepositoryPopulatorFactoryBean();
			factory.setResources(new Resource[] { sourceData });
		} catch (Exception e) {
			return null;
		}

		return factory;
    }
}
