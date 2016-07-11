/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.controller;

import com.mycompany.televisionblog.dao.BlogPostDao;
import com.mycompany.televisionblog.dao.CategoryDao;
import com.mycompany.televisionblog.dao.PageDao;
import com.mycompany.televisionblog.dao.TagDao;
import com.mycompany.televisionblog.dao.UserDao;
import com.mycompany.televisionblog.dto.BlogPost;
import com.mycompany.televisionblog.dto.Category;
import com.mycompany.televisionblog.dto.Page;
import com.mycompany.televisionblog.dto.Tag;
import com.mycompany.televisionblog.dto.User;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping
public class HomeController {

    
    SimpleDateFormat sdfDisplay = new SimpleDateFormat("MMMM dd, yyyy");
    private BlogPostDao postDao;
    private PageDao pageDao;
    private UserDao userDao;
    private CategoryDao categoryDao;
    private TagDao tagDao;
    
    @Inject
    public HomeController(BlogPostDao postDao, PageDao pageDao, UserDao userDao, CategoryDao categoryDao, TagDao tagDao) {
        this.postDao = postDao;
        this.pageDao = pageDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.tagDao = tagDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map<String, Object> model) {

        List<BlogPost> posts = postDao.listOfThree(0, 3);
        List<Category> categories = categoryDao.list();
        List<Tag> tags = tagDao.list();
        
        for (BlogPost blogView : posts) {

            blogView.setStringDateDisplay(sdfDisplay.format(blogView.getPostDate()));
            model.put("title", blogView.getTitle());
            model.put("author", blogView.getUser().getFirstName() + " " + blogView.getUser().getLastName());
            model.put("posts", posts);
        }
        
        
        boolean nextPage = postDao.checkIfNextPage(3, 3);
        List<Page> pages = pageDao.list();
        
        model.put("tags", tags);
        model.put("categories", categories);
        model.put("pages", pages);
        model.put("pageNext", 2);
        model.put("nextPage", nextPage);
        model.put("hidden", "hidden");
        return "/home";
    }

    @RequestMapping(value = "/{url}", method = RequestMethod.GET)
    public String get(@PathVariable("url") String url, Map model) {

        Page page;
        try {
            page = pageDao.get(url);
        } catch (EmptyResultDataAccessException e) {
            return "404";
        }
        User user = userDao.get(page.getUser().getId());

        page.setUser(user);
        List<Page> pages = pageDao.list();

        model.put("pages", pages);
        model.put("page", page);

        return "showPage";
    }
}