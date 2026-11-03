package com.dsa.chat.domain;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AnnouncementMapper implements RowMapper<Announcement> {

    @Override
    public Announcement mapRow(ResultSet rs, int rowNum) throws SQLException {
        Announcement announcement = new Announcement();
        announcement.setAnnouncementId(rs.getLong("ANNOUNCEMENT_ID"));
        announcement.setContent(rs.getString("CONTENT"));
        announcement.setSentDatetime(rs.getTimestamp("SENT_DATETIME").toLocalDateTime());
        announcement.setCreationDate(rs.getTimestamp("CREATE_DATETIME").toLocalDateTime());
        announcement.setUpdateDateTime(rs.getTimestamp("UPDATE_DATETIME").toLocalDateTime());

        return announcement;
    }
}
