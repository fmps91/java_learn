/* package com.developer.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.developer.backend.entity.Person;


public interface PersonRepository extends JpaRepository<Person,Long>{

  @Query("select u from User u where u.email = :email")
  Person findByUser(@Param("email") String email);
}
 */

 package com.developer.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Sort; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.CustomRepositoryImplementationDetector;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.developer.backend.entity.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@EnableJpaRepositories
public interface PersonRepository extends JpaRepository<Person,Long>,CustomRepository  {

  

  Optional<Person> findByEmail(String email);

  List<Person> findByPublicIdIn(List<UUID> publicIds);

  Optional<Person> findOneByPublicId(UUID publicId);

  @Query("SELECT p FROM Person p WHERE p.username = :username AND p.password = :password")
  Optional<Person> login(@Param("username") Object username,@Param("password") Object password);

  /*
  este es solo order by
  @Query("SELECT p FROM Person p")
  List<Person> findAllWithCustomSort(
    Sort sort
  ); */
  

  @Query(value = "SELECT * FROM ecommerce.person OFFSET :offset LIMIT :limit", nativeQuery = true)
  List<Person> findWithPagination(
    @Param("offset") int offset, 
    @Param("limit") int limit
);

  


}