/* package com.developer.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.backend.entity.Person;
import com.developer.backend.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Person saveperson(Person person) {
		return personRepository.save(person);
	}

	public Person findById(Long id) {
		Optional<Person> person = personRepository.findById(id);

		if (person.isEmpty()) {
			throw new RuntimeException("person not found");
		}

		return person.get();
	}

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public Person updateperson(Person person) {
		Optional<Person> dbperson = personRepository.findById(person.getId());

		if (dbperson.isEmpty()) {
			throw new RuntimeException("person not found");
		}

		Person existingperson = dbperson.get();
		existingperson.setpersonname(person.getpersonname());

		return personRepository.save(existingperson);
	}

	public void deleteperson(Long id) {
		Optional<Person> dbperson = personRepository.findById(id);

		if (dbperson.isEmpty()) {
			throw new RuntimeException("person not found");
		}

		personRepository.delete(dbperson.get());
	}

}
 */

package com.developer.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.backend.entity.Pagination;
import com.developer.backend.entity.Person;
import com.developer.backend.repository.PersonRepository;



@Service
public class PersonService {

	@Autowired
    private PersonRepository personRepository;

	public Person savePerson(Person person) {
		return personRepository.save(person);
	}

	public Optional<Person> getByEmail(String email) {
		return personRepository.findByEmail(email);
	}
	
	public Optional<Person> getByPublicId(UUID publicId) {
		return personRepository.findOneByPublicId(publicId);
	}

	public Person login(String username,String password){
		System.out.println("username: "+username+"  password: "+password);
		Optional<Person> obj = personRepository.login(username,password);
		if (obj.isEmpty()) {
			throw new RuntimeException("person not found");
		}
		
		return obj.get();
	}

	public Person findById(Long id) {
		Optional<Person> person = personRepository.findById(id);

		if (person.isEmpty()) {
			throw new RuntimeException("person not found");
		}

		return person.get();
	}

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	/* 
	este es solo paginacion
	public List<Person> sortAll(Pagination pagination) {
		
		Integer offset=(pagination.getPage()-1)*pagination.getLimit();
		pagination.setOffset(offset);
		Sort sort = Sort.by(Sort.Direction.DESC, pagination.getParam());
		return personRepository.findAllWithCustomSort(sort);
	} */

	public Long count() {
		return personRepository.count();
	}

	public List<Person> sortAll(Pagination pagination) {

		/* Object result = personRepository.findByDynamicColumn("SELECT * FROM ecommerce.person WHERE id=1");

		System.out.println("esto que es:"+(UUID)((Object[]) result)[1]);
		if (result instanceof Object[]) {
			Object[] row = (Object[]) result;
			Long id = (Long) row[0]; // Primera columna
			UUID public_id = (UUID)row[1]; // Segunda columna
			String first_name = (String) row[2]; // Tercera columna

			System.out.println("ID: " + id);
			System.out.println("public_id: " + public_id);
			System.out.println("first_name: " + first_name);
		} else {
			throw new IllegalStateException("Unexpected result type: " + result.getClass());
		} */



		/* Map<String, Object> map = new HashMap<>();
		map.put("col", "id");
        map.put("value", "1");

		Object result = personRepository.findByDynamicColumn(map);

		System.out.println("esto que es:"+result); */


		//List<Person> listperson =personRepository.ListfindBySQLClass("", Person.class);
		
		//System.out.println("person: "+listperson.get(0).getFirstname());

		return personRepository.findWithPagination(
			pagination.getOffset(),
			pagination.getLimit()
			);
	}

	public Person updatePerson(Person person) {
		Optional<Person> dbperson = personRepository.findById(person.getId());

		if (dbperson.isEmpty()) {
			throw new RuntimeException("person not found");
		}

		Person existingperson = dbperson.get();
		existingperson.setFirstname(existingperson.getFirstname());

		return personRepository.save(existingperson);
	}

	public void deletePerson(Long id) {
		Optional<Person> dbperson = personRepository.findById(id);

		if (dbperson.isEmpty()) {
			throw new RuntimeException("person not found");
		}

		personRepository.delete(dbperson.get());
	}

}