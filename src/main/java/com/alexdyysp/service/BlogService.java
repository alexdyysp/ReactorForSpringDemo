package com.alexdyysp.service;

import java.util.UUID;

import com.alexdyysp.Entity.BLOGPOST;
import com.alexdyysp.dao.BlogRepository;
import com.alexdyysp.model.PostContent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Service to manage blog posts asynchronously via reactive stream APIs.
 *
 * @author dyy
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired)) // may not be necessary anymore in new versions of spring?
public class BlogService {

    private final BlogRepository repository;

    public Mono getPost(final String id) {
        return Mono.defer(
                () -> Mono.justOrEmpty(repository.findById(id)))
                .subscribeOn(Schedulers.elastic())
                .map(post -> new PostContent(
                        post.getTitle(),
                        post.getAuthor(),
                        post.getBody()
                        )
                );
    }

    public Mono<String> addPost(final Mono<PostContent> contentMono) {
        return contentMono
            .map(content -> new BLOGPOST(UUID.randomUUID().toString(), content.getTitle(), content.getAuthor(), content.getBody()))
            .publishOn(Schedulers.parallel())
            .doOnNext(repository::save)
            .map(BLOGPOST::getId);
    }

    public Mono<Void> updatePost(final String id, final Mono<PostContent> contentMono) {
        return contentMono
            .map(content -> new BLOGPOST(id, content.getTitle(), content.getAuthor(), content.getBody()))
            .publishOn(Schedulers.parallel())
            .doOnNext(repository::save)
            .then();
    }


    // NOTICE: 用了Flux可以不用在写列表了，流帮你解决了所有问题
    public Flux<PostContent> getAllPostByFlux(){
        return Flux.fromIterable(repository.findAll())
                .map(post -> new PostContent(
                        post.getTitle(),
                        post.getAuthor(),
                        post.getBody()
                ));
    }

    public Flux<char[]> getAllTitlesByFlux(){
        return Flux.fromIterable(repository.findAll())
                .map(post -> post.getTitle().toCharArray());
    }

}
