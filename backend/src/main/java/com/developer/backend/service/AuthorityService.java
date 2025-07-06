
package com.developer.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.developer.backend.entity.Authority;
import com.developer.backend.repository.AuthorityRepository;

@Service
public class AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	public Authority saveAuthority(Authority authority) {
		return authorityRepository.save(authority);
	}

	public Authority findByString(String name) {
		Optional<Authority> authority = authorityRepository.findById(name);

		if (authority.isEmpty()) {
			throw new RuntimeException("Authority not found");
		}

		return authority.get();
	}

	public List<Authority> findAll() {
		return authorityRepository.findAll();
	}


	public void deleteAuthority(String name) {
		Optional<Authority> db = authorityRepository.findById(name);

		if (db.isEmpty()) {
			throw new RuntimeException("Authority not found");
		}

		authorityRepository.delete(db.get());
	}

}
