package com.alexdyysp.dao;

import com.alexdyysp.Entity.BLOGPOST;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Simple Spring Data repository for blog posts.
 *
 * @author dyy
 */
public interface BlogRepository extends JpaRepository<BLOGPOST, String> {
}
