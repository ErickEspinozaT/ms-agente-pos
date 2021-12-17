package ec.com.ticketshow.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable()
        // dont authenticate this particular request
        .authorizeRequests().antMatchers("/agent/certificates").permitAll();
  }

  @Bean
	public FilterRegistrationBean<CorsFilter> customCorsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(Boolean.valueOf(true));
		config.setAllowedOrigins((List) Arrays.asList("*"));
		config.setAllowedMethods((List) Arrays.asList("*"));
		config.setAllowedHeaders((List) Arrays.asList("*"));
		config.setMaxAge(Long.valueOf(86400L));
		config.setExposedHeaders((List) Arrays.asList("Authorization"));
		source.registerCorsConfiguration("/**", config);
		final FilterRegistrationBean<CorsFilter> bean = (FilterRegistrationBean<CorsFilter>) new FilterRegistrationBean(
				(Filter) new CorsFilter((CorsConfigurationSource) source), new ServletRegistrationBean[0]);
		bean.setName("CORS");
		bean.setOrder(Integer.MIN_VALUE);
		return bean;
	}
}