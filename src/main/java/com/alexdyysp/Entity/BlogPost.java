package com.alexdyysp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A blog post entity for storage and retrieval in Cassandra.
 *
 * @author dyy
 */
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * A blog post entity for storage and retrieval in Cassandra.
 *
 * @author Matt Sicker
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