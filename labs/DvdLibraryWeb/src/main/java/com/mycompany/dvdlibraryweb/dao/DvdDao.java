/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.dao;
import com.mycompany.dvdlibraryweb.dto.Dvd;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface DvdDao {

    Dvd create(Dvd dvd, Date date);

    List<Dvd> decode();

    void delete(Dvd dvd);

    void encode();

    List<Dvd> getByTitle(String title);

    Dvd get(int id);

    void update(Dvd dvd);

    List<Dvd> list();
    
    List<Dvd> getByMPAA(String mpaa);

    List<Dvd> getByStudio(String studio);
    
    List<Dvd> getMoviesAfterDate(int years);

    List<Dvd> getByDirector(String director);
    
    Dvd findNewestMovie();
    
    Dvd findOldestMovie();
    
    int findAverageAgeOfMovies();
    
    int findAverageAmountOfNotes();
    
    

}
