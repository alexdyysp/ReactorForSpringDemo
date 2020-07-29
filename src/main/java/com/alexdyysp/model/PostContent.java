package com.alexdyysp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * REST API data format for blog posts.
 *
 * @author dyy
 */
@Value
@Builder
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class PostContent {
    @NonNull
    String title;
    @NonNull
    String author;
    @NonNull
    String body;
}
