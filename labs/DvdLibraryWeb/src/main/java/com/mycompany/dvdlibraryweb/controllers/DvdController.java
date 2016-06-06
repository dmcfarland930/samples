/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.controllers;

import com.mycompany.dvdlibraryweb.dao.DvdDao;
import com.mycompany.dvdlibraryweb.dao.NoteDao;
import com.mycompany.dvdlibraryweb.dto.Dvd;
import com.mycompany.dvdlibraryweb.dto.Notes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
public class DvdController {

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private DvdDao dvdDao;
    private NoteDao noteDao;

    @Inject
    public DvdController(DvdDao dao, NoteDao notes) {
        this.dvdDao = dao;
        this.noteDao = notes;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute Dvd dvd, BindingResult bindingResult, 
            @RequestParam("dvdDate") Date date, Map model) {

        if (bindingResult.hasErrors()) {

            List<Dvd> dvds = dvdDao.decode();
            Dvd oldest = dvdDao.findOldestMovie();
            Dvd newest = dvdDao.findNewestMovie();
            int years = dvdDao.findAverageAgeOfMovies();
            int noteNum = dvdDao.findAverageAmountOfNotes();
            model.put("dvds", dvds);
            model.put("oldest", oldest.getTitle());
            model.put("newest", newest.getTitle());
            model.put("years", years);
            model.put("notenum", noteNum);
            model.put("dvd", dvd);
            return "home";

        }

        
        dvdDao.create(dvd, date);
        Notes newNote = new Notes();
        newNote.setTitle(dvd.getTitle());
        newNote.setDvdId(dvd.getId());
        newNote.setNote(dvd.getNotes());
        noteDao.create(newNote);
        noteDao.setNotesToDvd(dvd);
        return "redirect:/";

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer dvdId, Map model) {

        Dvd dvd = dvdDao.get(dvdId);
        List<Notes> notes = dvd.getNoteList();

        model.put("date", dvd.getDate());
        model.put("notes", notes);
        model.put("dvdShow", dvd);
        model.put("dvd", dvd);

        return "edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editSubmit(@Valid @ModelAttribute Dvd dvd, BindingResult bindingResult,
            @RequestParam("notes") String notes,
            @PathVariable("id") Integer dvdId, Map model) {

        if (bindingResult.hasErrors()) {

            Dvd dvdShow = dvdDao.get(dvdId);
            model.put("dvdShow", dvdShow);
            model.put("date", dvdShow.getDate());
            model.put("notes", dvdShow.getNoteList());
            model.put("dvd", dvd);
            return "edit";

        }

        Notes newNote = new Notes();
        newNote.setTitle(dvd.getTitle());
        newNote.setNote(notes);
        newNote.setDvdId(dvdId);
        noteDao.create(newNote);
        noteDao.setNotesToDvd(dvd);

        dvdDao.update(dvd);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer dvdId) {

        Dvd dvd = dvdDao.get(dvdId);
        dvdDao.delete(dvd);
        return "redirect:/";
    }

    @RequestMapping(value = "/deletenote/{id}", method = RequestMethod.GET)
    public String deleteNote(@PathVariable("id") Integer noteId, Map model) {

        Notes note = noteDao.get(noteId);
        Dvd dvd = dvdDao.get(note.getDvdId());
        int dvdId = note.getDvdId();
        noteDao.delete(note);
        noteDao.setNotesToDvd(dvd);
        
        model.put("dvdId", dvdId);
        model.put("notes", dvd.getNoteList());
        return "redirect:/dvd/show/{dvdId}";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String show(Map model) {

        List<Dvd> dvds = dvdDao.list();
        model.put("dvds", dvds);

        return "search";

    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchSubmit(@RequestParam("entry") String entry,
            @RequestParam("selection") String selection, Map model) {

        List<Dvd> dvds;

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

        Dvd dvd = dvdDao.get(id);
        List<Notes> notes = dvd.getNoteList();
        Date date = dvd.getDvdDate();
        model.put("date", sdf.format(date));
        model.put("notes", notes);
        model.put("dvd", dvd);
        return "show";

    }
}
