package com.alexdyysp;

import java.util.UUID;

import com.alexdyysp.controller.BlogController;
import com.alexdyysp.model.PostContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReactorExampleApplicationTests {

    @Autowired
    private BlogController controller;

    @Test
    public void sanityTests() throws Exception {
        final Mono<PostContent> nonexistent = controller.getPost(UUID.randomUUID().toString());
        assertThat(nonexistent.hasElement().block()).isFalse();

        PostContent postContent = new PostContent("Title", "Author", "Body");
        final String id = controller.addPost(Mono.just(postContent)).block();
        final Mono<PostContent> contentMono = controller.getPost(id);
        assertThat(contentMono.block()).isEqualTo(postContent);

        PostContent updateContent = new PostContent("Title", "Author2","Other body for update");
        controller.updatePost(id, Mono.just(updateContent)).block();
        final Mono<PostContent> updatedMono = controller.getPost(id);
        assertThat(updatedMono.block()).isEqualTo(updateContent);
    }


}
