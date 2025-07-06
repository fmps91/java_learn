
package com.developer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.developer.backend.entity.Authority;

@Repository
@EnableJpaRepositories
public interface AuthorityRepository extends JpaRepository<Authority,String>{

    Authority findByName(String name);
}
