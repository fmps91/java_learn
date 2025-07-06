package com.developer.backend.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developer.backend.entity.Person;
import com.developer.backend.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.developer.backend.entity.ApiResponse;

import com.developer.backend.entity.Pagination;

@RestController
@RequestMapping("/api/user")
@Tag(name = "Person Controller", description = "Operations related to person")
@SecurityRequirement(name = "bearerAuth")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@PostMapping("/")
	//@PreAuthorize("hasAuthority('ROLE_USER')") 
	@Operation(summary = "Person save", description = "Retrieve a person save in database",
	security = @SecurityRequirement(name = "bearerAuth")
	)
	public ResponseEntity<ApiResponse<Person>> save(@RequestBody Person person) {
		
		try {
			person.setCreatedDate(null);
			System.out.println("person:  "+person.getEmail());
			Person savedPerson = personService.savePerson(person);
			return ResponseEntity.status(200).body(new ApiResponse<>("success", savedPerson, "Person created successfully"));

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("e: "+e);
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
	}
	
	@GetMapping("/findAll")
	@Operation(summary = "Person findall", description = "Retrieve a list of users",
	security = @SecurityRequirement(name = "bearerAuth") 
	)
	public ResponseEntity<ApiResponse<List<Person>>> findAll() {
		try {
			List<Person> persons = personService.findAll();
        	return ResponseEntity.status(200).body(new ApiResponse<>("success", persons, "Persons retrieved successfully"));

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
	}

	@GetMapping("/sortAll")
	@Operation(summary = "Person findall", description = "Retrieve a list of users",
	security = @SecurityRequirement(name = "bearerAuth") 
	)
	public ResponseEntity<ApiResponse<Map<String, Object>>> sortAll(
		//@RequestParam(defaultValue = "0") int page,
        //@RequestParam(defaultValue = "nada") String Param,
		//@RequestParam(defaultValue = "nada") String TypeOrd,
		@RequestParam(defaultValue = "0")  Integer Page,
		@RequestParam(defaultValue = "0")  Integer Limit
	) {
		try {
			System.out.println("   Page: "+Page+"   Limit: "+Limit);
			/* if (Limit==0||Page==0) {
				Map<String, Object> json = new HashMap<>();
				return ResponseEntity.ok(new ApiResponse<>("success", json, "Persons retrieved por sort default successfully"));
			}
 */

			Integer offset=(Page-1)*Limit;
			
			//System.out.println("TypeOrd: "+TypeOrd+"   Page: "+Page+"   Param: "+Param+"   Limit: "+Limit);
			Pagination pagination=new Pagination("","", "",Page,offset, Limit);

			List<Person> persons = personService.sortAll(pagination);
			Long count=personService.count();

			//System.out.println("data "+persons);

			Map<String, Object> json = new HashMap<>();
			json.put("count", count);
			json.put("persons", persons);
			
			
        	return ResponseEntity.status(200).body(new ApiResponse<>("success", json, "Persons retrieved por sort successfully"));

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
	}
	
	@GetMapping("/findById")
	@Operation(summary = "Person findById", description = "Retrieve a find user by id")
	public  ResponseEntity<ApiResponse<Person>> findById(@RequestParam Long id) {

		try {
			Person person = personService.findById(id);
			return ResponseEntity.status(200).body(new ApiResponse<>("success", person, "Person retrieved successfully"));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
		
	}

	@GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<Person>> getPersonByEmail(@PathVariable String email) {
        Optional<Person> person = personService.getByEmail(email);
		return person.map(value -> ResponseEntity.status(200).body(new ApiResponse<>("success", value, "Person retrieved successfully")))
		.orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse<>("error", null, "Person not found in email")));
    }

    @GetMapping("/public-id/{publicId}")
    public ResponseEntity<ApiResponse<Person>> getPersonByPublicId(@PathVariable UUID publicId) {
        Optional<Person> person = personService.getByPublicId(publicId);
        return person.map(value -> ResponseEntity.status(200).body(new ApiResponse<>("success", value, "Person retrieved successfully")))
                     .orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse<>("error", null, "Person not found in public id")));
    }
	
	@PutMapping("/{id}")
	@Operation(summary = "Person update", description = "Retrieve a user for update") 
	public ResponseEntity<ApiResponse<Person>> updatePerson(@PathVariable Long id,@RequestBody Person personDetails) {
	//public ResponseEntity<ApiResponse<Person>> updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        Optional<Person> person = Optional.of(personService.findById(id));
        if (person.isPresent()) {
            Person updatedPerson = person.get();
            updatedPerson.setFirstname(personDetails.getFirstname());
            updatedPerson.setLastname(personDetails.getLastname());
            updatedPerson.setEmail(personDetails.getEmail());
            updatedPerson.setAddress_street(personDetails.getAddress_street());
            updatedPerson.setAddress_zip_code(personDetails.getAddress_zip_code());
            updatedPerson.setAddress_country(personDetails.getAddress_country());
            updatedPerson.setImage_url(personDetails.getImage_url());
            updatedPerson.setLastSeen(LocalDateTime.now());
            //updatedPerson.setCreated_date(LocalDateTime.now());
            updatedPerson.setLastModifiedDate(LocalDateTime.now());
            return ResponseEntity.status(200).body(new ApiResponse<>("success", personService.savePerson(updatedPerson), "Person updated successfully"));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("error", null, "Person not found"));
        }
    }
	
	@DeleteMapping("/deleteById")
	public ResponseEntity<ApiResponse<Void>> delete(@RequestParam Long id) {
        try {
			personService.deletePerson(id);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(404).body(new ApiResponse<>("error", null, e.getLocalizedMessage()));
		}
		
        return ResponseEntity.status(200).body(new ApiResponse<>("success", null, "Person deleted successfully"));
    }
	
}
