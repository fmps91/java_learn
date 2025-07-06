package com.developer.backend.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.developer.backend.entity.ApiResponse;
import com.developer.backend.entity.Product;
import com.developer.backend.entity.ProductPicture;
import com.developer.backend.service.ProductPictureService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/product-pictures")
@Tag(name = "ProductPicture Controller", description = "Operations related to ProductPicture")

public class ProductPictureController {

    @Autowired
    private ProductPictureService productPictureService;

    
 /*    public ProductPictureController(ProductPictureService productPictureService) {
        this.productPictureService = productPictureService;
    } */

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<?>> createProductPicture(
            @RequestParam("file") MultipartFile file,
            @RequestParam("productId") Long productId) {
        try {
            Map<String, String> savedPicture = productPictureService.save(file, productId);
            return ResponseEntity.status(200).body(new ApiResponse<>("success", savedPicture, "ProductPicture retrieved successfully"));
        } catch (IOException e) {
            return ResponseEntity.status(401).body(new ApiResponse<>("error", null, "Error processing file: "+ e.getMessage()));
            //return new ResponseEntity<>("Error processing file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse<>("error", null, "Error not found: "+ e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductPicture>> getProductPicture(@PathVariable Long id) {
        
        Optional<ProductPicture> obj = productPictureService.findById(id);

        return obj.map(value -> ResponseEntity.status(200).body(new ApiResponse<>("success", value, "ProductPicture retrieved successfully")))
		.orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse<>("error", null, "ProductPicture not found in fidnById")));
        
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ApiResponse<List<ProductPicture>>> getPicturesProduct(@PathVariable Long id) {
        
        try {
            Optional<List<ProductPicture>> obj = productPictureService.findAllPictures(id);
            
            if (obj.empty() != null) {
                return ResponseEntity.status(200).body(new ApiResponse<>("success", obj.get(), "Pictures of products  retrieved successfully"));
            }
            return ResponseEntity.status(404).body(new ApiResponse<>("error", null,"Person id not found"));

       } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

        }
        
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductPicture>>> getAllProductPictures() {
        try {
            List<ProductPicture> pictures = productPictureService.findAll();
            return ResponseEntity.status(200).body(new ApiResponse<>("success", pictures , "ProductPictures retrieved successfully"));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

        }

    }

    /* @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductPicture>> getProductPicturesByProductId(@PathVariable Long productId) {
        List<ProductPicture> pictures = productPictureService.findByProductId(productId);
        return ResponseEntity.ok(pictures);
    } */

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProductPicture(@PathVariable Long id) {
        
        try {
			productPictureService.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(404).body(new ApiResponse<>("error", null, e.getLocalizedMessage()));
		}
		
        return ResponseEntity.status(200).body(new ApiResponse<>("success", null, "ProductPicture deleted successfully"));
    }

}



