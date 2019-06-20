package com.berry.Anthony.codefellowshipp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Timestamp;

@Controller
public class PostController {


    @Autowired
    PostRepository postRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/addpost")
    public String goToAddPost(){ return "post";}

    @PostMapping("/addposts")
    public RedirectView createPost(String body, Principal p, Model m){
        AppUser user = appUserRepository.findByUsername(p.getName());
        Timestamp time = new Timestamp(System.currentTimeMillis());
        UserPost userPost = new UserPost(body, time, user);
        postRepository.save(userPost);
        m.addAttribute("user", user);
        m.addAttribute("posts", userPost);
        return new RedirectView("/myprofile");

    }

}
