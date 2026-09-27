package main.java.com.dsa.chat.repository;

import java.util.List;

public interface DsaUserRepository {
    List<DsaUserRepository> retrieveAllUsers();
    com.dsa.chat.domain.UserSearchResponse retrieveUserById(long userId);
    com.dsa.chat.domain.UserSearchResponse retrieveUserByUsername(String username);
    int registerNewDsaUser(com.dsa.chat.entity.DsaUser request);
    int updateDsaUser(com.dsa.chat.entity.DsaUser request);
    int deleteDsaUser(long userId);
}
