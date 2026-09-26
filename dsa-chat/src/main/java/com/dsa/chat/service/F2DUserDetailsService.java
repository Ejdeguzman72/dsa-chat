package com.dsa.chat.service;

import com.dsa.chat.entity.DsaUser;
import com.dsa.chat.repository.DsaUserDaoImpl;
import com.dsa.chat.repository.F2DUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DSAUserDetailsService implements UserDetailsService {

    @Autowired
    private DsaUserDaoImpl dasUserDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(DSAUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DsaUser user = dasUserDao.findByUsername(username);

        LOGGER.info("Finding user: " + username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
