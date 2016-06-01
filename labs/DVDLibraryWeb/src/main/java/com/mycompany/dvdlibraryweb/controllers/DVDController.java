/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.controllers;

import com.mycompany.dvdlibraryweb.dao.DVDDao;
import com.mycompany.dvdlibraryweb.dao.NoteDao;
import com.mycompany.dvdlibraryweb.dto.DVD;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/dvd")
public class DVDController {

    private DVDDao dvdDao;
    private NoteDao noteDao;

    @Inject
    public DVDController(DVDDao dao, NoteDao notes) {
        this.dvdDao = dao;
        this.noteDao = notes;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute DVD dvd, @ModelAttribute Date date) {

        dvdDao.create(dvd, date);
        return "redirect:/";

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@ModelAttribute DVD dvd, Map model) {

        List<DVD> dvdes = dvdDao.list();
        DVD editDVD = new DVD();
        for (DVD myDVD : dvdes) {

            if (Objects.equals(dvd.getId(), myDVD.getId())) {
                editDVD = myDVD;
                break;
            }

        }

        model.put("dvd", editDVD);

        return "edit";
    }

    @RequestMapping(value = "dvd/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute DVD dvd) {

        dvdDao.update(dvd);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@ModelAttribute DVD dvd) {

        dvdDao.delete(dvd);
        return "redirect:/";
    }

}
