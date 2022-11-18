package com.example.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postsIndexPage() {
        return "This is the posts index page.";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postsUserPage(@PathVariable long id) {
        return "This post belongs to id#" + id + " only.";
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
