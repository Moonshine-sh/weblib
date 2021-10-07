package com.WebLib.controllers;

import com.WebLib.domain.Admin;
import com.WebLib.domain.Reader;
import com.WebLib.repos.AdminRepo;
import com.WebLib.repos.ReaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    public static Admin currentAdmin;
    public static Reader currentReader;

    @Autowired
    AdminRepo adminRepo;
    @Autowired
    ReaderRepo readerRepo;


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model){
        Iterable<Admin> admins = adminRepo.findAll();
        Iterable<Reader> readers = readerRepo.findAll();
        for (Admin admin:admins) {
            if(admin.getUsername().equals(username) && admin.getPassword().equals(password)){
                return "redirect:/adminPage/"+admin.getId();
            }
        }
        for (Reader reader:readers) {
            if(reader.getUsername().equals(username) && reader.getPassword().equals(password)){
                return "redirect:/readerPage/"+reader.getId();
            }
        }
        model.addAttribute("error", "invalid username or password");
        return "login";
    }
}
