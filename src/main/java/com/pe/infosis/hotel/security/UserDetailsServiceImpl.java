package com.pe.infosis.hotel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pe.infosis.hotel.entity.Usuario;
import com.pe.infosis.hotel.repository.UsuarioRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Qualifier("userImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioDao;
    
    @Override
    public UserDetails loadUserByUsername(final String username) {
       
    	Usuario usuario = usuarioDao.findByUsername(username);
    	
    	if(usuario == null) {
    		throw new UsernameNotFoundException("Usuario no encontrado");
    	}
    	
    	return this.userBuilder(username, new BCryptPasswordEncoder().encode(usuario.getPassword()), usuario.getRol().getRol());
    	
    }

    private User userBuilder(String username, String password, String... roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return new User(username, password, true, true, true,
                true, authorities);
    }
}
