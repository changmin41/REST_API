package com.back.domain.post.post.controller;

import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//@Controller + @ResponseBody
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ApiV1PostController {
    private final PostService postService;

    @GetMapping
    @ResponseBody
    public List<Post> getItems() {
        List<Post> items = postService.getList();

        return items;
    }
    @GetMapping("/{id}")
    public Post getItem(@PathVariable Long id) {
        Post item = postService.getPost(id);

        return item;
    }
}