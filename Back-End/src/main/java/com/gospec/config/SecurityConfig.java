package com.gospec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.gospec.filter.JwtAuthenticationFilter;
import com.gospec.filter.JwtAuthorizationFilter;
import com.gospec.mapper.UserMapper;
import com.gospec.security.GoAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private GoAuthenticationProvider provider;
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userService);*/

		auth.authenticationProvider(provider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.cors().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilter(new JwtAuthenticationFilter(authenticationManager()))
			.addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userMapper)).authorizeRequests()
			.antMatchers(HttpMethod.POST, "/api/users").permitAll()
			.antMatchers(HttpMethod.POST, "/api/users/*").permitAll()
			.antMatchers(HttpMethod.POST, "/api/file/*").permitAll()
			.antMatchers(HttpMethod.GET, "/api/file/**").permitAll()
			.antMatchers(HttpMethod.PATCH, "/api/users/password").permitAll()
			.antMatchers(HttpMethod.PATCH, "/api/contest/*").permitAll()
			.antMatchers(HttpMethod.GET, "/api/users/dummy").permitAll()
			.antMatchers(HttpMethod.GET, "/api/users/email-duplication/*").permitAll()
			.antMatchers(HttpMethod.GET, "/api/users/nickname-duplication/*").permitAll()
			.antMatchers(HttpMethod.GET, "/api/users/email-authentication/*").permitAll()
			.antMatchers(HttpMethod.GET, "/api/users/other/*").permitAll()
			.antMatchers(HttpMethod.GET, "/api/users/bookmark-user/*").permitAll()
			.antMatchers(HttpMethod.GET, "/api/users/**").authenticated()
			.antMatchers(HttpMethod.GET).permitAll()
			.anyRequest().authenticated()
			.and()
			.httpBasic()
			.authenticationEntryPoint(authenticationEntryPoint);
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**",
				"/swagger/**");
	}
	

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
