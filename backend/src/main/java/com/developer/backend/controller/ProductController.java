package com.developer.backend.controller;

import com.developer.backend.entity.ApiResponse;
import com.developer.backend.entity.Product;
import com.developer.backend.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Controller", description = "Operations related to Product")

public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        
        return product.map(value -> ResponseEntity.status(200).body(new ApiResponse<>("success", value, "Product retrieved successfully")))
		.orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse<>("error", null, "Product not found in fidnById")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {
        try {
            product.setCreatedDate(null);
			Product savedProduct = productService.save(product);
			return ResponseEntity.status(200).body(new ApiResponse<>("success", savedProduct, "Product created successfully"));

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> findAll() {
		try {
			List<Product> products = productService.findAll();
        	return ResponseEntity.status(200).body(new ApiResponse<>("success", products , "Products retrieved successfully"));

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
	}

    @GetMapping("/sortAll/products")
	public ResponseEntity<ApiResponse<Map<String, Object>>> sortAll(
        @RequestParam(defaultValue = "") String action,
		@RequestParam(defaultValue = "") String Param,
		@RequestParam(defaultValue = "desc") String TypeOrd,
		@RequestParam(defaultValue = "0")  Integer Page,
		@RequestParam(defaultValue = "0")  Integer Limit
	) {

		Map<String, Object> json = new HashMap<>();
		try {

			System.out.println("action: "+action);
			List<Product> products;
			json.put("action", action);
			if(action.equals("")){

				return ResponseEntity.ok(new ApiResponse<>("success", json, "Arrays retrieved por sort default successfully"));
			}else if(action.equals("home")){
				
				Limit=3;

				//String query = "SELECT DISTINCT ON (category_fk) * FROM ecommerce.product ORDER BY category_fk, id "+TypeOrd+" limit "+Limit;
				String query = "SELECT DISTINCT ON (category_fk) * FROM ecommerce.product ORDER BY category_fk, id "+TypeOrd+" limit 3";
				System.out.println("sql: "+query);
				products = productService.sortAll(query, Product.class);

				json.put("products", products);


			}else if(action.equals("product")){

				Integer offset=(Page-1)*Limit;
			
				//SELECT * FROM ecommerce.product where LOWER(name) LIKE '%tele%' or LOWER(description) LIKE '%tele%' order by id desc offset 0 limit 3 
				String query = "SELECT * FROM ecommerce.product where LOWER(name) LIKE "+"'%"+Param+"%'" +" or LOWER(description) LIKE "+"'%"+Param+"%'"+ " order by id "+TypeOrd+ " offset "+offset+" limit "+Limit;
				products = productService.sortAll(query, Product.class);

				query = "SELECT count(*) FROM ecommerce.product where LOWER(name) LIKE "+"'%"+Param+"%'" +" or LOWER(description) LIKE "+"'%"+Param+"%'";
				String count = productService.Count(query);
				
				json.put("count", count);
				json.put("products", products);

				//return ResponseEntity.status(200).body(new ApiResponse<>("success", json, "Products retrieved por sort successfully"));


				/* Integer offset=0;
				Limit=2;
				Param="tele";

				//SELECT * FROM ecommerce.product where LOWER(name) LIKE '%tele%' or LOWER(description) LIKE '%tele%' order by id desc offset 0 limit 3 
				String query = "SELECT * FROM ecommerce.product where LOWER(name) LIKE "+"'%"+Param+"%'" +" or LOWER(description) LIKE "+"'%"+Param+"%'"+ " order by id "+TypeOrd+ " offset "+offset+" limit "+Limit;
				List<Product> products = productService.sortAll(query, Product.class);

				query = "SELECT count(*) FROM ecommerce.product where LOWER(name) LIKE "+"'%"+Param+"%'" +" or LOWER(description) LIKE "+"'%"+Param+"%'";
				String count = productService.Count(query);
				
				json.put("count", count);
				json.put("products", products); */
			
			}


			return ResponseEntity.status(200).body(new ApiResponse<>("success", json, "Products retrieved por sort successfully"));

        	

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
	}

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            Product obj1 = product.get();
            obj1.setName(productDetails.getName());
            obj1.setPublicId(productDetails.getPublicId());
            obj1.setPrice(productDetails.getPrice());
            obj1.setSize(productDetails.getSize());
            obj1.setColor(productDetails.getColor());
            obj1.setBrand(productDetails.getBrand());
            obj1.setDescription(productDetails.getDescription());
            obj1.setFeatured(productDetails.getFeatured());
            obj1.setNbInStock(productDetails.getNbInStock());
            obj1.setLastModifiedDate(LocalDateTime.now());
            obj1.setCategory(productDetails.getCategory());
            return ResponseEntity.status(200).body(new ApiResponse<>("success", productService.save(obj1), "Product created successfully"));
   
        } else {
            return ResponseEntity.status(400).body(new ApiResponse<>("error", null,"updateProduct"));

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@RequestParam Long id) {
        try {
			productService.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(404).body(new ApiResponse<>("error", null, e.getLocalizedMessage()));
		}
		
        return ResponseEntity.ok(new ApiResponse<>("success", null, "Product deleted successfully"));
    }
}