package com.dsa.chat.repository;

import com.dsa.chat.domain.Role;

public interface RoleRepository {
    Role searchRoleById(long roleId);
}
