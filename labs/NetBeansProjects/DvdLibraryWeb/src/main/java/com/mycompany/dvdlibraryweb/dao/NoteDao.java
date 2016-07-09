/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.dao;

import com.mycompany.dvdlibraryweb.dto.Notes;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface NoteDao {

    Notes create(Notes note);

    void delete(Notes note);

    Notes get(Integer id);

    void update(Notes note);

    List<Notes> list();
    
    List<Notes> getListOfDvdNotes(Integer id);
    
    Integer getNoteAmount();

}
