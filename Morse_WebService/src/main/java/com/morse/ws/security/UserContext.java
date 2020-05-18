package com.morse.ws.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class UserContext implements UserDetails {

	//private UsuarioPojo user;
	private Object user;

	//public UserContext(UsuarioPojo user) {
	public UserContext(Object user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		//for (RolePojo role : user.getRoles()) {
		//	if (role.getActive())
		//		authorities.add(new SimpleGrantedAuthority(role.getCode()));
		//}
		return authorities;
	}
	
	public Object getUser() {
		return this.user;
	}

	@Override
	public String getPassword() {
		//return user.getPassword();
		return null;
	}

	@Override
	public String getUsername() {
		//return user.getUsername();
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		return this == o
			|| o != null && o instanceof UserContext
			&& Objects.equals(user, ((UserContext) o).user);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(user);
	}
}
