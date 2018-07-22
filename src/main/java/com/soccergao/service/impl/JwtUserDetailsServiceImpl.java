package com.soccergao.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.soccergao.entity.mongo.User;
import com.soccergao.repository.mongo.UserRepository;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);

		return JwtUserFactory.create(
				user.orElseThrow(
						() -> new UsernameNotFoundException(
								String.format("No user found with username '%s'.", username))));
	}

}
