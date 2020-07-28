package com.alexdyysp.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

/**
 * Simple Spring Data repository for blog posts.
 *
 * @author dyy
 */
public interface BlogRepository extends CrudRepository<BlogPost, UUID> {
}
