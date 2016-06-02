/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.controllers;

import com.mycompany.dvdlibraryweb.dao.DVDDao;
import com.mycompany.dvdlibraryweb.dao.NoteDao;
import com.mycompany.dvdlibraryweb.dto.DVD;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeController {

    private DVDDao dvdDao;
    private NoteDao noteDao = new NoteDao();

    @Inject
    public HomeController(DVDDao dao, NoteDao notes) {
        this.dvdDao = dao;
        this.noteDao = notes;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map model) {

        List<DVD> dvds = dvdDao.list();
        DVD oldest = dvdDao.findOldestMovie();
        DVD newest = dvdDao.findNewestMovie();
        int years = dvdDao.findAverageAgeOfMovies();
        int noteNum = dvdDao.findAverageAmountOfNotes();

        model.put("dvds", dvds);
        model.put("oldest", oldest.getTitle());
        model.put("newest", newest.getTitle());
        model.put("years", years);
        model.put("notenum", noteNum);
        return "home";
    }

}
