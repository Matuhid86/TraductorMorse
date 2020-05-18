package com.morse.ws.security;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailService implements UserDetailsService {

	//@Autowired
	//private ServiceUsuario serviceUsuario;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//FilterUsuario filterUsuario = new FilterUsuario();
		//filterUsuario.setUsername(username);
		//filterUsuario.setActivo(true);
		
		//UsuarioPojo usuario = null;
		
		//try { usuario = serviceUsuario.findOne(filterUsuario); }
		//catch (Exception ex) { }
		
		//if (usuario == null)
		//	throw new UsernameNotFoundException("User " + username + " not found");
		
		//return new UserContext(usuario);
		return new UserContext(null);
	}
}