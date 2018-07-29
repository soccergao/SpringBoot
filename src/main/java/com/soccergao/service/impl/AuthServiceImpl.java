package com.soccergao.service.impl;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.soccergao.bo.JwtUser;
import com.soccergao.entity.mongo.User;
import com.soccergao.repository.mongo.UserRepository;
import com.soccergao.service.AuthService;
import com.soccergao.util.JwtTokenUtil;

@Service
public class AuthServiceImpl implements AuthService {

	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;
	private JwtTokenUtil jwtTokenUtil;
	private UserRepository userRepository;

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Autowired
	public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
			JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userRepository = userRepository;
	}

	@Override
	public User register(User user) {
		final String username = user.getUsername();
		if (userRepository.findByUsername(username).isPresent()) {
			return null;
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		final String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		user.setLastPasswordResetDate(new Date());
		user.setRoles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
		return userRepository.insert(user);
	}

	@Override
	public String login(String username, String password) {
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
		final Authentication authentication = authenticationManager.authenticate(upToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		final String token = jwtTokenUtil.generateToken(userDetails);
		return token;
	}

	@Override
	public String refresh(String oldToken) {
		final String token = oldToken.substring(tokenHead.length());
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
		if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			return jwtTokenUtil.refreshToken(token);
		}
		return null;
	}
}
