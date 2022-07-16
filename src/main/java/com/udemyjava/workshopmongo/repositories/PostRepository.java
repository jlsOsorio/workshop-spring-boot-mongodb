package com.udemyjava.workshopmongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.udemyjava.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	@Query("{ 'title': { $regex: ?0, $options: 'i' } }") //?0 indica que é o primeiro parâmetro do método (0 -> text) que será usado para o regex
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
}
