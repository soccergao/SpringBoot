package com.soccergao.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.auth.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccergao.bo.JwtAuthenticationRequest;
import com.soccergao.bo.JwtAuthenticationResponse;
import com.soccergao.bo.JwtUser;
import com.soccergao.entity.mongo.User;
import com.soccergao.service.AuthService;
import com.soccergao.util.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private AuthService authService;

    @Autowired
    @Qualifier("jwtUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @GetMapping(value = "refresh")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @PostMapping(value = "/register")
    public ResponseEntity<User> register(@RequestBody User user) throws AuthenticationException{
        return ResponseEntity.ok(authService.register(user));
    }
}
