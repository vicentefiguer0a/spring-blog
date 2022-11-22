package com.example.springblog.controllers;

import com.example.springblog.models.Post;
import com.example.springblog.models.User;
import com.example.springblog.repositories.PostRepository;
import com.example.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class PostController {

    private PostRepository postsDao;

    private UserRepository usersDao;

    public PostController(PostRepository postsDao, UserRepository usersDao) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
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
    public String viewPostForm() {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String submittingNewPost(@RequestParam(name = "post-title") String postTitle, @RequestParam(name = "post-body") String postBody) {
        Post postCreated = new Post(postTitle, postBody);
        User user = usersDao.getById(1L);
        postCreated.setUser(user);
        postsDao.save(postCreated);
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
