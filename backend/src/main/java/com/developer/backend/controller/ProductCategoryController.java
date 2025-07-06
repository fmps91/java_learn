
package com.developer.backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.developer.backend.entity.ApiResponse;
import com.developer.backend.entity.Person;
import com.developer.backend.entity.ProductCategory;
import com.developer.backend.service.ProductCategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product-category")
@Tag(name = "ProductCategory Controller", description = "Operations related to ProductCategory")

public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductCategory>>> getAll() {
        try {
			List<ProductCategory> objs = productCategoryService.findAll();
        	return ResponseEntity.status(200).body(new ApiResponse<>("success", objs, "ProductCategorys retrieved successfully"));

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}

    }

    /* @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getById(@PathVariable Long id) {
        return productCategoryService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    } */
    @GetMapping("/{id}")
    public  ResponseEntity<ApiResponse<ProductCategory>> getById(@PathVariable Long id) {

        
        Optional<ProductCategory> obj = productCategoryService.findById(id);

        return obj.map(value -> ResponseEntity.status(200).body(new ApiResponse<>("success", value, "ProductCategory retrieved successfully")))
		.orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse<>("error", null, "ProductCategory not found in getbyId")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductCategory>> create(@RequestBody ProductCategory productCategory) {
    

        try {
            productCategory.setCreatedDate(null);
			ProductCategory obj = productCategoryService.save(productCategory);
			return ResponseEntity.status(200).body(new ApiResponse<>("success", obj, "ProductCategory created successfully"));

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductCategory>> update(@PathVariable Long id, @RequestBody ProductCategory productCategory) {
        try {
            productCategory.setLastModifiedDate(LocalDateTime.now());
            ProductCategory obj=productCategoryService.update(id, productCategory);
            return ResponseEntity.status(200).body(new ApiResponse<>("success", obj, "ProductCategory update successfully"));
            //return ResponseEntity.ok(productCategoryService.update(id, productCategory));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        

        try {
			productCategoryService.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(404).body(new ApiResponse<>("error", null, e.getLocalizedMessage()));
		}

        return ResponseEntity.status(200).body(new ApiResponse<>("success", null, "ProductCategory deleted successfully"));

    }
}
