/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.controllers;

import com.mycompany.dvdlibraryweb.dao.DvdDao;
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
        
            
        List<Dvd> oldest = dvdDao.findOldestMovie();
        List<Dvd> newest = dvdDao.findNewestMovie();
        int years = dvdDao.findAverageAgeOfMovies();
        model.put("oldest", oldest);
        model.put("newest", newest);
        model.put("years", years);
        
        
//            
//            
//        model.put("oldest", "You have no movies!");
//        model.put("newest", "You have no movies!");
//        model.put("years", "0");
//        model.put("notenum", "0");
//        
            
        
        
        model.put("dvds", dvds);
        model.put("dvd", new Dvd());
        return "home";
    }

}
