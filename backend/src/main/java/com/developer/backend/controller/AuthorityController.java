
package com.developer.backend.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.backend.entity.Authority;
import com.developer.backend.entity.Order;
import com.developer.backend.entity.OrderedProduct;
import com.developer.backend.service.AuthorityService;
import com.developer.backend.entity.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/authority")
@Tag(name = "Authority Controller", description = "Operations related to rols")
public class AuthorityController {
	
	@Autowired
    private AuthorityService authorityService;

   @GetMapping("/findAll")
    public ResponseEntity<ApiResponse<List<Authority>>> getAllAuthorities() {
        
        try {
			List<Authority> objs = authorityService.findAll();
        	return ResponseEntity.status(200).body(new ApiResponse<>("success", objs, "Authorities retrieved successfully"));

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
    }

    @GetMapping("/{name}")
    public ResponseEntity<ApiResponse<Authority>> getAuthorityByName(@PathVariable String name) {
        try {
			Authority authority = authorityService.findByString(name);
			return ResponseEntity.status(200).body(new ApiResponse<>("success", authority, "Authority retrieved successfully"));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Authority>> createAuthority(@RequestBody Authority authority) {
        try {
            authority.setCreatedDate(null);
			Authority save = authorityService.saveAuthority(authority);	
			return ResponseEntity.status(200).body(new ApiResponse<>("success", save, "Authority retrieved successfully"));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
    }

	@PutMapping("/{name}")
    public ResponseEntity<ApiResponse<Authority>> updateOrder(@PathVariable String name,@RequestBody Map<String, String> map ) {
        Optional<Authority> obj = Optional.of(authorityService.findByString(name));
        if (obj.isPresent()) {
            Authority obj1 = obj.get();
            obj1.setName(map.get("name"));
            obj1.setLastModifiedDate(LocalDateTime.now());
            //obj1.setLastModifiedDate(LocalDateTime.now());

            return ResponseEntity.status(200).body(new ApiResponse<>("success",authorityService.saveAuthority(obj1), "update successfully"));

        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("error", null, "Authority Not found"));

        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<ApiResponse<Void>>  deleteAuthority(@PathVariable String name) {
        
        try {
			authorityService.deleteAuthority(name);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(404).body(new ApiResponse<>("error", null, e.getLocalizedMessage()));
		}
		
        return ResponseEntity.status(200).body(new ApiResponse<>("success", null, "Authority deleted successfully"));
    }

	
	
}
