/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.dao;
import com.mycompany.dvdlibraryweb.dto.Dvd;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface DvdDao {

    Dvd create(Dvd dvd);

    void delete(Dvd dvd);

    List<Dvd> getByTitle(String title);

    Dvd get(Integer id);

    void update(Dvd dvd);

    List<Dvd> list();
    
    List<Dvd> getByMPAA(String mpaa);

    List<Dvd> getByStudio(String studio);
    
    List<Dvd> getMoviesAfterDate(int years);

    List<Dvd> getByDirector(String director);
    
    Dvd findNewestMovie();
    
    Dvd findOldestMovie();
    
    int findAverageAgeOfMovies();
    
    

}
