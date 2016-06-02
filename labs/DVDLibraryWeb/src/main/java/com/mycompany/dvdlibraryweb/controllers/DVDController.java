/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.controllers;

import com.mycompany.dvdlibraryweb.dao.DVDDao;
import com.mycompany.dvdlibraryweb.dao.NoteDao;
import com.mycompany.dvdlibraryweb.dto.DVD;
import com.mycompany.dvdlibraryweb.dto.Notes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/dvd")
public class DVDController {

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private DVDDao dvdDao;
    private NoteDao noteDao;

    @Inject
    public DVDController(DVDDao dao, NoteDao notes) {
        this.dvdDao = dao;
        this.noteDao = notes;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute DVD dvd) {

        Date date = new Date();
        try {

            date = sdf.parse(dvd.getDate());

        } catch (ParseException ex) {
            Logger.getLogger(DVDController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Notes newNote = new Notes();
        newNote.setTitle(dvd.getTitle());
        newNote.setNote(dvd.getNotes());
        noteDao.create(newNote, dvd.getId());
        dvdDao.create(dvd, date);
        return "redirect:/";

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer dvdId, Map model) {

        DVD dvd = dvdDao.get(dvdId);
        List<Notes> notes = dvd.getNoteList();
        Date date = dvd.getDvdDate();
        model.put("date", sdf.format(date));
        model.put("notes", notes);
        model.put("dvd", dvd);

        return "edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editSubmit(@ModelAttribute DVD dvd,
            @RequestParam("date") String date, @RequestParam("notes") String notes, Map model) {

        try {
            dvd.setDvdDate(sdf.parse(date));
        } catch (ParseException ex) {
            Logger.getLogger(DVDController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Notes newNote = new Notes();
        newNote.setTitle(dvd.getTitle());
        newNote.setNote(notes);
        noteDao.create(newNote, dvd.getId());
        noteDao.setNotesToDVD(dvd);
        
        dvdDao.update(dvd);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer dvdId) {

        DVD dvd = dvdDao.get(dvdId);
        dvdDao.delete(dvd);
        return "redirect:/";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String show(Map model) {

        List<DVD> dvds = dvdDao.list();
        model.put("dvds", dvds);

        return "search";

    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchSubmit(@RequestParam("entry") String entry,
            @RequestParam("selection") String selection, Map model) {

        List<DVD> dvds;

        switch (selection) {

            case "title":
                dvds = dvdDao.getByTitle(entry);
                model.put("dvds", dvds);
                return "search";

            case "director":
                dvds = dvdDao.getByDirector(entry);
                model.put("dvds", dvds);
                return "search";

            case "rating":
                dvds = dvdDao.getByMPAA(entry);
                model.put("dvds", dvds);
                return "search";

            case "studio":
                dvds = dvdDao.getByStudio(entry);
                model.put("dvds", dvds);
                return "search";
                
            case "after":
                int years = Integer.parseInt(entry);
                dvds = dvdDao.getMoviesAfterDate(years);
                model.put("dvds", dvds);
                return "search";

            default:
                return "search";
        }
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id, Map model) {

        DVD dvd = dvdDao.get(id);
        List<Notes> notes = dvd.getNoteList();
        Date date = dvd.getDvdDate();
        model.put("date", sdf.format(date));
        model.put("notes", notes);
        model.put("dvd", dvd);
        return "show";

    }
}
