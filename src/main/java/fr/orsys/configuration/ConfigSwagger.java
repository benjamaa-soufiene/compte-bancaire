package fr.orsys.configuration;


import java.util.Arrays;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ConfigSwagger {
	/************************************************
	 * ***********Sans JWT******************************
	 ***************************************************/
	/*
	 * public static final Contact DEFAULT_CONTACT = new Contact("CompteBanque.fr",
	 * "http://www.comptebanque.fr", "info@comptebanque.fr"); public static final
	 * ApiInfo DEFAULT_API_INFO = new ApiInfo("CompteBanque.fr",
	 * "Spring Boot Data Rest for CompteBanque.fr", "1.0", "urn:tos",
	 * DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
	 * Arrays.asList());
	 * 
	 * @Bean public Docket api() { return new
	 * Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).select()
	 * .apis(RequestHandlerSelectors.any()).apis(RequestHandlerSelectors.any())
	 * .paths(PathSelectors.regex("/appRoles.*").negate()).paths(PathSelectors.regex
	 * ("/appUsers.*").negate())
	 * .paths(PathSelectors.regex("/error.*").negate()).paths(PathSelectors.regex(
	 * "/profile.*").negate()) .build(); }
	 * 
	 */
	/******************************************************
	 **************** Avec JWT*************************
	 ***************************************************/
	public static final String AUTHORIZATION_HEADER = "Authorization";

	public static final Contact DEFAULT_CONTACT = new Contact("CompteBanque.fr", "http://www.comptebanque.fr",
			"info@comptebanque.fr");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("CompteBanque.fr",
			"Spring Boot Data Rest for CompteBanque.fr", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
			"http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO)
				.securityContexts(Arrays.asList(securityContext())).securitySchemes(Arrays.asList(apiKey())).select()
				.apis(RequestHandlerSelectors.any()).apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/appRoles.*").negate()).paths(PathSelectors.regex("/appUsers.*").negate())
				.paths(PathSelectors.regex("/error.*").negate()).paths(PathSelectors.regex("/profile.*").negate())
				.build();
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private java.util.List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

}
