package com.developer.backend.controller;

import com.developer.backend.entity.ApiResponse;
import com.developer.backend.entity.Order;
import com.developer.backend.entity.OrderedProduct;
import com.developer.backend.entity.OrderedProductId;
import com.developer.backend.entity.Person;
import com.developer.backend.service.OrderedProductService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ordered-products")
@Tag(name = "OrderedProduct Controller", description = "Operations related to orderedproduct")

public class OrderedProductController {

    @Autowired
    private OrderedProductService orderedProductService;

    @GetMapping
    public  ResponseEntity<ApiResponse<List<OrderedProduct>>> getAllOrderedProducts() {
       

        try {
			List<OrderedProduct> objs = orderedProductService.findAll();
        	return ResponseEntity.status(200).body(new ApiResponse<>("success", objs, "OrderProducts retrieved successfully"));

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
    }

    @GetMapping("/{orderId}/{productId}")
    public  ResponseEntity<ApiResponse<OrderedProduct>> getOrderedProductById(@PathVariable Long orderId, @PathVariable UUID productId) {
        OrderedProductId id = new OrderedProductId();
        id.setOrderId(orderId);
        id.setProductId(productId);
        Optional<OrderedProduct> obj = orderedProductService.findById(id);
        //return orderedProduct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return obj.map(value -> ResponseEntity.status(200).body(new ApiResponse<>("success", value, "OrderProduct retrieved successfully")))
                     .orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse<>("error", null, "OrderProduct error getOrderedProductById")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderedProduct>> createOrderedProduct(@RequestBody OrderedProduct orderedProduct) {
        
        orderedProduct.setCreatedDate(null);
        Optional<OrderedProduct> obj = Optional.of(orderedProductService.save(orderedProduct));
        
        return obj.map(value -> ResponseEntity.status(200).body(new ApiResponse<>("success", value, "Order retrieved successfully")))
                     .orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse<>("error", null, "OrderProduct error save")));
        
    }

    @PutMapping("/{orderId}/{productId}")
    public ResponseEntity<ApiResponse<OrderedProduct>> updateOrderedProduct(@PathVariable Long orderId, @PathVariable UUID productId, @RequestBody OrderedProduct orderedProductDetails) {
        OrderedProductId id = new OrderedProductId();
        id.setOrderId(orderId);
        id.setProductId(productId);
        Optional<OrderedProduct> obj = orderedProductService.findById(id);
        if (obj.isPresent()) {
            OrderedProduct obj1 = obj.get();
            obj1.setQuantity(orderedProductDetails.getQuantity());
            obj1.setPrice(orderedProductDetails.getPrice());
            obj1.setProductName(orderedProductDetails.getProductName());
            obj1.setLastModifiedDate(LocalDateTime.now());
            return ResponseEntity.status(200).body(new ApiResponse<>("success", orderedProductService.save(orderedProductService.save(obj1)), "update successfully"));


        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("error", null, "orderProduct Not found"));

        }
    }

    @DeleteMapping("/{orderId}/{productId}")
    public ResponseEntity<ApiResponse<Void>> deleteOrderedProduct(@PathVariable Long orderId, @PathVariable UUID productId) {
        

        try {
			OrderedProductId id = new OrderedProductId();
            id.setOrderId(orderId);
            id.setProductId(productId);
            orderedProductService.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(404).body(new ApiResponse<>("error", null, e.getLocalizedMessage()));
		}
		
        return ResponseEntity.status(200).body(new ApiResponse<>("success", null, "OrdenProduct deleted successfully"));
    }
}
