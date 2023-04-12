package com.xiaolin.chat.security;

import com.xiaolin.chat.model.entity.User;
import com.xiaolin.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.Optional;

/**
 * TODO 不知道@Transactional注解的含意，所以暂时没有增加该注解
 * @author xlxing
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorityUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("username not found: " + this.getClass().getName());
        }
        User user = userOptional.get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("USER")));
    }
}
