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
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BLOGPOST {

    @Id
    String id;

    String title;

    String author;

    String body;



}
