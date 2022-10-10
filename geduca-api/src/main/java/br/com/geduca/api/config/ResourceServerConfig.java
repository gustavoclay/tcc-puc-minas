package br.com.geduca.api.config;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Configuração do Spring Security - ResourceServerConfigurerAdapter Não
 * necessita estar autenticado para executar requisição em '/teste', mas pra
 * frente configurar healthcheck API REST não criará sessão no servidor, ou
 * seja, não manterá estado de nada. Cross site desabilitado -> javascript
 * injection Configuração do CORS de acordo com a documentação do Spring, Em
 * caso de problemas implmentar o filtro com as configurações do CORS
 *
 * @author gustavoclay
 *
 */
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value("#{ @environment['geduca.urls-liberadas'] }")
	private String[] urlsLiberadas;

	@Value("#{ @environment['geduca.urls-principais-liberadas'] }")
	private String[] urlsPrincipaisLiberadas;

	@Value("#{ @environment['geduca.origin-pemitida'] }")
	private String originPermitida;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(ArrayUtils.addAll(urlsPrincipaisLiberadas, urlsLiberadas))
				.permitAll().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.stateless(true);
	}

	@Bean
	public MethodSecurityExpressionHandler createExpressionHandler() {
		return new OAuth2MethodSecurityExpressionHandler();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Content-Type, Authorization, Content-Length, X-Requested-With"));
		configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
