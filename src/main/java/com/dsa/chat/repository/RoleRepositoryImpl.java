package com.dsa.chat.repository;

import com.dsa.chat.domain.Role;
import com.dsa.chat.domain.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private JdbcTemplate jdbcTemplate;
    private RoleMapper roleMapper;
    @Autowired
    public RoleRepositoryImpl(JdbcTemplate jdbcTemplate,RoleMapper roleMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.roleMapper = roleMapper;
    }
    public static final String RETRIEVE_ROLE_BY_ID = "SELECT ROLE_ID,ROLE FROM ROLE WHERE ROLE_ID = ?";
    @Override
    public Role searchRoleById(long roleId) {
        Role result = jdbcTemplate.queryForObject(RETRIEVE_ROLE_BY_ID,roleMapper,roleId);
        return result;
    }
}
