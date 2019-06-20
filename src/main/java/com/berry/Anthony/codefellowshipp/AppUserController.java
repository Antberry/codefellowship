package com.berry.Anthony.codefellowshipp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.ServiceConfigurationError;

@Controller
public class AppUserController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio){
        AppUser newUser = new AppUser(username, bCryptPasswordEncoder.encode(password),firstName,lastName,dateOfBirth,bio);
        appUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser,null,new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }
// profile page GetMapping for current login user
    @GetMapping("/myprofile")
    public String goToProfile(Principal p, Model model){
        AppUser user = appUserRepository.findByUsername(p.getName());
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/login")
    public String goToLogin(){
        return "login";
    }

    @GetMapping( "/signup")
    public String goToSignUp(){
        return "signup-form";
    }
}
