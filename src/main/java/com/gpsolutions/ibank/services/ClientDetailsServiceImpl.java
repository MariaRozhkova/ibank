/*
package com.gpsolutions.ibank.services;

import com.gpsolutions.ibank.dbo.UserDbo;
import com.gpsolutions.ibank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class ClientDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDbo userDbo = userRepository.findByLogin(login);
        //GrantedAuthority grantedAuthorities = new SimpleGrantedAuthority(userDbo.getRole().getRoleName());
        //return new org.springframework.security.core.userdetails.User(userDbo.getLogin(), userDbo.getPassword(), (Collection<? extends GrantedAuthority>) grantedAuthorities);

        return new User(userDbo.getLogin(), userDbo.getPassword(), AuthorityUtils.createAuthorityList(String.valueOf(userDbo.getRole_id())));
    }
}*/
