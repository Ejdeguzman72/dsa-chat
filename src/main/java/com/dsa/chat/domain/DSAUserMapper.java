package com.dsa.chat.domain;

import com.dsa.chat.entity.DsaUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DSAUserMapper implements RowMapper<DsaUser> {

    @Override
    public DsaUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        DsaUser dsaUser = new DsaUser();
        dsaUser.setUsername(rs.getString("USERNAME"));
        dsaUser.setPassword(rs.getString("PASSWORD"));
        dsaUser.setFirstname(rs.getString("FIRSTNAME"));
        dsaUser.setLastname(rs.getString("LASTNAME"));
        dsaUser.setEmail(rs.getString("EMAIL"));
        dsaUser.setDescription(rs.getString("DESCRIPTION"));
        dsaUser.setCreationDate(rs.getDate("CREATION_DATE").toLocalDate());
        dsaUser.setLastUpdatetime(rs.getDate("LAST_UPDATETIME").toLocalDate());
        return dsaUser;
    }
}
