package com.alexdyysp.controller;

import com.alexdyysp.model.PostContent;
import com.alexdyysp.service.BlogService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for the blog microservice.
 * @author dyy
 */
@RestController
@CrossOrigin("http://localhost:8000")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BlogController {

    private final BlogService service;

    // 路径直接传入id
    // http://localhost:8080/api/blog/bd3ae648-41ab-42c8-bc9e-4dcd9737f698
    @GetMapping("/api/blog/{id}")
    public Mono<PostContent> getPost(@PathVariable final String id) {
        return service.getPost(id);
    }

    // 传入string类型的id
    // http://localhost:8080/api/blog/string/?id=bd3ae648-41ab-42c8-bc9e-4dcd9737f698
    @GetMapping("/api/blog/string/")
    public Mono<PostContent> getPostByStringId(@RequestParam String id) {
        return service.getPost(id);
    }

    // 全部blogs数据列表
    @GetMapping("/api/blog/allblogs")
    public Flux<PostContent> getPostAll() {
        return service.getAllPostByFlux();
    }

    // 全部blogs的title数据列表
    @GetMapping(value = "/api/blog/alltitles", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> getPostAllTitles() {
        return service.getAllTitlesByFlux();
    }


    // 传入Mono类型的body
    @PostMapping("/api/blog")
    public Mono<String> addPost(@RequestBody Mono<PostContent> content) {
        return service.addPost(content);
    }

    // 传入json类型body
    @PostMapping("/api/blog/json")
    public Mono<String> addPostByJson(@RequestBody PostContent content) {
        return service.addPost(Mono.just(content));
    }

    // 更新对应id的blog
    @PutMapping("/api/blog/{id}")
    public Mono<Void> updatePost(@PathVariable final String id, @RequestBody final Mono<PostContent> content) {
        return service.updatePost(id, content);
    }
}
