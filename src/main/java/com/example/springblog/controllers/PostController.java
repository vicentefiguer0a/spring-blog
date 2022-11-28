package com.example.springblog.controllers;

import com.example.springblog.models.Post;
import com.example.springblog.models.User;
import com.example.springblog.repositories.PostRepository;
import com.example.springblog.repositories.UserRepository;
import com.example.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class PostController {

    private PostRepository postsDao;

    private UserRepository usersDao;

    private EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository usersDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String postsIndexPage(Model model) {
        List<Post> posts = postsDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postsUserPage(@PathVariable long id, Model model) {
        Post post = postsDao.getById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String submittingNewPost(@ModelAttribute Post post) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(loggedInUser);
        postsDao.save(post);

        emailService.prepareAndSend(post, "New Post Created!", "A new post has been created with the title of " + post.getTitle());

        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editUserPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.getReferenceById(id));
        return "posts/edit-post";
    }

    @PostMapping("/posts/edit")
    public String submitEditedPost(@ModelAttribute Post post) {
        User user = usersDao.getById(1L);
        post.setUser(user);
        postsDao.save(post);
        return "redirect:/posts";
    }

}
