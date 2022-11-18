package com.example.springblog.controllers;

import com.example.springblog.models.Post;
import com.example.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;

    public PostController(PostRepository postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")
    public String postsIndexPage(Model model) {
        List<Post> posts = postsDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postsUserPage(@PathVariable long id, Model model) {
        Post post = new Post(id, "My Individual Post", "This is my individual post.");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewPostForm() {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String submittingNewPost(@RequestParam(name = "post-title") String postTitle, @RequestParam(name = "post-body") String postBody, Model model) {
        Post postCreated = new Post(postTitle, postBody);
        postsDao.save(postCreated);
        return "redirect:/posts";
    }
}
