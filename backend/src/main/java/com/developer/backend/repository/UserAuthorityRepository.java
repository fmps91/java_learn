
package com.developer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.developer.backend.entity.UserAuthorityId;
import com.developer.backend.entity.UserAuthority;

@Repository
@EnableJpaRepositories
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, UserAuthorityId>{
}
