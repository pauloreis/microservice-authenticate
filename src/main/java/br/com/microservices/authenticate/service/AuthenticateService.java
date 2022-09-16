package br.com.microservices.authenticate.service;

import br.com.microservices.authenticate.model.Profile;
import br.com.microservices.authenticate.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticateService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = User.builder()
                .id(1L)
                .email("paulo@teste.com")
                .password("$2a$10$mb5tI320VkDA6kLeuhTFuejyKCKKuz2QDMpMgRPPo52511yeQ4gTS")
                .profiles(List.of(Profile.builder().id(1L).name("ADM").build()))
                .build();
        return user;
    }
}