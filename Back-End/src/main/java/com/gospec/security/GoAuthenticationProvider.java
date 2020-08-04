package com.gospec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GoAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private GoUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder pwEncoding;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();

		GoUserDetails user = (GoUserDetails) userDetailsService.loadUserByUsername(username);

		if (user == null || !username.equals(user.getUsername()) || !pwEncoding.matches(password, user.getPassword())) {
			throw new BadCredentialsException(username);
		} else if (!user.isEnabled()) {
			throw new DisabledException(username);
		} else if (!user.isAccountNonExpired()) {
			throw new AccountExpiredException(username);
		} else if (!user.isCredentialsNonExpired()) {
			throw new CredentialsExpiredException(username);
		}

		Authentication auth = new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());

		return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
