package com.wandersonalves.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandersonalves.workshopmongo.domain.Post;
import com.wandersonalves.workshopmongo.repository.PostRepository;
import com.wandersonalves.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
//	public List<Post> findByTitle(String text){
//		return repo.findByTitleContainingIgnoreCase(text);
//	}

	// metodo alternativo // Query manual
	public List<Post> findByTitle(String text){
		return repo.findByTitle(text);
	}
	
	public List<Post> search(String text, Date minDate, Date maxDate){
		// Add mais um dia ao maxDate pois um instante pode ocorrer até o final do dia.
		maxDate = new Date(maxDate.getTime() + 24 *60 *60 * 1000);
		return repo.search(text, minDate, maxDate);
	}

}
