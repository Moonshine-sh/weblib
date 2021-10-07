package com.WebLib.controllers;

import com.WebLib.domain.Admin;
import com.WebLib.domain.Book;
import com.WebLib.domain.Intertable;
import com.WebLib.domain.Reader;
import com.WebLib.repos.BookRepo;
import com.WebLib.repos.IntertableRepo;
import com.WebLib.repos.ReaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReaderController {
@Autowired
    ReaderRepo readerRepo;
@Autowired
    BookRepo bookRepo;
@Autowired
    IntertableRepo intertableRepo;

    @GetMapping("/readerPage/{id}")
    public String reader(@PathVariable(value = "id") Integer id, Model model){
        Reader reader = readerRepo.findById(id).orElseThrow();
        Iterable<Book> books = bookRepo.findAll();
        model.addAttribute("books",books);
        model.addAttribute("reader",reader);
        return "readerPage";
    }

    @GetMapping("/readerPage/{id}/edit")
    public String editUser(@PathVariable(name = "id") Integer id, Model model){
        Reader reader = readerRepo.findById(id).orElseThrow();
        model.addAttribute("reader",reader);
        return "editReader";
    }

    @PostMapping("/editReader")
    public String editUser(@RequestParam Integer id,@RequestParam String name,
                           @RequestParam String surname, @RequestParam String mobNumber,
                           @RequestParam String username,@RequestParam String password, Model model){
        Reader reader = readerRepo.findById(id).orElseThrow();
        reader.setName(name);
        reader.setSurname(surname);
        reader.setMobNumber(mobNumber);
        reader.setUsername(username);
        reader.setPassword(password);
        readerRepo.save(reader);
        return "redirect:/readerPage/"+id;
    }

    @GetMapping("/readerPage/{id}/add/{bookid}")
    public String addBook(@PathVariable(name = "id") Integer id,
                          @PathVariable(name = "bookid") Integer bookid, Model model){
        Intertable intertable = new Intertable(bookid,id);
        Book book = bookRepo.findById(bookid).orElseThrow();
        book.setStock(book.getStock()-1);
        bookRepo.save(book);
        intertableRepo.save(intertable);
        return "redirect:/readerPage/"+id;
    }

    @GetMapping("/readerPage/{id}/mybooklist")
    public String mylist(@PathVariable(name = "id") Integer id, Model model){
        List<Intertable> intertables = intertableRepo.findAllByReaderID(id);
        List<Book> books = new ArrayList<>();
        for (Intertable intertable: intertables) {
            books.add(bookRepo.findById(intertable.getBookID()).orElseThrow());
        }
        model.addAttribute("books",books);
        model.addAttribute("ID",id);
        return "booklist";
    }

    @GetMapping("/readerPage/{id}/mybooklist/delete/{bookid}")
    public String deletebook(@PathVariable(name = "id") Integer id, @PathVariable(name = "bookid") Integer bookid, Model model){
        List<Intertable> intertable = intertableRepo.findAllByBookID(bookid);
        Book book = bookRepo.findById(bookid).orElseThrow();
        book.setStock(book.getStock()+1);
        bookRepo.save(book);
        intertableRepo.delete(intertable.get(0));
        return "redirect:/readerPage/"+id+"/mybooklist/";
    }
}
