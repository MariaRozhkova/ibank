package dev.rozhkova.ibank.config;

import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class MyAppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        final UserEntity activeUserEntity = userRepository.findByLogin(login);
        final GrantedAuthority authority = new SimpleGrantedAuthority(activeUserEntity.getRole());
        return new User(activeUserEntity.getLogin(), activeUserEntity.getPassword(), Arrays.asList(authority));
    }
}