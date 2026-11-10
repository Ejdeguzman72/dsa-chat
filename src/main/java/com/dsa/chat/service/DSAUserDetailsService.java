package com.dsa.chat.service;

import com.dsa.chat.domain.DsaUser;
import com.dsa.chat.repository.DsaUserRepository;
import com.dsa.chat.repository.RoleRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DSAUserDetailsService implements UserDetailsService {
    @Autowired
    private DsaUserRepository dsaUserRepository;
    @Autowired
    private RoleRepositoryImpl roleRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(DSAUserDetailsService.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DsaUser user = dsaUserRepository.retrieveUserByUsername(username);
        String roleName = roleRepository.searchRoleById(user.getRoleId()).getName();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(
                        new SimpleGrantedAuthority("ROLE_" + roleName)
                ));
    }
}
