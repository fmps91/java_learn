package com.developer.backend.controller;

import java.time.LocalDateTime;
import java.util.List;
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


import com.developer.backend.entity.UserAuthorityId;
import com.developer.backend.entity.ApiResponse;
import com.developer.backend.entity.Person;
import com.developer.backend.entity.Product;
import com.developer.backend.entity.ProductPicture;
import com.developer.backend.entity.UserAuthority;
import com.developer.backend.service.UserAuthorityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api/user_authority")
@Tag(name = "UserAuthority Controller", description = "Operations related to User with rols")
public class UserAuthorityController {
	
	@Autowired
	private UserAuthorityService userAuthorityService;

	@PostMapping("/")
	/* @Operation(summary = "UserAuthority save", description = "Retrieve a User_authority save in database",
	security = @SecurityRequirement(name = "bearerAuth")
	) */
	public ResponseEntity<ApiResponse<UserAuthority>> save(@RequestBody UserAuthority user_authority) {
		 try {
			
			//user_authority.setCreatedDate(null);
			/* UserAuthorityId id=new UserAuthorityId();
			id.setAuthorityName(user_authority.getAuthorityName());
			id.setUserId(user_authority.getUserId()); */

			user_authority.setCreatedDate(LocalDateTime.now());
			UserAuthority save =userAuthorityService.saveUser_authority(user_authority);
			return ResponseEntity.status(200).body(new ApiResponse<>("success", save, "UserAuthority created successfully"));

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error: "+e);
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<ApiResponse<List<UserAuthority>>> findAll() {

		try {
            List<UserAuthority> objs = userAuthorityService.findAll();
            return ResponseEntity.status(200).body(new ApiResponse<>("success", objs , "UserAuthority retrieved successfully"));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

        }
	}
	
	@GetMapping("/findById")
	//public ResponseEntity<ApiResponse<UserAuthority>> findById(@RequestParam UserAuthorityId id) {
	public ResponseEntity<ApiResponse<UserAuthority>> findById(@RequestParam String authority,@RequestParam Long id) {

		System.out.println("authority:  "+authority);
		System.out.println("id:  "+id);

		try {
			UserAuthorityId idC = new UserAuthorityId();
			idC.setAuthorityName(authority);
			idC.setUserId(id); 
			UserAuthority userAuthority = userAuthorityService.findByCompuest(idC);
			return ResponseEntity.status(200).body(new ApiResponse<>("success", userAuthority, "UserAuthority retrieved successfully"));
		} catch (RuntimeException e) {
			return ResponseEntity.status(404).body(new ApiResponse<>("error", null, e.getMessage()));
		}
	}


	/* @PutMapping("/{userId}/{authorithyId}")
	public ResponseEntity<ApiResponse<UserAuthority>> Update(@PathVariable Long userId, @PathVariable String authorithyId, @RequestBody UserAuthority userAuthority) {

		
		UserAuthorityId id=new UserAuthorityId();
		id.setAuthorityName(authorithyId);
		id.setUserId(userId);
		Optional<UserAuthority> obj = Optional.of(userAuthorityService.findByCompuest(id));
        if (obj.isPresent()) {
            UserAuthority obj1 = obj.get();
			obj1.setAuthorityName(userAuthority.getAuthorityName());
			obj1.setUserId(userAuthority.getUserId());
            obj1.setLastModifiedDate(LocalDateTime.now());
            return ResponseEntity.status(200).body(new ApiResponse<>("success", userAuthorityService.saveUser_authority(obj1), "UserAuthority update successfully"));
   
        } else {
            return ResponseEntity.status(400).body(new ApiResponse<>("error", null,"updateProduct"));

        }
	} */
	
	
	@DeleteMapping("/deleteById")
	public ResponseEntity<ApiResponse<Void>> delete(@RequestParam UserAuthorityId id) {
		

		try {
			userAuthorityService.deleteUser_authority(id);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(404).body(new ApiResponse<>("error", null, e.getLocalizedMessage()));
		}
		
        return ResponseEntity.status(200).body(new ApiResponse<>("success", null, "UserAuthority deleted successfully"));
	}
	
	
}
