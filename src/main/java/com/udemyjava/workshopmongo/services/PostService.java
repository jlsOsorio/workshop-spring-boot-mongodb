package com.udemyjava.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemyjava.workshopmongo.domain.Post;
import com.udemyjava.workshopmongo.repositories.PostRepository;
import com.udemyjava.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado."));
	}

}
