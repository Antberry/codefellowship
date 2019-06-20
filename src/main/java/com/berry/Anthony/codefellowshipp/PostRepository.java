package com.berry.Anthony.codefellowshipp;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<UserPost, Long> {
//    List<UserPost> findByUsername(String username);

}
