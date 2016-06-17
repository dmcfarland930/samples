/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.dao;

import com.mycompany.dvdlibraryweb.dto.Dvd;
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
public class DVDDaoDbImpl implements DvdDao {

    private static final String SQL_INSERT_DVD = "INSERT INTO dvd (title, director, rating, studio, release_date) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_DVD = "UPDATE dvd SET title = ?, director = ?, rating = ?, studio = ?, release_date = ? WHERE id = ?";
    private static final String SQL_DELETE_DVD = "DELETE FROM dvd WHERE id = ?";
    private static final String SQL_GET_DVD = "SELECT * FROM dvd WHERE id = ?";
    private static final String SQL_GET_DVD_LIST = "SELECT * FROM dvd";
    private static final String SQL_GET_NOTE_LIST = "SELECT note_details FROM dvd d INNER JOIN notes n ON d.id = n.dvd_id WHERE dvd_id = ?";
    private static final String SQL_GET_TITLE_LIST = "SELECT * FROM dvd WHERE last_name = ?";
    private static final String SQL_GET_DIRECTOR_LIST = "SELECT * FROM dvd WHERE city = ?";
    private static final String SQL_GET_STUDIO_LIST = "SELECT * FROM dvd WHERE state = ?";
    private static final String SQL_GET_TIME_LIST = "SELECT * FROM dvd WHERE zip = ?";

    private JdbcTemplate jdbcTemplate;

    public DVDDaoDbImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Dvd create(Dvd dvd) {

        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getTitle(),
                dvd.getDirector(),
                dvd.getRating(),
                dvd.getStudio(),
                dvd.getDvdDate());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        dvd.setId(id);

        return dvd;
    }

    @Override
    public void update(Dvd dvd) {

        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getTitle(),
                dvd.getDirector(),
                dvd.getRating(),
                dvd.getStudio(),
                dvd.getDvdDate(),
                dvd.getId());

    }

    @Override
    public void delete(Dvd dvd) {

        jdbcTemplate.update(SQL_DELETE_DVD,
                dvd.getId());

    }

    @Override
    public Dvd get(Integer id) {

        return jdbcTemplate.queryForObject(SQL_GET_DVD, new DvdMapper(), id);

    }

    @Override
    public List<Dvd> list() {

        return jdbcTemplate.query(SQL_GET_DVD_LIST, new DvdMapper());

    }
   

    @Override
    public List<Dvd> getByTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dvd> getByMPAA(String mpaa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dvd> getByStudio(String studio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dvd> getMoviesAfterDate(int years) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dvd> getByDirector(String director) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dvd findNewestMovie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dvd findOldestMovie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int findAverageAgeOfMovies() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static final class DvdMapper implements RowMapper<Dvd> {

        @Override
        public Dvd mapRow(ResultSet rs, int i) throws SQLException {

            Dvd dvd = new Dvd();

            dvd.setId(rs.getInt("id"));
            dvd.setTitle(rs.getString("title"));
            dvd.setDirector(rs.getString("director"));
            dvd.setRating(rs.getString("rating"));
            dvd.setStudio(rs.getString("studio"));
            dvd.setDvdDate(rs.getDate("release_date"));

            return dvd;

        }

    }

}
