package com.alexdyysp.dao;

import java.util.UUID;

import com.alexdyysp.Entity.BlogPost;
import org.springframework.data.repository.CrudRepository;


/**
 * Simple Spring Data repository for blog posts.
 *
 * @author dyy
 */

public interface BlogRepository extends CrudRepository<BlogPost, UUID> {
}