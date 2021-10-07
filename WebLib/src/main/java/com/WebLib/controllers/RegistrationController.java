package com.WebLib.controllers;

import com.WebLib.domain.Reader;
import com.WebLib.repos.ReaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired
    ReaderRepo readerRepo;
    @GetMapping("/registration")
    public String register(){
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@RequestParam String name, @RequestParam String surname,
                           @RequestParam String mobNumber, @RequestParam String username, @RequestParam String password, Model model){
        Iterable<Reader> readers = readerRepo.findAll();
        for (Reader reader:readers) {
            if(reader.getUsername().equals(username)){
                model.addAttribute("error","this username is already in use");
                return "registration";
            }
        }
        Reader reader = new Reader(name,surname,mobNumber,username,password);
        readerRepo.save(reader);
        return "login";
    }
}
