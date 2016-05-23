/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.dto.DVD;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface DVDDao {

    DVD create(DVD dvd, Date date);

    List decode();

    void delete(DVD dvd);

    void encode();

    DVD get(String title);

    DVD get(int id);

    void update(DVD dvd);

    List getByMPAA(String mpaa);

    List getByStudio(String studio);
    
    List getMoviesAfterDate(int years);

    Map getByDirector(String director);
    
    DVD findNewestMovie();
    
    DVD findOldestMovie();
    
    int findAverageAgeOfMovies();
    
    int findAverageAmountOfNotes();
    
    

}
