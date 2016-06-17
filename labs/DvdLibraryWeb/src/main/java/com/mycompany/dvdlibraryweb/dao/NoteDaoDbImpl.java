/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.dao;

import com.mycompany.dvdlibraryweb.dto.Notes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class NoteDaoDbImpl implements NoteDao {

    private static final String SQL_INSERT_NOTES = "INSERT INTO notes (dvd_id, note_details) VALUES (?, ?)";
    private static final String SQL_UPDATE_NOTES = "UPDATE notes SET dvd_id = ?, note_details = ? WHERE id = ?";
    private static final String SQL_DELETE_NOTES = "DELETE FROM notes WHERE id = ?";
    private static final String SQL_GET_NOTES = "SELECT * FROM notes WHERE id = ?";
    private static final String SQL_GET_NOTES_LIST = "SELECT * FROM notes";
    private static final String SQL_GET_NOTES_FOR_DVD_LIST = "SELECT * FROM notes n INNER JOIN dvd d ON n.dvd_id = d.id WHERE dvd_id = ?";
    private static final String SQL_GET_NOTES_COUNT = "SELECT COUNT(*) FROM dvd";
    

    private JdbcTemplate jdbcTemplate;

    public NoteDaoDbImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Notes create(Notes note) {
     jdbcTemplate.update(SQL_INSERT_NOTES,
                note.getDvdId(),
                note.getNote());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        note.setNoteId(id);

        return note;
    
    }

    @Override
    public void delete(Notes note) {
    
         
        jdbcTemplate.update(SQL_DELETE_NOTES,
                note.getNoteId());

    
    }

    @Override
    public Notes get(Integer id) {
       
        return jdbcTemplate.queryForObject(SQL_GET_NOTES, new NotesMapper(), id);
}

    @Override
    public void update(Notes note) {
         jdbcTemplate.update(SQL_UPDATE_NOTES,
                
                note.getDvdId(),
                note.getNote(),
                note.getNoteId());
     }

    @Override
    public List<Notes> list() {
        
        
        return jdbcTemplate.query(SQL_GET_NOTES_LIST, new NotesMapper());
        
    }

    @Override
    public Integer getNoteAmount() {
    
        return jdbcTemplate.queryForObject(SQL_GET_NOTES_COUNT, Integer.class);
        
    }

    @Override
    public List<Notes> getListOfDvdNotes(Integer id) {
        
        return jdbcTemplate.query(SQL_GET_NOTES_FOR_DVD_LIST, new NotesMapper(), id);
    
    }


    private static final class NotesMapper implements RowMapper<Notes> {

        @Override
        public Notes mapRow(ResultSet rs, int i) throws SQLException {

            Notes note = new Notes();

            note.setNoteId(rs.getInt("id"));
            note.setDvdId(rs.getInt("dvd_id"));
            note.setNote(rs.getString("note_details"));

            return note;
        }

    }

}
