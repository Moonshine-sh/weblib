package com.WebLib.controllers;

import com.WebLib.domain.Admin;
import com.WebLib.domain.Book;
import com.WebLib.domain.Reader;
import com.WebLib.repos.AdminRepo;
import com.WebLib.repos.BookRepo;
import com.WebLib.repos.ReaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    ReaderRepo readerRepo;

    @GetMapping("/adminPage/{id}")
    public String admin(@PathVariable(value = "id") Integer id, Model model){
        Iterable<Book> books = bookRepo.findAll();
        Admin admin = adminRepo.findById(id).orElseThrow();
        model.addAttribute("books",books);
        model.addAttribute("admin",admin);
        return "adminPage";
    }
    @GetMapping("/adminPage/{id}/delete/{book}")
    public String delete(@PathVariable(name = "book") Integer book,@PathVariable(name = "id") Integer id ,Model model){
        bookRepo.deleteById(book);
        return "redirect:/adminPage/"+id;
    }

    @GetMapping("/adminPage/{id}/edit/{book}")
    public String edit(@PathVariable(name = "book") Integer book,@PathVariable(name = "id") Integer id ,Model model){
        Book book1 = bookRepo.findById(book).orElseThrow();
        model.addAttribute("book",book1);
        model.addAttribute("ID",id);
        return "editBook";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam Integer id,@RequestParam String name,@RequestParam String author,
                       @RequestParam Integer yearOfPubl,@RequestParam Integer stock,@RequestParam Integer genre,@RequestParam Integer ID, Model model){
        Book book = bookRepo.findById(id).orElseThrow();
        book.setName(name);
        book.setAuthor(author);
        book.setYearOfPubl(yearOfPubl);
        book.setGenreId(genre);
        book.setStock(stock);
        bookRepo.save(book);
        return "redirect:/adminPage/"+ID;
    }

    @GetMapping("/adminPage/{id}/add")
    public String add(@PathVariable Integer id, Model model){
        model.addAttribute("ID",id);
        return "addBook";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name,@RequestParam String author,
                      @RequestParam Integer yearOfPubl,@RequestParam Integer stock,@RequestParam Integer genre,
                      @RequestParam Integer ID, Model model){
        Book book = new Book(name,author,yearOfPubl,genre,stock);
        try{
        bookRepo.save(book);}
        catch (Exception ex){

        }
        return "redirect:/adminPage/"+ID;
    }

    @GetMapping("/adminPage/{id}/readerlist")
    public String users(@PathVariable(name = "id") Integer id,Model model){
        Iterable<Reader> readers = readerRepo.findAll();
        model.addAttribute("readers",readers);
        model.addAttribute("ID",id);
        return "readerlist";
    }

    @GetMapping("/adminPage/{id}/readerlist/delete/{readerid}")
    public String deleteUser(@PathVariable(name = "id") Integer id, @PathVariable(name = "readerid") Integer readerid, Model model){
        readerRepo.deleteById(readerid);
        return "redirect:/adminPage/"+id+"/readerlist/";
    }
}
