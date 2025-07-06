
package com.developer.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.developer.backend.entity.UserAuthorityId;
import com.developer.backend.entity.UserAuthority;
import com.developer.backend.repository.UserAuthorityRepository;

@Service
public class UserAuthorityService {

	@Autowired
	private UserAuthorityRepository userAuthorityRepository;

	public UserAuthority saveUser_authority(UserAuthority userAuthority) {
		
		//UserAuthority obj=new UserAuthority(userAuthority.getUserId(),userAuthority.getAuthorityName());
		return userAuthorityRepository.save(userAuthority);
	}

	public UserAuthority findByCompuest(UserAuthorityId id) {
		Optional<UserAuthority> authority = userAuthorityRepository.findById(id);

		if (authority.isEmpty()) {
			throw new RuntimeException("Authority not found");
		}

		return authority.get();
	}

	public List<UserAuthority> findAll() {
		return userAuthorityRepository.findAll();
	}


	public void deleteUser_authority(UserAuthorityId id) {
		Optional<UserAuthority> db = userAuthorityRepository.findById(id);

		if (db.isEmpty()) {
			throw new RuntimeException("UserAuthority not found");
		}

		userAuthorityRepository.delete(db.get());
	}

}
