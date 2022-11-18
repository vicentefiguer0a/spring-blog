package com.example.springblog.controllers;

import com.example.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String postsIndexPage(Model model) {
        List<Post> posts = new ArrayList<>();
        Post post1 = new Post(1, "My First Post", "This is my first post using spring blog.");
        Post post2 = new Post(2, "My Second Post", "This is my second post using spring blog.");
        posts.add(post1);
        posts.add(post2);
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
    @ResponseBody
    public String viewPostForm() {
        return "Viewing create post form.";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String submittingNewPost() {
        return "Creating new post.";
    }
}
