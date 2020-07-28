package com.alexdyysp.dao;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * A blog post entity for storage and retrieval in Cassandra.
 *
 * @author dyy
 */
@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@Table
public class BlogPost {
    @PrimaryKey
    @NonNull
    UUID id;
    @NonNull
    String title;
    @NonNull
    String author;
    @NonNull
    String body;
}
