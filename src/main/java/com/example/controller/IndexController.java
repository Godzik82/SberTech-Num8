package com.example.controller;

import com.example.config.MyUserDetailsService;
import com.example.models.User;
import com.example.repositories.UserRepository;
import com.example.services.NotesService;
import com.example.services.UsersService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
// @RequestMapping("/registration")
@RequiredArgsConstructor
public class IndexController {

    private final UsersService usersService;

    // @Autowired
    // private UserRepository userRepository;

    @GetMapping("/")
    public String homePage (Model model) {
        // if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
        //     // Long userid = usersService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        //     return "redirect:/notes";
        // }
            
        // model.addAttribute("notesList", notesService.listNotes());
        // model.addAttribute("note", new Note());
        return "base1";
    }

    @PostMapping("/registration")
    public String newUser(@RequestParam String name, @RequestParam String email, @RequestParam String password, Model model) {
        // log.info(login);
        User user = new User(name, email, password);
        usersService.newUser(user);
        log.info(user.getName());
        // usersService.saveUser(user);
        model.addAttribute("user", user);
        // model.addAttribute("userName", user.getName());
        // model.addAttribute("notes", notesService.allUserNotes(user.getId()));
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration() {
        log.info("GetMapping");
        return "registration";
    }

    @PostMapping("/login")
    public String login () {
        log.info("isAuth " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            //     // Long userid = usersService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
            return "redirect:/notes";
            }
        
        return "redirect:/";
        }

    @GetMapping("/login")
    public String loginUser() {
        return "login";
    }


    
}   