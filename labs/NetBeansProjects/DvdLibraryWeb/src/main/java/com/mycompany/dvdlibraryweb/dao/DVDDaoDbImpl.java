/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.dao;

import com.mycompany.dvdlibraryweb.dto.Dvd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 * @author apprentice
 */
public class DVDDaoDbImpl implements DvdDao {

    private static final String SQL_INSERT_DVD = "INSERT INTO dvd (title, director, rating, studio, release_date) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_DVD = "UPDATE dvd SET title = ?, director = ?, rating = ?, studio = ?, release_date = ? WHERE id = ?";
    private static final String SQL_DELETE_DVD = "DELETE FROM dvd WHERE id = ?";
    private static final String SQL_DELETE_NOTES = "DELETE FROM notes where dvd_id = ?";
    private static final String SQL_GET_DVD = "SELECT * FROM dvd WHERE id = ?";
    private static final String SQL_GET_DVD_LIST = "SELECT * FROM dvd";
    private static final String SQL_GET_TITLE_LIST = "SELECT * FROM dvd WHERE title = ?";
    private static final String SQL_GET_DIRECTOR_LIST = "SELECT * FROM dvd WHERE director = ?";
    private static final String SQL_GET_STUDIO_LIST = "SELECT * FROM dvd WHERE studio = ?";
    private static final String SQL_GET_RATING_LIST = "SELECT * FROM dvd WHERE rating = ?";

    private static final String SQL_GET_MOVIE_NEWEST = "SELECT MAX(release_date) FROM dvd";
    private static final String SQL_GET_MOVIE_OLDEST = "SELECT MIN(release_date) FROM dvd";
    private static final String SQL_GET_DATES = "SELECT (release_date) FROM dvd";

    private JdbcTemplate jdbcTemplate;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");

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

        
        jdbcTemplate.update(SQL_DELETE_NOTES,
                dvd.getId());
        
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

        return jdbcTemplate.query(SQL_GET_TITLE_LIST, new DvdMapper(), title);
    }

    @Override
    public List<Dvd> getByMPAA(String mpaa) {
        return jdbcTemplate.query(SQL_GET_RATING_LIST, new DvdMapper(), mpaa);
    }

    @Override
    public List<Dvd> getByStudio(String studio) {
        return jdbcTemplate.query(SQL_GET_STUDIO_LIST, new DvdMapper(), studio);
    }

    @Override
    public List<Dvd> getByDirector(String director) {
        return jdbcTemplate.query(SQL_GET_DIRECTOR_LIST, new DvdMapper(), director);
    }

    @Override
    public List<Dvd> getMoviesAfterDate(int years) {

        Date date = new Date();
        List<Dvd> listOfDvds = jdbcTemplate.query(SQL_GET_DVD_LIST, new DvdMapper());
        List<Dvd> dvdWithinTime = new ArrayList();

        for (Dvd dvd : listOfDvds) {

            int year = date.getYear() - years;
            int dvdYear = dvd.getDvdDate().getYear();

            if (dvdYear >= year) {
                dvdWithinTime.add(dvd);
            }

        }

        return dvdWithinTime;

    }

    @Override
    public List<Dvd> findNewestMovie() {

        Date foundDate = jdbcTemplate.queryForObject(SQL_GET_MOVIE_NEWEST, Date.class);
        String foundDateString = sdf.format(foundDate);
        List<Dvd> dvds = list();
        List<Dvd> dvdMatch = new ArrayList();
        for (Dvd dvd : dvds) {

            String dvdDateString = sdf.format(dvd.getDvdDate());
            if (dvdDateString.equals(foundDateString)) {
                dvdMatch.add(dvd);
            }

        }

        return dvdMatch;

    }

    @Override
    public List<Dvd> findOldestMovie() {
        Date foundDate = jdbcTemplate.queryForObject(SQL_GET_MOVIE_OLDEST, Date.class);
        String foundDateString = sdf.format(foundDate);
        List<Dvd> dvds = list();
        List<Dvd> dvdMatch = new ArrayList();
        for (Dvd dvd : dvds) {

            String dvdDateString = sdf.format(dvd.getDvdDate());
            if (dvdDateString.equals(foundDateString)) {
                dvdMatch.add(dvd);
            }

        }
        return dvdMatch;
    }

    @Override
    public int findAverageAgeOfMovies() {

        int sum = 0;
        List<Date> listOfDates = jdbcTemplate.query(SQL_GET_DATES, new DateMapper());
        List<Integer> years = new ArrayList();
        Date currentDate = new Date();

        for (Date dvdDate : listOfDates) {

            String currentDateString = sdf.format(currentDate);
            String dvdDateString = sdf.format(dvdDate);

            String currentYear = currentDateString.substring(0, 4);
            String dvdYear = dvdDateString.substring(0, 4);

            int currentYearInt = Integer.parseInt(currentYear);
            int dvdYearInt = Integer.parseInt(dvdYear);

            years.add(currentYearInt - dvdYearInt);
        }

        for (Integer year : years) {

            sum += year;

        }

        int avg = sum / years.size();

        return avg;

    }

    private class DvdMapper implements RowMapper<Dvd> {

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

    private class DateMapper implements RowMapper<Date> {

        @Override
        public Date mapRow(ResultSet rs, int i) throws SQLException {

            Date dvdDate;

            dvdDate = rs.getDate("release_date");

            return dvdDate;

        }

    }

}
