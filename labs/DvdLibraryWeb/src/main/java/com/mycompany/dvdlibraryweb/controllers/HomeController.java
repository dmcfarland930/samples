/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.controllers;

import com.mycompany.dvdlibraryweb.dao.DvdDao;
import com.mycompany.dvdlibraryweb.dao.NoteDao;
import com.mycompany.dvdlibraryweb.dto.Dvd;
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

    private DvdDao dvdDao;

    @Inject
    public HomeController(DvdDao dao) {
        this.dvdDao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map model) {

        List<Dvd> dvds = dvdDao.list();
        Dvd oldest = dvdDao.findOldestMovie();
        Dvd newest = dvdDao.findNewestMovie();
        int years = dvdDao.findAverageAgeOfMovies();
        int noteNum = dvdDao.findAverageAmountOfNotes();

        model.put("dvds", dvds);
        model.put("oldest", oldest.getTitle());
        model.put("newest", newest.getTitle());
        model.put("years", years);
        model.put("notenum", noteNum);
        model.put("dvd", new Dvd());
        return "home";
    }

}
