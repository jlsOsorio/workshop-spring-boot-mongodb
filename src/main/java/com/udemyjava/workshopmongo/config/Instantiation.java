package com.udemyjava.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.udemyjava.workshopmongo.domain.Post;
import com.udemyjava.workshopmongo.domain.User;
import com.udemyjava.workshopmongo.dto.AuthorDTO;
import com.udemyjava.workshopmongo.dto.CommentDTO;
import com.udemyjava.workshopmongo.repositories.PostRepository;
import com.udemyjava.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("10/07/2022"), "Partiu viagem", "Vou viajar para o Algarve! Abraços.", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("13/07/2022"), "Bom dia", "Acordei cheio de vontade de programar!", new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO("Boa viagem, mano!", sdf.parse("10/07/2022"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveita", sdf.parse("11/07/2022"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tem um dia espectacular!", sdf.parse("13/07/2022"), new AuthorDTO(alex));

		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));

		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
