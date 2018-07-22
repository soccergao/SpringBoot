package com.soccergao.bo;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails {
	private static final long serialVersionUID = 1L;

	private final String id;
	private final String username;
	private final String password;
	private final String email;
	private final Collection<? extends GrantedAuthority> authorities;
	private final Date lastPasswordResetDate;

	public JwtUser(String id, String username, String password, String email,
			Collection<? extends GrantedAuthority> authorities, Date lastPasswordResetDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	/**
	 * 返回分配给用户的角色列表
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@JsonIgnore
	public String getId() {
		return id;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}

	/**
	 * 账户是否未过期
	 */
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 账户是否未锁定
	 */
	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 密码是否未过期
	 */
	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 账户是否激活
	 */
	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}

	/**
	 * @return 次密码重置日期
	 */
	@JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}
